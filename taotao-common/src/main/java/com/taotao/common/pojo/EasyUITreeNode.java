package com.taotao.common.pojo;

import java.io.Serializable;

/**
 * EasyUI Tree控件响应的pojo
 * @author zhaoyuan
 * @date 2019-02-22 10：03 PM
 */
public class EasyUITreeNode implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/* Id */
	private long id;
	/* 名称 */
	private String text;
	/* 节点状态，'open' 或 'closed'，默认：'open'。如果为'closed'的时候，将不自动展开该节点 */
	private String state;
	/* 该节点是否被选中 */
	private boolean checked;
	
	public boolean getChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
