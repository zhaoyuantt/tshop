package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemPropertyMapper;
import com.taotao.mymapper.ItemPropertyMapper;
import com.taotao.mypojo.ItemPropertyInfo;
import com.taotao.pojo.TbItemProperty;
import com.taotao.pojo.TbItemPropertyExample;
import com.taotao.service.ItemPropertyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 商品属性接口实现
 * @author zhaoyuan
 * @date 2019,Nov 28 3:46 pm
 */
@Service
public class ItemPropertyServiceImpl implements ItemPropertyService {

    @Autowired
    private TbItemPropertyMapper itemPropertyMapper;
    @Autowired
    private ItemPropertyMapper myItemPropertyMapper;

    @Override
    public EasyUIDataGridResult getItemPropertyList(TbItemProperty itemProperty, Integer pageNum, Integer pageSize) {
        //给PageHelper设置查询条件
        PageHelper.startPage(pageNum,pageSize);

        //执行查询
        //TbItemPropertyExample itemPropertyExample = new TbItemPropertyExample();
        //List<TbItemProperty> itemPropertyList = itemPropertyMapper.selectByExample(itemPropertyExample);
        List<ItemPropertyInfo> itemPropertyList = myItemPropertyMapper.getItemPropertyList();

        //封装返回结果
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setRows(itemPropertyList);
        PageInfo<ItemPropertyInfo> pageInfo = new PageInfo<>(itemPropertyList);
        result.setTotal(pageInfo.getTotal());

        return result;
    }

    @Override
    public TaotaoResult getItemPropertyByItemCatId(long itemCatId) {
        if ((null == (Long) itemCatId)) {
            throw  new RuntimeException("商品类目Id无值");
        }
        
        TbItemPropertyExample itemPropertyExample = new TbItemPropertyExample();
        TbItemPropertyExample.Criteria itemPropertyCriteria = itemPropertyExample.createCriteria();
        itemPropertyCriteria.andItemCatIdEqualTo(itemCatId);
        List<TbItemProperty> propertyList = itemPropertyMapper.selectByExample(itemPropertyExample);

        if(null == propertyList || 0 == propertyList.size()){
            return TaotaoResult.ok();
        }

        return TaotaoResult.ok(propertyList.get(0));
    }

    @Override
    public TaotaoResult insertItemProperty(long itemCatId,String itemPropertyData) {
        //校验参数
        if(StringUtils.isEmpty(itemPropertyData)){
            throw new RuntimeException("插入属性分组时，属性分组为空");
        }
        if(null == (Long)itemCatId){
            throw new RuntimeException("插入属性分组时，商品类目Id为空");
        }

        //创建对象
        TbItemProperty itemProperty = new TbItemProperty();
        itemProperty.setItemCatId(itemCatId);
        itemProperty.setItemProperty(itemPropertyData);
        itemProperty.setCreated(new Date());
        itemProperty.setUpdated(new Date());

        //插入数据库
        int iResult= itemPropertyMapper.insert(itemProperty);

        if(0 == iResult){
            return TaotaoResult.build(500,"插入商品属性，未知异常");
        }

        return TaotaoResult.ok();
    }
}
