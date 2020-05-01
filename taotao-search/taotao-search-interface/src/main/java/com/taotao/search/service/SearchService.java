package com.taotao.search.service;

/**
 * 商品（索引）搜索的接口
 * @author zhaoyuan
 * @date 2019年4月26日 上午9:27:12
 */
public interface SearchService {
	
	/**
	 * 商品搜索
	 * @param queryString   查询条件
	 * @param page   第几页
	 * @param rows   每页显示多少条
	 * @return
	 */
	com.taotao.common.pojo.SearchResult search(String queryString,int page,int rows);
	
}
