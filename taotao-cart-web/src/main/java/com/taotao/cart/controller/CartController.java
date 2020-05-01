package com.taotao.cart.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import com.taotao.utils.CookieUtils;
import com.taotao.utils.JsonUtils;

/**
 * 购物车管理的Controller
 * @author zhaoyuan
 * @date 2019年5月4日 下午2:08:53
 */

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private ItemService ItemService;
	
	@Value("${CART_KEY}")
	private String CART_KEY;
	@Value("${CART_EXPIRE}")
	private int CART_EXPIRE;
	
	/**
	 * 购物车添加
	 * @param itemId
	 * @param buyNum
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/add/{itemId}")
	public String addItamCart(@PathVariable Long itemId,
			@RequestParam(value="num",defaultValue = "1") Integer buyNum,
			HttpServletRequest request,
			HttpServletResponse response){
		//取购物车列表
		List<TbItem> cartList = getItemCartList(request);
		
		//判断商品在购物车中是否存在
		//是否存在的标志位
		Boolean flag = false;
		
		for (TbItem tbItem : cartList) {
			//存在数量相加
			if(tbItem.getId() == itemId.longValue()){
				tbItem.setNum(tbItem.getNum()+buyNum);
				flag = true;
				break;
			}
		}
		//不存在
		if(!flag){
			TbItem item = ItemService.getItemById(itemId);
			//购买数量
			item.setNum(buyNum);
			//处理图片
			String image = item.getImage();
			if(StringUtils.isNoneBlank(image)){
				String[] imageArr = image.split(",");
				item.setImage(imageArr[0]);
				cartList.add(item);
			}
		}
		
		//写进cookie
		String cookieJson = JsonUtils.objectToJson(cartList);
		CookieUtils.setCookie(request, response, CART_KEY, cookieJson, CART_EXPIRE, true);
		
		return "cartSuccess";
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
	 * 展示购物车列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/cart",method=RequestMethod.GET)
	public String showCartListPage(HttpServletRequest request){
		List<TbItem> itemCartList = getItemCartList(request);
		request.setAttribute("cartList", itemCartList);
		return "cart";
	}
	
	/**
	 * 更新购物车数量
	 * @param itemId
	 * @param num
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/update/num/{itemId}/{num}",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult updateCartItemNum(@PathVariable Long itemId,@PathVariable Integer num,
			HttpServletRequest request,HttpServletResponse response){
		try {
			//从cookie中取购物车列表
			List<TbItem> cartList = getItemCartList(request);
			
			for (TbItem item : cartList) {
				if(item.getId() == itemId.longValue()){
					//更新购买数量
					item.setNum(num);
					break;
				}
			}
			
			//写进cookie
			String cookieJson = JsonUtils.objectToJson(cartList);
			CookieUtils.setCookie(request, response, CART_KEY, cookieJson, CART_EXPIRE, true);
			
			return TaotaoResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, "更新购物车异常");
		}
	}
	
	
	/**
	 * 删除购物车商品
	 * @param itemId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/delete/{itemId}",method=RequestMethod.GET)
	public String deleteItemCart(@PathVariable Long itemId,
			HttpServletRequest request,HttpServletResponse response){
		try {
			//从cookie中取购物车列表
			List<TbItem> cartList = getItemCartList(request);
			for (TbItem item : cartList) {
				if(item.getId() == itemId.longValue()){
					cartList.remove(item);
					break;
				}
			}
			
			//重新写入cookie
			String cookieJson = JsonUtils.objectToJson(cartList);
			CookieUtils.setCookie(request, response, CART_KEY, cookieJson, CART_EXPIRE, true);
			
			//重定向到购物车列表页面
			return "redirect:/cart/cart.html";
		} catch (Exception e) {
			e.printStackTrace();
			return "删除购物车商品出错";
		}
		
	}
	
	
}
