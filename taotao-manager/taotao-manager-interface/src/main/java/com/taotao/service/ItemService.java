package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;

/**
 * 
 * @author zhaoyuan
 * @date 2019-02-17 11:12 AM
 */
public interface ItemService {

	/**
	 * 由商品Id查询商品信息
	 * 
	 * @param itemId
	 * @return
	 */
	public TbItem getItemById(long itemId);

	/**
	 * 分页查询商品列表
	 * 
	 * @param pageNum
	 *            当前页
	 * @param pageSize
	 *            每页显示多少条数据
	 * @return
	 */
	public EasyUIDataGridResult getItemList(Integer pageNum, Integer pageSize);

	/**
	 * @date 2019-11-19 14:58:46
	 * @param pageNum
	 * @param pageSize
	 * @param item
	 *            查询条件
	 * @return
	 */
	public EasyUIDataGridResult getItemList(Integer pageNum, Integer pageSize, TbItem item);

	/**
	 * 分页查询商品列表，关联后台管理用户
	 * 
	 * @date 2019-11-18 15:09:59
	 * @param pageNum
	 *            当前第几页
	 * @param pageSize
	 *            每页显示多少条数据
	 * @param adminUserId
	 *            后台管理用户的唯一标识
	 * @return
	 */
	public EasyUIDataGridResult getItemList(Integer pageNum, Integer pageSize, String adminUserId);

	/**
	 * 分页查询商品列表，关联后台管理用户
	 * 
	 * @date 2019-11-19 14:59:18
	 * @param pageNum
	 * @param pageSize
	 * @param adminUserId
	 * @param item
	 *            查询条件
	 * @return
	 */
	public EasyUIDataGridResult getItemList(Integer pageNum, Integer pageSize, String adminUserId, TbItem item);

	/**
	 * 商品添加
	 * 
	 * @param item
	 *            商品的pojo
	 * @param desc
	 *            商品的描述
	 * @param paramData
	 *            商品规格参数
	 * @return
	 */
	public TaotaoResult addItem(TbItem item, StringBuffer desc, String paramData);

	/**
	 * 添加商品，关联用户
	 * 
	 * @date 2019-11-17 18:49:06
	 * @param item
	 * @param desc
	 * @param paramData
	 * @param adminUserId
	 * @return
	 */
	public TaotaoResult addItem(TbItem item, StringBuffer desc, String paramData, String adminUserId);

	/**
	 * 根据商品Id查询商品描述
	 * 
	 * @param itemId
	 * @return
	 */
	public TbItemDesc getItemDescByItemId(long itemId);

	/**
	 * 由商品id更改商品状态 商品状态： 1：上架 2:下架 3:删除
	 * 
	 * @date 2019-11-21 10:25:50
	 * @param operType
	 *            1：上架 2：下架
	 * @param itemIds
	 * @return
	 */
	public TaotaoResult itemDownOrUpByItemIds(int operType, String itemIds);
	
	/**
	 * 由多个商品id删除商品信息，同时删除的商品关联信息
	 * 
	 * @date 2019-11-21 17:14:25
	 * @param itemIds
	 * @return
	 */
	public TaotaoResult deleteItemByItemIds(String itemIds);
	
	/**
	 * 编辑商品
	 * 
	 * @date 2019-11-22 23:41:20
	 * @param item
	 * @param desc
	 * @param paramData
	 * @return
	 */
	public TaotaoResult updataItem(TbItem item, StringBuffer desc, String paramData);
}
