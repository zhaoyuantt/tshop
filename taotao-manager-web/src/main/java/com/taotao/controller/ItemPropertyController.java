package com.taotao.controller;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemProperty;
import com.taotao.service.ItemPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 商品属性Controller
 *
 * @author zhaoyuan
 * @date 2019, Nov 28 4:07 pm
 */
@Controller
@RequestMapping("/item/property")
public class ItemPropertyController {

    @Autowired
    private ItemPropertyService itemPropertyService;


    /**
     * 商品属性初始化
     * @param pageNum
     * @param pageSize
     * @param itemProperty
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public EasyUIDataGridResult getPropertyList(@RequestParam(value = "page", defaultValue = "1") Integer pageNum,
                                                @RequestParam(value = "rows", defaultValue = "30") Integer pageSize,
                                                TbItemProperty itemProperty) {
        EasyUIDataGridResult result = itemPropertyService.getItemPropertyList(itemProperty, pageNum, pageSize);
        return result;
    }

    /**
     * 根据商品类目Id查询商品属性模板
     *  选择某个类目时，判断该类目是否已经添加属性模板
     * @param itemCatId
     * @return
     */
    @RequestMapping("/query/itemcatid/{itemCatId}")
    @ResponseBody
    public TaotaoResult getItemPropertyByItemCatList(@PathVariable long itemCatId){
        TaotaoResult suofeiyaResult = itemPropertyService.getItemPropertyByItemCatId(itemCatId);
        return suofeiyaResult;
    }

     /**
     * 商品属性插入
     * @param itemCatId
     * @param itemPropertyData
     * @return
     */
    @RequestMapping(value = "/save/{itemCatId}",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult insertItemProperty(@PathVariable long itemCatId,@RequestParam(value = "propertyData") String itemPropertyData){
        TaotaoResult result = itemPropertyService.insertItemProperty(itemCatId,itemPropertyData);
        return result;
    }


}
