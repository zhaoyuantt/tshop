package com.taotao.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbAdminUserItem implements Serializable{
   
	private static final long serialVersionUID = 1L;

	private String id;

    private Long itemId;

    private String useradminId;

    private Date created;

    private Date updated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getUseradminId() {
        return useradminId;
    }

    public void setUseradminId(String useradminId) {
        this.useradminId = useradminId == null ? null : useradminId.trim();
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