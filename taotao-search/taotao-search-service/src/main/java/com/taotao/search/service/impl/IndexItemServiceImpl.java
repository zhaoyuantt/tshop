package com.taotao.search.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.SearchItem;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.mapper.IndexItemMapper;
import com.taotao.search.service.IndexItemService;

/**
 * 商品索引管理Service接口实现
 * @author zhaoyuan
 * @date 2019年4月14日 下午9:27:25
 */
@Service
public class IndexItemServiceImpl implements IndexItemService {

	@Autowired
	private IndexItemMapper indexItemMapper;
	@Autowired
	private HttpSolrServer httpSolrServer;
	//@Autowired
	//private SolrServer solrServer;
	//private CloudSolrServer cloudSolrServer;
	
	
	@Override
	public TaotaoResult importItemsToIndex() {
		//先查询所有商品
		List<SearchItem> searchItemList = indexItemMapper.getItemList();
		
		try {
			for (SearchItem item : searchItemList) {
				//创建文档对象
				SolrInputDocument solrInputDocument = new SolrInputDocument();
				//向文档中添加域，必须要有一个Id域
				solrInputDocument.addField("id", item.getId());
				solrInputDocument.addField("item_title", item.getTitle());
				solrInputDocument.addField("item_sell_point", item.getSell_point());
				solrInputDocument.addField("item_price", item.getPrice());
				solrInputDocument.addField("item_image", item.getImage());
				solrInputDocument.addField("item_category_name", item.getCategory_name());
				solrInputDocument.addField("item_desc", item.getItem_desc());
				//把文档写入索引库
				httpSolrServer.add(solrInputDocument);
			}
			//提交
			httpSolrServer.commit();
			return TaotaoResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, "商品数据导入索引库失败");
		} 
		
	}
	
	@Override
	public void addDocument(long itemId){
		//等待数据库事务提交
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		//调用dao查询商品数据
		SearchItem searchItem = indexItemMapper.getItemById(itemId);
		
		//创建文档对象
		SolrInputDocument solrInputDocument = new SolrInputDocument();
		//向文档添加域，必须要有一个Id域
		solrInputDocument.addField("id", searchItem.getId());
		solrInputDocument.addField("item_title", searchItem.getTitle());
		solrInputDocument.addField("item_sell_point", searchItem.getSell_point());
		solrInputDocument.addField("item_price", searchItem.getPrice());
		solrInputDocument.addField("item_image", searchItem.getImage());
		solrInputDocument.addField("item_category_name", searchItem.getCategory_name());
		solrInputDocument.addField("item_desc", searchItem.getItem_desc());
		
		try {
			//把文档写入索引库
			httpSolrServer.add(solrInputDocument);
			//提交到索引库
			httpSolrServer.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
