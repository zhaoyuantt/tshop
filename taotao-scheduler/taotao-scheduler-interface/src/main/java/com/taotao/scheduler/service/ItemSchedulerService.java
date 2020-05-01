package com.taotao.scheduler.service;

/**
 * 商品相关信息定时统计接口
 * @author zhaoyuan
 * @date Nov 26,2019 10:22 am
 */
public interface ItemSchedulerService {

    /**
     * 统计商品相关的数量
     * 每1小时统计（执行）一次
     */
    public void addItemRelationNum();

}
