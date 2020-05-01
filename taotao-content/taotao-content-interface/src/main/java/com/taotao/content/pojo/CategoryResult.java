package com.taotao.content.pojo;

import java.io.Serializable;
import java.util.List;

/**
 *@author zhaoyuan
 *@date 2019年5月15日 下午12:44:04
 */
public class CategoryResult implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<?> data;

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}
	
}
