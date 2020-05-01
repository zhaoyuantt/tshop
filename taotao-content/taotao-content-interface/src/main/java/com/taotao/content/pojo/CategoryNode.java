package com.taotao.content.pojo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 门户首页商品分类节点
 * @author zhaoyuan
 * @date 2019年5月15日 上午11:50:53
 */
public class CategoryNode implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("n")
	private String name;
	
	@JsonProperty("u")
	private String url;
	
	@JsonProperty("i")
	private List<?> cat;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<?> getCat() {
		return cat;
	}

	public void setCat(List<?> cat) {
		this.cat = cat;
	}

}
