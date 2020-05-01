package com.taotao.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbUserStatistics implements Serializable {
    private Integer id;

    private Short adminUserNum;

    private Long userNum;

    private Date jobTime;

    private Date jobDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getAdminUserNum() {
        return adminUserNum;
    }

    public void setAdminUserNum(Short adminUserNum) {
        this.adminUserNum = adminUserNum;
    }

    public Long getUserNum() {
        return userNum;
    }

    public void setUserNum(Long userNum) {
        this.userNum = userNum;
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