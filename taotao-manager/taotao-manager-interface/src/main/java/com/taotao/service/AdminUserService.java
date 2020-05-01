package com.taotao.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbAdminUser;

/**
 * 后台系统管理用户的Service接口
 * 
 * @author zhaoyuan
 * @date 2019年11月14日 下午4:52:36
 */
public interface AdminUserService {

	/**
	 * 登录提交
	 * 验证成功，返回true，否则返回false
	 * @param adminUser
	 * @return
	 */
	public TaotaoResult loginSubmit(TbAdminUser adminUser);
	
	/**
	 * 由用户名获取用户对象
	 * @param username
	 * @return
	 */
	//public TbAdminUser getAdminUserByUsername(String username);
}
