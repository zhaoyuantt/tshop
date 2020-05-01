package com.taotao.common.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品规格参数pojo
 * @author zhaoyuan
 * @date 2019年5月13日 下午4:27:12
 */
public class ItemParamInfo  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String itemCatName;

	private Long id;

    private Long itemCatId;

    private Date created;

    private Date updated;

    private String paramData;

	public String getItemCatName() {
		return itemCatName;
	}

	public void setItemCatName(String itemCatName) {
		this.itemCatName = itemCatName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getItemCatId() {
		return itemCatId;
	}

	public void setItemCatId(Long itemCatId) {
		this.itemCatId = itemCatId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getParamData() {
		return paramData;
	}

	public void setParamData(String paramData) {
		this.paramData = paramData;
	}
    
}
