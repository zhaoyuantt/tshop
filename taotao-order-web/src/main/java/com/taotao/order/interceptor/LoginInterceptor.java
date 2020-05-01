package com.taotao.order.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.UserService;
import com.taotao.utils.CookieUtils;
import com.taotao.utils.HttpClientUtil;

/**
 * 判断用户是否登录的Controller
 * @author zhaoyuan
 * @date 2019年5月4日 下午8:59:56
 */
public class LoginInterceptor implements HandlerInterceptor{

	@Autowired
	private UserService UserService;
	
	@Value("${TOKEN_KEY}")
	private String TOKEN_KEY;
	@Value("${SSO_LOGIN_URL}")
	private String SSO_LOGIN_URL;
	
	
	/**
	 * 执行handler方法之前执行
	 * 返回 true 放行     返回 false 拦截 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Boolean flag = false;
		//从cookie中token
		String token = CookieUtils.getCookieValue(request,TOKEN_KEY);
		//取不到
		if(StringUtils.isBlank(token)){
			//定向到登录页面，需要把当前请求的url作为参数传递给sso
			String url = request.getRequestURL().toString();
			response.sendRedirect(SSO_LOGIN_URL+"?url="+url);
			//HttpClientUtil.doGet(url);
			return flag;
		}
		
		//取到
		//通过sso系统判断用户是否登录
		TaotaoResult result = UserService.getUserByToken(token);
		if(200 != result.getStatus()){
			//定向到登录页面，需要把当前请求的url作为参数传递给sso
			String url = request.getRequestURL().toString();
			response.sendRedirect(SSO_LOGIN_URL+"?url="+url);
			return flag;
		}
		if(200 == result.getStatus()){
			//取用户信息，放到request域中
			TbUser user = (TbUser) result.getData();
			request.setAttribute("user",user);
			flag = true;
		}
	
		return flag;
	}

	/**
	 * handler执行之后，返回ModelAndView之前
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	/**
	 * 返回ModelAndView之后
	 * 处理异常
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
