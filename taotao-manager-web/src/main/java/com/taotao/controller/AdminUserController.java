package com.taotao.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.dubbo.common.json.JSONObject;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbAdminUser;
import com.taotao.service.AdminUserService;

/**
 * 后台管理用户的Controller
 * 
 * @author zhaoyuan
 * @date 2019年11月14日 下午4:48:11
 */
@Controller
@RequestMapping("/login")
public class AdminUserController {

	@Autowired
	private AdminUserService adminUserService;

	@Value("${MANAGER_WEB_URL}")
	private String MANAGER_WEB_URL;

	/**
	 * 登录提交
	 * 
	 * @param adminUser
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult loginSubmit(TbAdminUser adminUser, HttpServletRequest request, HttpServletResponse response) {

		TaotaoResult serviceResult = adminUserService.loginSubmit(adminUser);

		// ModelAndView modelAndView = new ModelAndView();
		// JSONObject jsonObject = new JSONObject();
		TaotaoResult result = new TaotaoResult();

		// 获取session
		HttpSession session = request.getSession();
		Object sessionValue = session.getAttribute("USER");

		// 判断是否已经登录
		if (null != sessionValue) {
			/*
			 * modelAndView.addObject("MSG", "Session还没过期哟");
			 * modelAndView.setViewName("myjsp/loginfail");
			 * 
			 * //jsonObject.put("SESSIONISEXPIRE", true);
			 * 
			 * return modelAndView;
			 */

			result.setStatus(401);
			result.setData(MANAGER_WEB_URL);
			return result;
		}

		// 验证成功
		if (200 == serviceResult.getStatus()) {
			TbAdminUser adminUserSfy = (TbAdminUser) serviceResult.getData();

			// 存入Session
			session.setAttribute("USER", adminUserSfy);

			// modelAndView.addObject("MSG", adminUser.getUsername());
			// modelAndView.addObject("STAT", 200);
			// modelAndView.setViewName("index");

			// jsonObject.put("SESSIONISEXPIRE", false);
			// jsonObject.put("STAT", 200);

			// TaotaoResult result = new TaotaoResult(200, "登录成功", null);
			result.setStatus(200);
			result.setData(MANAGER_WEB_URL);
			// return result;
		}

		// 验证失败
		if (200 != serviceResult.getStatus()) {
			// modelAndView.addObject("MSG", result.getMsg());
			// modelAndView.addObject("STAT", 400);
			// modelAndView.setViewName("myjsp/loginfail");

			// jsonObject.put("SESSIONISEXPIRE", false);
			// jsonObject.put("MSG", result.getMsg());
			// jsonObject.put("STAT", 400);

			// TaotaoResult result = new TaotaoResult(400, result.getMsg(),
			// null);
			// return result;
			result.setStatus(400);
			result.setMsg(serviceResult.getMsg());
			result.setData(MANAGER_WEB_URL);
		}

		return result;
	}

	/**
	 * 后台管理用户退出
	 * 	请求方式这里必须用post，get方式跳转页面会报错
	 * @date 2019-11-16 16:56:58
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult logout(HttpServletRequest request) {
		TaotaoResult result = new TaotaoResult();

		try {
			// 获取Session
			HttpSession session = request.getSession();
			// 清空Session
			session.removeAttribute("USER");
			result.setStatus(200);
			result.setData(MANAGER_WEB_URL);
		} catch (Exception e) {
			result.setStatus(500);
			result.setMsg(e.getMessage());
			e.printStackTrace();
			return result;
		}
		return result;
	}

	/*
	 * @RequestMapping(value="/logout",method=RequestMethod.GET) public
	 * ModelAndView logout(HttpServletRequest request){ ModelAndView
	 * modelAndView = new ModelAndView();
	 * 
	 * try { //获取Session HttpSession session = request.getSession(); //清空Session
	 * session.removeAttribute("USER");
	 * 
	 * 
	 * modelAndView.addObject("MANAGER_WEB_URL", MANAGER_WEB_URL);
	 * modelAndView.setViewName("/login"); } catch (Exception e) {
	 * e.printStackTrace(); modelAndView.setViewName("/"); }
	 * 
	 * return modelAndView; }
	 */
}
