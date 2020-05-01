package com.taotao.sso.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.jedis.JedisClient;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.pojo.TbUserExample.Criteria;
import com.taotao.sso.service.UserService;
import com.taotao.utils.JsonUtils;

/**
 * 用户处理Service接口实现
 * @author zhaoyuan
 * @date 2019年5月3日 下午2:50:07
 */
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private TbUserMapper userMapper;
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${USER_SESSION}")
	private String USER_SESSION;
	@Value("${USER_SESSION_EXPIRE}")
	private int USER_SESSION_EXPIRE;

	@Override
	public TaotaoResult checkUserDate(String date, int type) {
		
		//封装查询条件
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		//1、2、3分别代表username、phone、email
		if(1 == type){
			criteria.andUsernameEqualTo(date);
		}else if (2 == type){
			criteria.andPhoneEqualTo(date);
		}else if (3 == type){
			criteria.andEmailEqualTo(date);
		}else{
			return TaotaoResult.build(400, "请求参数不合法");
		}
		
		//执行查询
		List<TbUser> userList = userMapper.selectByExample(example);
		if(null != userList && userList.size()>0){
			return TaotaoResult.ok(false);
		}else{
			return TaotaoResult.ok(true);
		}
		
	}

	@Override
	public TaotaoResult userRegister(TbUser user) {
		//校验数据合法性
		if(null == user.getUsername() || "".equals(user.getUsername())){
			return TaotaoResult.build(400, "用户名不能为空");
		}
		TaotaoResult result = checkUserDate(user.getUsername(), 1);
		if(!(boolean) result.getData()){
			return TaotaoResult.build(400, "用户名已经存在");
		}
		if(null == user.getPassword() || "".equals(user.getPassword())){
			return TaotaoResult.build(400, "密码不能为空");
		}
		if(null != user.getPhone()&& "".equals(user.getPhone())){
			result = checkUserDate(user.getPhone(), 2);
			if(!(boolean) result.getData()){
				return TaotaoResult.build(400, "手机号码已经存在");
			}
		}
		if(null != user.getEmail()&& "".equals(user.getEmail())){
			result = checkUserDate(user.getEmail(), 3);
			if(!(boolean) result.getData()){
				return TaotaoResult.build(400, "邮箱号已经存在");
			}
		}
		
		//补全属性
		user.setCreated(new Date());
		user.setUpdated(new Date());
		
		//密码md5加密
		String md5Pwd = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setPassword(md5Pwd);
		
		//插入数据
		userMapper.insert(user);
		
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult userLogin(String username, String password) {
		//判断参数是否为空
		if(null == username || null == password ){
			return TaotaoResult.build(400, "参数错误");
		}
		
		//判断用户名密码是否正确
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<TbUser> userList = userMapper.selectByExample(example);
		if(null == userList || userList.size() == 0){
			return TaotaoResult.build(400, "用户名或密码错误");
		}
		TbUser user = userList.get(0);
		String pwd = user.getPassword();
		if(!pwd.equals(DigestUtils.md5DigestAsHex(password.getBytes()))){
			return TaotaoResult.build(400, "用户名或密码错误");
		}
		
		//生成token
		String token = UUID.randomUUID().toString();
		//清空密码
		user.setPassword(null);
		//保存到redis
		jedisClient.set(USER_SESSION+":"+token, JsonUtils.objectToJson(user));
		jedisClient.expire(USER_SESSION+":"+token, USER_SESSION_EXPIRE);
		
		//返回token
		return TaotaoResult.ok(token);
	}

	@Override
	public TaotaoResult getUserByToken(String token) {
		String redisJson = jedisClient.get(USER_SESSION+":"+token);
		if(null == redisJson){
			return TaotaoResult.build(500, "无登录");
		}
		
		//重置过期时间
		jedisClient.expire(USER_SESSION+":"+token, USER_SESSION_EXPIRE);
		
		return TaotaoResult.ok(JsonUtils.jsonToPojo(redisJson, TbUser.class));
	}

	@Override
	public TaotaoResult userLogout(String token) {
		jedisClient.del(USER_SESSION+":"+token);
		return TaotaoResult.ok();
	}
	
}
