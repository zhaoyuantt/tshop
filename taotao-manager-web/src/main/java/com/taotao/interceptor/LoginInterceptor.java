package com.taotao.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录拦截器 访问的url：http://127.0.0.1:8081
 * 参考资料：https://segmentfault.com/a/1190000015642264
 * 
 * @author zhaoyuan
 * @date 2019年11月14日 下午4:25:10
 */
public class LoginInterceptor implements HandlerInterceptor {

	/*
	 * @Value("${LOGIN_URL}") private String LOGIN_URL;
	 */

	/**
	 * 执行handler方法之前执行
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub

		// 不拦截登录请求，配置在springmvc.xml中
		/*String uri = request.getRequestURI();
		if ("/login".equals(uri) || "/login/submit".equals(uri)) {
			return true;
		}*/

		// 获取用户的Session的值
		HttpSession session = request.getSession();
		Object flag = session.getAttribute("USER");

		if (null == flag) {
			// 转发到登录页面
			//request.getRequestDispatcher("/login").forward(request, response);
			response.sendRedirect("/login");
			// 拦截
			return false;
		}

		// 放行
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
