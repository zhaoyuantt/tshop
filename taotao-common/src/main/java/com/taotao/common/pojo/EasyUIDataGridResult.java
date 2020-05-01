package com.taotao.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * EayuUI的DataGrid的响应pojo
 * {total:”2”,rows:[{“id”:”1”,”name”:”张三”},{“id”:”2”,”name”:”李四”}]}
 * @author zhaoyuan
 * @date 2019-02-17 5:11 PM
 */
public class EasyUIDataGridResult implements Serializable{
	
	private static final long serialVersionUID = 1L;

	//响应的总记录数
	private Long total;
	
	//响应的数据
	private List<?> rows;
	
	//给页面响应商品title字段值 Add on 2019-11-19 14:54:41
	private String pageSearchInputValue_title;
	
	//给页面响应商品sellPoint字段值 Add on 2019-11-19 14:54:49
	private String pageSearchInputValue_sellPoint;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public String getPageSearchInputValue_sellPoint() {
		return pageSearchInputValue_sellPoint;
	}

	public void setPageSearchInputValue_sellPoint(String pageSearchInputValue_sellPoint) {
		this.pageSearchInputValue_sellPoint = pageSearchInputValue_sellPoint;
	}
	
	public String getPageSearchInputValue_title() {
		return pageSearchInputValue_title;
	}

	public void setPageSearchInputValue_title(String pageSearchInputValue_title) {
		this.pageSearchInputValue_title = pageSearchInputValue_title;
	}
	
}
