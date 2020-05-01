package com.taotao.item;

import com.taotao.mymapper.ItemPropertyMapper;
import com.taotao.mypojo.ItemPropertyInfo;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * 商品相关的测试类
 * @author zhaoyuan
 * @date 2019,Nov 28 4:56 pm
 */
public class TestItemRel {

    @Test
    public void testItemPropertyList(){
        ApplicationContext applicationContext = new
                ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
        ItemPropertyMapper itemPropertyMapper = applicationContext.getBean(ItemPropertyMapper.class);
        List<ItemPropertyInfo> propertyList = itemPropertyMapper.getItemPropertyList();
        if(null != propertyList && 0 < propertyList.size()){
            for (int i = 0; i < propertyList.size(); i++) {
                ItemPropertyInfo itemPropertyInfo =  propertyList.get(i);
                System.out.println(itemPropertyInfo.getId());
                System.out.println(itemPropertyInfo.getItemCatId());
                System.out.println(itemPropertyInfo.getItemProperty());
                System.out.println(itemPropertyInfo.getCreated().toLocaleString());
                System.out.println(itemPropertyInfo.getUpdated().toLocaleString());
                System.out.println(itemPropertyInfo.getCatName());
            }
        }
    }

}
