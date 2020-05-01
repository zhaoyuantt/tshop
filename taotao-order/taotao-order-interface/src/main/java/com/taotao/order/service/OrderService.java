package com.taotao.order.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.order.pojo.OrderInfo;

/**
 * 订单处理接口
 * @author zhaoyuan
 * @date 2019年5月6日 上午11:25:06
 */

public interface OrderService {
	
	/**
	 * 生成订单
	 * @param orderInfo
	 * @return
	 */
	TaotaoResult createOrder(OrderInfo orderInfo);
	
}
