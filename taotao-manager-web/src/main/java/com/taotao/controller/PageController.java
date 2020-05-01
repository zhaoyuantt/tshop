package com.taotao.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 页面跳转Controller
 * @author zhaoyuan
 * @date 2019-02-17 2:59 PM
 */
@Controller
public class PageController {

	/**
	 * 后台首页跳转
	 * @param request
	 * @return
	 */
	@RequestMapping("/")
	public ModelAndView showIndex(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		
		//从Session获取用户名
		HttpSession session = request.getSession();
		Object adminUser = session.getAttribute("USER");
		
		modelAndView.addObject("USER", adminUser);
		modelAndView.setViewName("index");
		
		return modelAndView;
		//return "index";
	}
	
	/**
	 * 页面跳转
	 * @param page
	 * @return
	 */
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page){
		return page;
	}
}
