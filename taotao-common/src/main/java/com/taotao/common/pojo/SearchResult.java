package com.taotao.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 商品索引搜索结果的Pojo
 * @author zhaoyuan
 * @date 2019年4月25日 下午7:26:14
 */
public class SearchResult implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<SearchItem> itemList;
	//共搜索到多少条商品文档
	private Long recordCount;
	//每页显示多少条商品文档
	private Long pageCount;
	
	public List<SearchItem> getItemList() {
		return itemList;
	}
	public void setItemList(List<SearchItem> itemList) {
		this.itemList = itemList;
	}
	public Long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(Long recordCount) {
		this.recordCount = recordCount;
	}
	public Long getPageCount() {
		return pageCount;
	}
	public void setPageCount(Long pageCount) {
		this.pageCount = pageCount;
	}
	
}
