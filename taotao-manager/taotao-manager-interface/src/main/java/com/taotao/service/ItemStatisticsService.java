package com.taotao.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * 获取商品相关的统计数量Service接口
 */
public interface ItemStatisticsService {

    /**
     * 获取商品相关的统计数量
     * @return
     */
    public TaotaoResult getItemStatisticsNum();

}
