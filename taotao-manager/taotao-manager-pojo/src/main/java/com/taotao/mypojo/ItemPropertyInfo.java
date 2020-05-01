package com.taotao.mypojo;

import com.taotao.pojo.TbItemProperty;

import java.io.Serializable;

/**
 * 用于描述商品属性
 * @author zhaoyuan
 * @date 2019,Nov 28 4:47 pm
 */
public class ItemPropertyInfo extends TbItemProperty implements Serializable {

    private String catName;

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}
