package com.taotao.order.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.jedis.JedisClient;
import com.taotao.mapper.TbOrderItemMapper;
import com.taotao.mapper.TbOrderMapper;
import com.taotao.mapper.TbOrderShippingMapper;
import com.taotao.order.pojo.OrderInfo;
import com.taotao.order.service.OrderService;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;

/**
 * 订单处理接口实现
 * @author zhaoyuan
 * @date 2019年5月6日 上午11:28:16
 */

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private TbOrderMapper orderMapper;
	@Autowired
	private TbOrderItemMapper orderItemMapper;
	@Autowired
	private TbOrderShippingMapper orderShippingMapper;
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${ORDER_ID_GEN_KEY}")
	private String ORDER_ID_GEN_KEY;
	@Value("${ORDER_KEY_BEGIN}")
	private String ORDER_KEY_BEGIN;
	@Value("${ORDER_ITEM_ID_GEN_KEY}")
	private String ORDER_ITEM_ID_GEN_KEY;
	
	@Override
	public TaotaoResult createOrder(OrderInfo orderInfo) {
		//******************主订单*********************
		//生成订单Id
		if(!jedisClient.exists(ORDER_ID_GEN_KEY)){
			jedisClient.set(ORDER_ID_GEN_KEY, ORDER_KEY_BEGIN);
		}
		String orderId = jedisClient.incr(ORDER_ID_GEN_KEY).toString();
		
		//补全属性
		orderInfo.setOrderId(orderId);
		orderInfo.setPostFee("0");   //免邮
		orderInfo.setStatus(1);   //1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
		orderInfo.setCreateTime(new Date());
		orderInfo.setUpdateTime(new Date());
		
		//插入数据库
		orderMapper.insert(orderInfo);
		
		//******************订单明细*********************
		List<TbOrderItem> orderItemList = orderInfo.getOrderItems();
		for (TbOrderItem orderItem : orderItemList) {
			//补全属性
			String orderItemId = jedisClient.incr(ORDER_ITEM_ID_GEN_KEY).toString();
			orderItem.setId(orderItemId);
			orderItem.setOrderId(orderId);
			
			//插入数据库
			orderItemMapper.insert(orderItem);
		}
		
		//******************订单物流*********************
		TbOrderShipping orderShipping = orderInfo.getOrderShipping();
		orderShipping.setOrderId(orderId);
		
		//插入数据库
		orderShippingMapper.insert(orderShipping);
		
		//返回订单号
		return TaotaoResult.ok(orderId);
	}

}
