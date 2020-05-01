package com.taotao.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 单点登录系统页面跳转Controller
 * @author zhaoyuan
 * @date 2019年5月3日 下午9:45:14
 */
@Controller
@RequestMapping("/page")
public class PageController {

	/**
	 * 注册页面跳转
	 * @return
	 */
	@RequestMapping(value="/register")
	public String showRegister(){
		return "register";
	}
	
	
	/**
	 * 登录页面跳转
	 * @param url   登录成功后回调的url
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/login")
	public String showLogin(String url,Model model){
		model.addAttribute("redirect", url);
		return "login";
	}
	
}
