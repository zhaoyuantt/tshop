package com.taotao.content.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentService;
import com.taotao.jedis.JedisClient;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.utils.JsonUtils;

/**
 * 商品内容管理接口实现
 * @author zhaoyuan
 * @date 2019年3月2日 下午6:51:19
 */
@Service
public class ContentServiceImpl implements ContentService{

	@Autowired
	private TbContentMapper contentMapper;
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${INDEX_CONTENT}")
	private String INDEX_CONTENT;
	
	@Override
	public TaotaoResult addContent(TbContent content) {
		content.setCreated(new Date());
		content.setUpdated(new Date());
		contentMapper.insert(content);
		
		//缓存同步
		try {
			jedisClient.hdel(INDEX_CONTENT,content.getCategoryId().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return TaotaoResult.ok();
	}

	@Override
	public List<TbContent> getContentListByCid(Long cid) {
		//查询缓存
		try {
			String contentsString = jedisClient.hget(INDEX_CONTENT, cid+"");
			if(StringUtils.isNotBlank(contentsString)){
				List<TbContent> contentsResRedis = JsonUtils.jsonToList(contentsString, TbContent.class);
				return contentsResRedis;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(cid);
		List<TbContent> contents = contentMapper.selectByExample(example);
		
		//添加缓存
		try {
			jedisClient.hset(INDEX_CONTENT, cid+"", JsonUtils.objectToJson(contents));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return contents;
	}

	@Override
	public EasyUIDataGridResult getContentList(int pageNum, int pageSize, long cid) {
		//设置分页信息
		PageHelper.startPage(pageNum, pageSize);
		//设置查询条件
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(cid);
		List<TbContent> contentList = contentMapper.selectByExample(example);
		
		//去查询结果
		PageInfo<TbContent> pageInfo = new PageInfo<>(contentList);
		
		//包装返回结果
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(contentList);
		long total = pageInfo.getTotal();
		result.setTotal(total);
		
		return result;
	}

}
