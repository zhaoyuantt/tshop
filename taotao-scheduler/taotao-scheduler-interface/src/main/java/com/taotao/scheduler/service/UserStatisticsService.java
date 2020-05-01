package com.taotao.scheduler.service;

/**
 * 用户相关的统计接口
 * @author zhaoyuan
 * @date 2019，Nov 27 3：40 pm
 */
public interface UserStatisticsService {

    /**
     * 用户数量的统计,每一小时执行一次
     */
    public void addUserStatisticsNum();
}
