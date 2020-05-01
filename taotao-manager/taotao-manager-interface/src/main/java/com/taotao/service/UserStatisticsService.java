package com.taotao.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * 用户相关的统计接口
 * @author zhaoyuan
 * @date 2019，Nov 27 3：40 pm
 */
public interface UserStatisticsService {

    /**
     * 获取用户相关的统计数量
     * @return
     */
    public TaotaoResult getUserStatisticsNum();

}
