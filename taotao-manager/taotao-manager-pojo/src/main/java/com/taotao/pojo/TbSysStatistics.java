package com.taotao.pojo;

import java.util.Date;

public class TbSysStatistics {
    private Byte id;

    private Long itemNum;

    private Long itemUpNum;

    private Integer itemDownNum;

    private Long itemParamNum;

    private Date jobTime;

    private Date jobDate;

    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

    public Long getItemNum() {
        return itemNum;
    }

    public void setItemNum(Long itemNum) {
        this.itemNum = itemNum;
    }

    public Long getItemUpNum() {
        return itemUpNum;
    }

    public void setItemUpNum(Long itemUpNum) {
        this.itemUpNum = itemUpNum;
    }

    public Integer getItemDownNum() {
        return itemDownNum;
    }

    public void setItemDownNum(Integer itemDownNum) {
        this.itemDownNum = itemDownNum;
    }

    public Long getItemParamNum() {
        return itemParamNum;
    }

    public void setItemParamNum(Long itemParamNum) {
        this.itemParamNum = itemParamNum;
    }

    public Date getJobTime() {
        return jobTime;
    }

    public void setJobTime(Date jobTime) {
        this.jobTime = jobTime;
    }

    public Date getJobDate() {
        return jobDate;
    }

    public void setJobDate(Date jobDate) {
        this.jobDate = jobDate;
    }
}