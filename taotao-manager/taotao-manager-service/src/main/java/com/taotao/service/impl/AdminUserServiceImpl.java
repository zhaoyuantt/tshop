package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbAdminUserMapper;
import com.taotao.pojo.TbAdminUser;
import com.taotao.pojo.TbAdminUserExample;
import com.taotao.pojo.TbAdminUserExample.Criteria;
import com.taotao.service.AdminUserService;

/**
 * 后台管理系统用户-接口实现
 * 
 * @author zhaoyuan
 * @date 2019年11月14日 下午4:57:06
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {

	private final static Logger log  = Logger.getLogger(AdminUserServiceImpl.class);
	
	@Autowired
	private TbAdminUserMapper adminUserMapper;
	
	@Override
	public TaotaoResult loginSubmit(TbAdminUser adminUser) {
		//校验参数
		if(StringUtils.isEmpty(adminUser.getUsername())){
			log.info("登录时用户名为空");
			return TaotaoResult.build(400, "登录时用户名为空");
		}
		if(StringUtils.isEmpty(adminUser.getPassword())){
			log.info("登录时密码为空");
			return TaotaoResult.build(400, "登录时密码为空");
		}
		
		//验证用户名、密码
		TbAdminUserExample example = new TbAdminUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(adminUser.getUsername());
		List<TbAdminUser> adminUserList = adminUserMapper.selectByExample(example);
		
		if(null == adminUserList || 0 == adminUserList.size()){
			log.info("登录时用户名错误");
			return TaotaoResult.build(400,"用户名或者密码错误！");
		}
		
		TbAdminUser adminUserSfy = adminUserList.get(0);
		String password = DigestUtils.md5DigestAsHex(adminUser.getUsername().getBytes());
		if(!adminUserSfy.getPassword().equals(password)){
			log.info("登录时密码错误");
			return TaotaoResult.build(400,"用户名或者密码错误！");
		}
		
		//更新最后登录时间
		TbAdminUser adminUserNll = adminUserList.get(0);
		adminUserNll.setUpdatedTime(new Date());
		adminUserMapper.updateByPrimaryKey(adminUserNll);
		
		//代码走到这里，说明用户名、密码验证成功
		return TaotaoResult.ok(adminUserSfy);
	}

}
