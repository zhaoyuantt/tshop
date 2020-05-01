package com.taotao.shopthread;

import com.taotao.service.ShopThreadService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 商品线程测试类
 * @author zhaoyuan
 * @date 2019，Dec 2 2:48 pm
 */
public class TestShopThread {

    @Test
    public void testItemDataExportExcel(){
        ApplicationContext applicationContext = new
                ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        ShopThreadService shopThreadService = applicationContext.getBean(ShopThreadService.class);
        shopThreadService.itemDataExportExcel();
    }


}
