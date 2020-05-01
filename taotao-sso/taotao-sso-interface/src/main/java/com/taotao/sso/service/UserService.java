package com.taotao.sso.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

/**
 * 用户处理Service接口
 * @author zhaoyuan
 * @date 2019年5月3日 下午2:47:59
 */
public interface UserService {

	/**
	 * 检查用户注册数据是否可用
	 * @param date
	 * @param type
	 * @return
	 */
	TaotaoResult checkUserDate(String date,int type);
	
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	TaotaoResult userRegister(TbUser user);
	
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return
	 */
	TaotaoResult userLogin(String username,String password);
	
	/**
	 * 根据token查询用户信息
	 * @param token
	 * @return
	 */
	TaotaoResult getUserByToken(String token);
	
	/**
	 * 根据token退出
	 * @param token
	 * @return
	 */
	TaotaoResult userLogout(String token);
}
