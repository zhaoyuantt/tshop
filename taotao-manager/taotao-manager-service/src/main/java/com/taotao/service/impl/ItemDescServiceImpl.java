package com.taotao.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemDescExample;
import com.taotao.pojo.TbItemDescExample.Criteria;
import com.taotao.service.ItemDescService;

/**
 * 商品描述Service接口实现
 * 
 * @author zhaoyuan
 * @date 2019年11月22日 上午9:46:45
 */
@Service
public class ItemDescServiceImpl implements ItemDescService {

	private static final Logger log = Logger.getLogger(ItemDescServiceImpl.class);
	
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	@Override
	public TaotaoResult getItemDescByItemId(long itemId) {
		TbItemDescExample itemDescExample = new TbItemDescExample();
		Criteria itemDescCriteria = itemDescExample.createCriteria();
		itemDescCriteria.andItemIdEqualTo(itemId);
		
		List<TbItemDesc> itemDescList = itemDescMapper.selectByExampleWithBLOBs(itemDescExample);
		
		if(null != itemDescList && 0 < itemDescList.size() ){
			TbItemDesc itemDesc = itemDescList.get(0);
			return TaotaoResult.ok(itemDesc);
		}
		
		return TaotaoResult.build(400, "没有查询到记录，itemId为："+itemId, null);
	}

}
