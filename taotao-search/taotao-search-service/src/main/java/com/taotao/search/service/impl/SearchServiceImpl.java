package com.taotao.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.SearchResult;
import com.taotao.search.dao.SearchDao;
import com.taotao.search.service.SearchService;


/**
 * 商品（索引）搜索的接口实现
 * @author zhaoyuan
 * @date 2019年4月26日 上午9:27:12
 */
@Service
public class SearchServiceImpl implements SearchService{

	@Autowired
	private SearchDao searchDao;
	
	
	@Override
	public SearchResult search(String queryString, int page, int rows) {
		//*****************************************************
		//				封装SolrQuery对象的查询条件
		//*****************************************************
		//创建SolrQuery对象
		SolrQuery solrQuery = new SolrQuery();
		
		//向SolrQuery对象中添加查询条件、过滤条件......
		solrQuery.setQuery(queryString);
		//设置分页条件
		if(page < 1){
			page = 1;
		}
		if(rows < 1){
			rows = 10;
		}
		solrQuery.setStart((page-1)*rows);   //第几页
		solrQuery.setRows(rows);   //每页显示多少条文档
		//设置默认搜索域
		solrQuery.set("df", "item_title");
		//开启高亮显示
		solrQuery.setHighlight(true);
		//要高亮显示的域
		solrQuery.addHighlightField("item_title");
		solrQuery.setHighlightSimplePre("<font color='red'>");   //高亮前缀
		solrQuery.setHighlightSimplePost("</font>");   //高亮后缀
		//*****************************************************
		
		SearchResult result = searchDao.search(solrQuery);
		
		//总共多少条记录
		long recordCount = result.getRecordCount();
		//总共多少页
		long pages = recordCount / rows;
		if (recordCount % rows > 0) {
			pages++;
		}
		
		result.setPageCount(pages);
		
		return result;
	}

	
	
}
