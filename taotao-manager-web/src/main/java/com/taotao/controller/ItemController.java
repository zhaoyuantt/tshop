package com.taotao.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.taotao.service.ShopThreadService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbAdminUser;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

/**
 * 商品管理的Controller
 * 
 * @author zhaoyuan
 * @date 2019-02-17 12:46 PM
 */
@Controller
@RequestMapping("/item")
public class ItemController {

	private static final Logger log = Logger.getLogger(ItemController.class);

	@Autowired
	private ItemService itemService;
	@Autowired
	private ShopThreadService shopThreadService;

	@Value("${SUPER_ADMIN_USERNAME}")
	private String SUPER_ADMIN_USERNAME;

	/**
	 * 由商品Id查询商品信息
	 * 
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable long itemId) {
		TbItem item = itemService.getItemById(itemId);
		return item;
	}

	/**
	 * 分页查询商品列表
	 * 
	 * @param page
	 *            当前页
	 * @param rows
	 *            每页显示多少条数据
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(HttpServletRequest request, Integer page, Integer rows,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "sellPoint", required = false) String sellPoint) {
		/*
		 * before EasyUIDataGridResult result = itemService.getItemList(page,
		 * rows); return result;
		 */

		// Edit on 2019-11-18 15:28:18
		// 获取Session
		HttpSession session = request.getSession();
		Object sessionVal = session.getAttribute("USER");
		String adminUserId = "";
		TbAdminUser adminUser = null;
		if (null != sessionVal) {
			adminUser = (TbAdminUser) sessionVal;
			adminUserId = adminUser.getId();
		}

		TbItem item = new TbItem();
		if (null != title && !"".equals(title)) {
			item.setTitle(title);
		}
		if (null != sellPoint && !"".equals(sellPoint)) {
			item.setSellPoint(sellPoint);
		}

		// 超级管理员查询所有商品
		if (SUPER_ADMIN_USERNAME.equals(adminUser.getUsername())) {
			// EasyUIDataGridResult result = itemService.getItemList(page,
			// rows);
			EasyUIDataGridResult result = itemService.getItemList(page, rows, item);
			if (null != title && !"".equals(title)) {
				result.setPageSearchInputValue_title(title);
			}
			if (null != sellPoint && !"".equals(sellPoint)) {
				result.setPageSearchInputValue_sellPoint(sellPoint);
			}
			return result;
		}

		// 普通管理员查询各自发布的商品
		// EasyUIDataGridResult result = itemService.getItemList(page, rows,
		// adminUserId);
		EasyUIDataGridResult result = itemService.getItemList(page, rows, adminUserId, item);
		if (null != title && "".equals(title)) {
			result.setPageSearchInputValue_title(title);
		}
		if (null != sellPoint && "".equals(sellPoint)) {
			result.setPageSearchInputValue_sellPoint(sellPoint);
		}

		return result;
		// End edit
	}

	/**
	 * 商品添加
	 * 
	 * @param item
	 *            商品的pojo
	 * @param desc
	 *            商品描述
	 * @param paramData
	 *            商品规格参数
	 * @param request
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult addItem(TbItem item, StringBuffer desc, @RequestParam(value = "itemParams") String paramData,
			HttpServletRequest request) {
		// Edit on 2019-11-17 19:18:26
		TaotaoResult result = null;
		// 获取Session
		HttpSession session = request.getSession();
		Object adminUser = session.getAttribute("USER");
		if (null != adminUser) {
			TbAdminUser adminUserSFY = (TbAdminUser) adminUser;
			result = itemService.addItem(item, desc, paramData, adminUserSFY.getId());
		}
		// End edit

		// before
		// TaotaoResult result = itemService.addItem(item, desc, paramData);
		// return result;

		return result;
	}

	/**
	 * 由商品Id上架or下架商品
	 * 
	 * @date 2019-11-21 11:29:09
	 * @param itemIds
	 * @param operType
	 *            1:上架 2：删除
	 * @return
	 */
	@RequestMapping("/change/{operType}")
	@ResponseBody
	public TaotaoResult downItemByItemId(@PathVariable int operType,
			@RequestParam(value = "ids", required = true) String itemIds) {
		if (1 == operType) {
			TaotaoResult result = itemService.itemDownOrUpByItemIds(operType, itemIds);
			return result;
		}

		if (2 == operType) {
			TaotaoResult result = itemService.itemDownOrUpByItemIds(operType, itemIds);
			return result;
		}

		return TaotaoResult.build(400, "无效操作类型");

	}

	/**
	 * 由多个商品id删除商品信息
	 * 
	 * @date 2019-11-21 17:42:53
	 * @param itemIds
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public TaotaoResult deleteItemByItemIds(@RequestParam(value = "ids", required = true) String itemIds) {
		TaotaoResult result = itemService.deleteItemByItemIds(itemIds);
		return result;
	}
	
	/**
	 * 编辑商品
	 * 
	 * @date 2019-11-23 00:03:08
	 * @param item
	 * @param desc
	 * @param paramData
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult updateItem(TbItem item, StringBuffer desc, @RequestParam(value = "itemParams") String paramData){
		try {
			TaotaoResult result = itemService.updataItem(item, desc, paramData);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, e.getMessage());
		}
	}

	@RequestMapping("/export")
	@ResponseBody
	public TaotaoResult showDataExportToExcel(){
		TaotaoResult result = shopThreadService.itemDataBetchExportToExcel();
		return result;
	}

}
