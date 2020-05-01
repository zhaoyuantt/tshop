package com.taotao.order.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.order.pojo.OrderInfo;
import com.taotao.order.service.OrderService;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.UserService;
import com.taotao.utils.CookieUtils;
import com.taotao.utils.JsonUtils;

/**
 * 订单确认页面
 * @author zhaoyuan
 * @date 2019年5月4日 下午8:05:32
 */

@Controller
@RequestMapping("/order")
public class OrderCartController {
	
	private static final Logger log = Logger.getLogger(OrderCartController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private OrderService orderService;
	
	@Value("${CART_KEY}")
	private String CART_KEY;
	@Value("${TOKEN_KEY}")
	private String TOKEN_KEY;
	
	/**
	 * 订单确认页面显示
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/order-cart",method=RequestMethod.GET)
	public String showOrderCartPage(HttpServletRequest request){
		//存cookie中取用户信息
		//方式一：
		//String cookieToken = CookieUtils.getCookieValue(request, TOKEN_KEY);
		//TaotaoResult userResult = userService.getUserByToken(cookieToken);
		//TbUser user = (TbUser) userResult.getData();
		TbUser user = (TbUser) request.getAttribute("user");
		log.info("处理订单相关逻辑： "+user.getUsername());
		//由用户信息取用户收货地址列表
		//?????
		
		
		//从cookie中取购物车相关信息，展示购物车中商品
		List<TbItem> itemCartList = getItemCartList(request);
		request.setAttribute("cartList", itemCartList);
		
		//返回逻辑视图
		return "order-cart";
	}
	
	/**
	 * 从cookie中取购物车列表
	 * @param request
	 * @return
	 */
	private List<TbItem> getItemCartList(HttpServletRequest request){
		String cookieJson = CookieUtils.getCookieValue(request, CART_KEY, true);
		if(null == cookieJson){
			return new ArrayList<>();
		}
		List<TbItem> cookieCartList = JsonUtils.jsonToList(cookieJson, TbItem.class);
		return cookieCartList;
	}
	
	
	/**
	 * 生成订单
	 * @param orderInfo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="create",method=RequestMethod.POST)
	public String createOrder(OrderInfo orderInfo,Model model){
		try {
			TaotaoResult orderResult = orderService.createOrder(orderInfo);
			
			String orderId = orderResult.getData().toString();
			model.addAttribute("orderId", orderId);
			model.addAttribute("payment", orderInfo.getPayment());
			DateTime dateTime = new DateTime();
			dateTime = dateTime.plusDays(3);
			//订单预计送货时间
			String downTime = dateTime.toString("yyyy-MM-dd");
			model.addAttribute("date", downTime);
			
			//返回逻辑视图
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "订单生成异常";
		}
	}
	
}
