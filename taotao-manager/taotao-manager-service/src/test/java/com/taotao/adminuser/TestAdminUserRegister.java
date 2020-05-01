package com.taotao.adminuser;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.DigestUtils;

import com.taotao.mapper.TbAdminUserMapper;
import com.taotao.mymapper.AdminUserItemMapper;
import com.taotao.pojo.TbAdminUser;
import com.taotao.pojo.TbItem;

/**
 * 后台管理系统用户注册
 * 
 * @author zhaoyuan
 * @date 2019年11月15日 下午5:09:03
 */
public class TestAdminUserRegister {

	/**
	 * 后台管理系统用户注册
	 */
	@Test
	public void testAuserRegister() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/applicationContext-dao.xml");
		TbAdminUserMapper adminUserMapper = applicationContext.getBean(TbAdminUserMapper.class);
		TbAdminUser adminUser = new TbAdminUser();
		String uuid_ = UUID.randomUUID().toString();
		String uuid = uuid_.replaceAll("-", "");
		adminUser.setId(uuid);
		adminUser.setUsername("zhaoyuan");
		String password = DigestUtils.md5DigestAsHex("1qaz2wsx,./".getBytes());
		System.out.println(password);
		adminUser.setPassword(password);
		adminUser.setPhone("15105166464");
		adminUser.setEmail("2270907801@qq.com");
		adminUser.setCreatedTime(new Date());
		adminUser.setUpdatedTime(new Date());
		/*
		 * int result = adminUserMapper.insert(adminUser); if (0 < result) {
		 * System.out.println("***********");
		 * System.out.println("后台管理系统用户注册成功，棒棒的"); }
		 */
	}

	/**
	 * @date 2019-11-16 22:30:57
	 * 测试后台管理用户发布的商品
	 */
	@Test
	public void testGetItemListByUserAdminId() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/applicationContext-dao.xml");
		AdminUserItemMapper adminUserItemMapper = applicationContext.getBean(AdminUserItemMapper.class);
		TbItem item = new TbItem();
		item.setTitle("索菲亚");
		//item.setSellPoint("suofeiya");
		List<TbItem> itemWithAdminUserList = adminUserItemMapper
				.getItemListByUserAdminIdWithCondition("65be0a14ca2a4fa8b408a014a8d65e2d",item);
		if (null != itemWithAdminUserList && itemWithAdminUserList.size() > 0) {
			for (TbItem tbItem : itemWithAdminUserList) {
				System.out.println(tbItem);
			}
		}
	}

}
