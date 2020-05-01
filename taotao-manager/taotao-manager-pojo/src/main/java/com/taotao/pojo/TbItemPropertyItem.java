package com.taotao.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbItemPropertyItem implements Serializable {
    private Long id;

    private Long itemId;

    private String itemPropertyValue;

    private Date created;

    private Date updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemPropertyValue() {
        return itemPropertyValue;
    }

    public void setItemPropertyValue(String itemPropertyValue) {
        this.itemPropertyValue = itemPropertyValue == null ? null : itemPropertyValue.trim();
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
}