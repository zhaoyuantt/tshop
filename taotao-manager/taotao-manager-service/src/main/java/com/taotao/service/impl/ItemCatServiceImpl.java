package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.service.ItemCatService;

/**
 * 商品类目的接口实现
 * 
 * @author zhaoyuan
 * @Date 2019-02-22 10:34 PM
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;

	@Override
	public List<EasyUITreeNode> getItemCatList(long parentId) {
		// 根据父节点id查询子节点列表
		TbItemCatExample example = new TbItemCatExample();
		// 设置查询条件
		Criteria criteria = example.createCriteria();
		// 设置parentid
		criteria.andParentIdEqualTo(parentId);
		// 执行查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		// 转换成EasyUITreeNode列表
		List<EasyUITreeNode> resultList = new ArrayList<>();
		for (TbItemCat tbItemCat : list) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbItemCat.getId());
			node.setText(tbItemCat.getName());
			// 如果节点下有子节点“closed”，如果没有子节点“open”
			node.setState(tbItemCat.getIsParent() ? "closed" : "open");
			// 添加到节点列表
			resultList.add(node);
		}
		return resultList;
	}

	@Override
	public TaotaoResult createItemCategory(String name, long parentId) {
		TbItemCat itemCat = new TbItemCat();
		// 补全属性
		itemCat.setName(name);
		itemCat.setParentId(parentId);
		itemCat.setIsParent(false); // 该类目是否为父类目，1为true，0为false
		itemCat.setStatus(1); // 可选值:1(正常),2(删除)
		itemCat.setSortOrder(1);
		itemCat.setUpdated(new Date());
		itemCat.setCreated(new Date());
		// 插入
		itemCatMapper.insert(itemCat);

		// 判断父节点的状态，如果父节点为父节点，需要置为非节点，即 isparent = 0
		TbItemCat itemCat2 = itemCatMapper.selectByPrimaryKey(parentId);
		if (!itemCat2.getIsParent()) {
			itemCat2.setIsParent(true);
			itemCatMapper.updateByPrimaryKey(itemCat2);
		}
		return TaotaoResult.ok(itemCat);
	}

	@Override
	public TaotaoResult updateItemCategoryByCid(String name, long cid) {
		TbItemCat itemCat = itemCatMapper.selectByPrimaryKey(cid);
		// TbItemCat itemCat = new TbItemCat();
		// itemCat.setId(cid);
		itemCat.setName(name);
		itemCatMapper.updateByPrimaryKey(itemCat);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult deleteItemCategoryByCid(long cid, long parentId) {
		List<Long> cidList = getCidListbyCid(cid);
		for (Long itemCatId : cidList) {
			itemCatMapper.deleteByPrimaryKey(itemCatId);
		}
		if (0 == cidList.size()) {
			itemCatMapper.deleteByPrimaryKey(cid);
		}

		return TaotaoResult.ok();
	}

	/**
	 * 获取某节点下所有叶子节点的cid
	 * 
	 * @param parentId
	 * @return
	 */
	public List<Long> getCidListbyCid(Long cid) {
		// 查询此类目下的叶子节点
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(cid);
		List<TbItemCat> itemCatlist = itemCatMapper.selectByExample(example);

		ArrayList<Long> cidList = new ArrayList<>();

		if (null != itemCatlist && itemCatlist.size() > 0) {
			for (TbItemCat itemCat : itemCatlist) {
				if (itemCat.getIsParent()) {
					cidList.add(itemCat.getId());
					// 开始递归
					getCidListbyCid(itemCat.getId());
				} else {
					cidList.add(itemCat.getId());
				}
			}
		}

		return cidList;
	}

	@Override
	public List<EasyUITreeNode> getItemCatList(long parentId, long cid) {
		return null;
	}

	@Override
	public TaotaoResult getItemCatNameByCid(long cid) {
		TbItemCat itemCat = itemCatMapper.selectByPrimaryKey(cid);
		return TaotaoResult.ok(itemCat.getName());
	}

}
