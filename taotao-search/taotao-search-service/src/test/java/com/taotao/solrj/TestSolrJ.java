package com.taotao.solrj;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * SolrJ入门
 * @author zhaoyuan
 * @date 2019年4月14日 下午8:41:45
 */
public class TestSolrJ {

	/**
	 * 添加索引
	 */
	@Test
	public void addDocument(){
		HttpSolrServer solrServer = null;
		try {
			//1.创建一个SolrServer对象。HttpSolrServer是连接单机版的
			solrServer = new HttpSolrServer("http://192.168.25.132:8081/solr/collection1");
			//2.创建一个文档对象 SolrInputDocument对象
			SolrInputDocument document  = new SolrInputDocument();
			//3.向文档添加域，这个域必须要有Id域，域的名称必须在scheam.xml中定义
			document.addField("id", "test001");
			document.addField("item_title","测试商品");
			document.addField("item_price", 1000);
			//4.把文档写入索引库
			solrServer.add(document);
			//5.提交
			solrServer.commit();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} finally {
			/*try {
				solrServer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}*/
		}
		
	}
	
	/**
	 * 根据查询条件删除文档
	 */
	@Test
	public void testDeleteDocumentByQuery(){
		try {
			HttpSolrServer solrServer = new HttpSolrServer("http://192.168.25.132:8081/solr/collection1");
			solrServer.deleteByQuery("id:test001");
			solrServer.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	//*****************************************************
	//              2019-04-25 4:34 PM
	//*****************************************************
	
	/**
	 * 简单查询
	 */
	@Test
	public void queryDocument(){
		HttpSolrServer httpSolrServer = null;
		try {
			//创建SolrServer对象
			httpSolrServer = new HttpSolrServer("http://192.168.25.132:8081/solr/collection1");
			//创建SolrQuery对象
			SolrQuery solrQuery = new SolrQuery();
			//向SolrQuery对象中添加查询条件、过滤条件.....
			solrQuery.setQuery("*:*");
			//执行查询
			QueryResponse queryResponse = httpSolrServer.query(solrQuery);
			//取查询结果
			SolrDocumentList documentList = queryResponse.getResults();
			System.out.println("共查询到 "+documentList.getNumFound()+" 条文档");
			
			for (SolrDocument solrDocument : documentList) {
				System.out.println(solrDocument.get("item_title"));
				System.out.println(solrDocument.get("item_sell_point"));
				System.out.println(solrDocument.get("item_price"));
				System.out.println(solrDocument.get("item_image"));
				System.out.println(solrDocument.get("item_category_name"));
				System.out.println(solrDocument.get("item_desc"));
				System.out.println(solrDocument.get("item_keywords"));
			}
			
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			/*try {
				httpSolrServer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}*/
		}
	}
	
	/**
	 * 高亮查询
	 */
	@Test
	public void queryDocumentWithHighLighting(){
		HttpSolrServer httpSolrServer = null;
		try {
			httpSolrServer = new HttpSolrServer("http://192.168.25.132:8081/solr/collection1");
			SolrQuery solrQuery = new SolrQuery();
			
			//向SolrQuery对象中添加查询条件、过滤条件......
			solrQuery.setQuery("测试");
			//指定默认搜索域
			solrQuery.set("df", "item_keywords");
			//开启高亮显示
			solrQuery.setHighlight(true);
			//要高亮显示的域
			solrQuery.addHighlightField("item_title");
			solrQuery.setHighlightSimplePre("<em>");
			solrQuery.setHighlightSimplePost("</em>");
			
			//执行查询
			QueryResponse queryResponse = httpSolrServer.query(solrQuery);
			//取查询结果
			SolrDocumentList documentList = queryResponse.getResults();
			
			System.out.println("共查询到 "+documentList.getNumFound()+" 条文档");
			
			for (SolrDocument solrDocument : documentList) {
				
				//*******************************************************
				//取高亮结果
				Map<String, Map<String, List<String>>> highlightingMap = queryResponse.getHighlighting();
				List<String> list = highlightingMap
						.get(solrDocument.get("id")).get("item_title");
				
				String itemTitle = null;
				if(null != list && list.size()>0){
					itemTitle = list.get(0);
				} else{
					itemTitle = (String) solrDocument.get("item_title");
				}
				//*******************************************************
				
				System.out.println(itemTitle);
				System.out.println(solrDocument.get("item_sell_point"));
				System.out.println(solrDocument.get("item_price"));
				System.out.println(solrDocument.get("item_image"));
				System.out.println(solrDocument.get("item_category_name"));
				System.out.println(solrDocument.get("item_desc"));
				System.out.println(solrDocument.get("item_keywords"));
			}
			
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			/*try {
				httpSolrServer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}*/
		}
	}
	
}
