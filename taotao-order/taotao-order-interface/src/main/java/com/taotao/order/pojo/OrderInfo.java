package com.taotao.order.pojo;

import java.io.Serializable;
import java.util.List;

import com.taotao.pojo.TbOrder;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;

/**
 * 订单的pojo
 * @author zhaoyuan
 * @date 2019年5月6日 上午10:57:21
 */
public class OrderInfo extends TbOrder implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/* 订单明细 */
	private List<TbOrderItem> orderItems;
	/*订单物流*/
	private TbOrderShipping orderShipping;
	
	public List<TbOrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<TbOrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public TbOrderShipping getOrderShipping() {
		return orderShipping;
	}
	public void setOrderShipping(TbOrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}
	
}
