package com.taotao.pagehelper;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;

/**
 * PageHelper分页插件的使用方法
 * @author zhaoyuan
 * @date 2019-02-17 5:43 PM
 */
public class TestPageHelper {

	@Test
	public void testPageHelper(){
		try {
			//1.在执行查询之前配置分页条件
			PageHelper.startPage(1, 10);
			//2.执行查询
			//初始化spring容器
			ApplicationContext application = new 
					ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
			TbItemMapper  itemMapper = application.getBean(TbItemMapper.class);
			TbItemExample example = new TbItemExample();
			List<TbItem> items = itemMapper.selectByExample(example);
			//3.取分页信息
			PageInfo<TbItem> pageInfo = new PageInfo<>(items);
			System.out.println("总记录数："+pageInfo.getTotal());
			System.out.println("总页数:"+pageInfo.getPages());
			System.out.println("返回的记录数:"+items.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
