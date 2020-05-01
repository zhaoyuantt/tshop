package com.taotao.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.UserService;
import com.taotao.utils.CookieUtils;
import com.taotao.utils.JsonUtils;

/**
 * 用户请求的Controller
 * @author zhaoyuan
 * @date 2019年5月3日 下午3:04:08
 */
@Controller
@RequestMapping("/user")
public class UserController {

	private static final Logger log = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@Value("${TOKEN_KEY}")
	private String TOKEN_KEY;
	
	/**
	 * 检查用户注册数据是否可用
	 * @param parma
	 * @param type
	 * @return
	 */
	@RequestMapping(value="/check/{param}/{type}",method=RequestMethod.GET)
	@ResponseBody
	public TaotaoResult checkUserDate(@PathVariable String param,@PathVariable int type){
		try {
			TaotaoResult result = userService.checkUserDate(param, type);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, "系统出现异常",e.getMessage());
		}
	}
	
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/register",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult userRegister(TbUser user){
		try {
			TaotaoResult result = userService.userRegister(user);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, "系统出现异常",e.getMessage());
		}
	}
	
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult userLogin(String username,String password,
			HttpServletRequest request,HttpServletResponse response){
		try {
			TaotaoResult result = userService.userLogin(username, password);
			//登录成功后写cookie
			if (result.getStatus() == 200) {
				//把token写入cookie
				CookieUtils.setCookie(request, response, TOKEN_KEY, result.getData().toString());
				log.info(username+" :登录成功，token已写进cookie");
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, "登录异常");
		}
	}
	
	/**
	 * 根据token查询用户信息
	 * @param token
	 * @param callback
	 * @return
	 */
	@RequestMapping(value="/token/{token}",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getUserByToken(@PathVariable String token,String callback){
		try {
			TaotaoResult result = userService.getUserByToken(token);
			
			//处理json请求
			if(null != callback){
				return callback+"("+JsonUtils.objectToJson(result)+");";
			}
			
			return JsonUtils.objectToJson(result);
		} catch (Exception e) {
			e.printStackTrace();
			return "查询异常";
		}
	}
	
	/**
	 * 用户退出
	 * @param token
	 * @param callback   json请求回调
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/logout/{token}",method=RequestMethod.GET)
	@ResponseBody
	public String userLogout(@PathVariable String token,String callback,
			HttpServletRequest request,HttpServletResponse response){
		TaotaoResult result = userService.userLogout(token);
		//删除用户登录的cookie
		if(200 == result.getStatus()){
			CookieUtils.deleteCookie(request, response, TOKEN_KEY);
		}
		if(null != callback){
			return callback+"("+JsonUtils.objectToJson(result)+");";
		}
		return JsonUtils.objectToJson(result);
	}
	
}
