package com.taotao.search.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.taotao.common.pojo.SearchItem;
import com.taotao.common.pojo.SearchResult;

/**
 * 商品索引查询Dao
 * @author zhaoyuan
 * @date 2019年4月25日 下午7:20:17
 */
@Repository
public class SearchDao {

	@Autowired
	private HttpSolrServer httpSolrServer;
	//@Autowired
	//private SolrServer solrServer;
	//private CloudSolrServer cloudSolrServer;
	
	/**
	 * 根据查询条件查询商品索引
	 * @param solrQuery   查询条件
	 * @return
	 */
	public SearchResult search(SolrQuery solrQuery){
		SearchResult result = null;
		
		try {
			
			result = new SearchResult();
			
			//执行查询
			QueryResponse queryResponse = httpSolrServer.query(solrQuery);
			//取查询结果
			SolrDocumentList documentList = queryResponse.getResults();
			
			//查询到总记录数
			long numFound = documentList.getNumFound();
			result.setRecordCount(numFound);
			
			ArrayList<SearchItem> searchItemList = new ArrayList<SearchItem>();
			
			for (SolrDocument solrDocument : documentList) {
				SearchItem item = new SearchItem();
				item.setId((String) solrDocument.get("id"));
				item.setCategory_name((String) solrDocument.get("item_category_name"));
				//只取一张图片
				String images = (String) solrDocument.get("item_image");
				if(StringUtils.isNotBlank(images)){
					images = images.split(",")[0];
				}
				item.setImage(images);
				item.setPrice((long) solrDocument.get("item_price"));
				item.setSell_point((String) solrDocument.get("item_sell_point"));
				
				//取高亮结果
				Map<String, Map<String, List<String>>> highlightingMap = queryResponse.getHighlighting();
				List<String> list = highlightingMap.get(solrDocument.get("id")).get("item_title");
				String title = "";
				if(null != list && list.size() > 0){
					title = list.get(0);
				}else{
					title = (String) solrDocument.get("item_title");
				}
				
				item.setTitle(title);
				
				searchItemList.add(item);
			}
			
			result.setItemList(searchItemList);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return result;
	}
}
