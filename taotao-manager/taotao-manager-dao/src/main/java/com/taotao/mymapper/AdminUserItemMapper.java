package com.taotao.mymapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.taotao.pojo.TbItem;

/**
 * 后台管理用户与商品的Mapper接口
 * 
 * @author zhaoyuan
 * @date 2019年11月16日 下午9:55:37
 */
public interface AdminUserItemMapper {

	/**
	 * 查询某个用户发布的商品，查询条件title,sellPoint
	 * 
	 * @date 2019-11-16 22:32:05
	 * @param adminUserId
	 * @param item
	 * @return
	 */
	public List<TbItem> getItemListByUserAdminIdWithCondition(@Param("adminUserId") String adminUserId,@Param("item") TbItem item);
	
	/**
	 * 无查询条件
	 * @param adminUserId
	 * @return
	 */
	public List<TbItem> getItemListByUserAdminId(@Param("adminUserId") String adminUserId);

}
