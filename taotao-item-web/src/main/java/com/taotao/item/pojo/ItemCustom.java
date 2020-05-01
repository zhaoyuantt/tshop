package com.taotao.item.pojo;

import java.io.Serializable;

import com.taotao.pojo.TbItem;

/**
 * 商品展示工程的商品属性
 * @author zhaoyuan
 * @date 2019年5月1日 下午4:10:42
 */
public class ItemCustom extends TbItem implements Serializable{

	private static final long serialVersionUID = 1L;

	public ItemCustom(TbItem item) {
		super();
		//初始化属性
		this.setId(item.getId());
		this.setTitle(item.getTitle());
		this.setSellPoint(item.getSellPoint());
		this.setPrice(item.getPrice());
		this.setNum(item.getNum());
		this.setBarcode(item.getBarcode());
		this.setImage(item.getImage());
		this.setCid(item.getCid());
		this.setStatus(item.getStatus());
		this.setCreated(item.getCreated());
		this.setUpdated(item.getUpdated());
	}
	
	public String[] getImages(){
		if(null != this.getImage()&& !"".equals(this.getImage())){
			String str[] = this.getImage().split(",");
			return str;
		}
		return null;
	}

}
