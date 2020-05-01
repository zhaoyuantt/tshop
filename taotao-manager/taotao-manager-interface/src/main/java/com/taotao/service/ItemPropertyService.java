package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemProperty;

/**
 * 商品属性接口
 * @author zhaoyuan
 * @date 2019,Nov 28 3:40 pm
 */
public interface ItemPropertyService {

    /**
     * 商品属性列表
     * @param itemProperty   查询条件
     * @param pageNum   第几页
     * @param pageSize   每页显示多少条数据
     * @return
     */
    public EasyUIDataGridResult getItemPropertyList(TbItemProperty itemProperty,Integer pageNum,Integer pageSize);

    /**
     * 根据商品分类Id查询商品属性模板
     * @param itemCatId
     * @return
     */
    public TaotaoResult getItemPropertyByItemCatId(long itemCatId);

    /**
     * 插入商品属性
     * @param itemCatId
     * @param itemPropertyData
     * @return
     */
    public TaotaoResult insertItemProperty(long itemCatId,String itemPropertyData);

}
