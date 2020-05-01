package com.taotao.solrj;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * solr集群版测试
 * @author zhaoyuan
 * @date 2019年4月28日 下午6:13:12
 */
public class TestSolrCloud {

	/**
	 * solr集群 添加文档
	 */
	@Test
	public void testSolrCloudAddDocu(){
		CloudSolrServer cloudSolrServer = null;
		try {
			//1.创建CloudSolrCloud地址，构造方法中指定zookeeper集群地址列表
			//客户端连接时，直接连zookeeper
			cloudSolrServer = new 
					CloudSolrServer("192.168.25.128:2181,192.168.25.128:2182,192.168.25.128:2183");
			//2.必须设置默认的Collection,否则会报错
			cloudSolrServer.setDefaultCollection("collection2");
			//3.创建一个文档对象
			SolrInputDocument inputDocument = new SolrInputDocument();
			//4.向文档中添加域
			inputDocument.setField("id", "test001");
			inputDocument.setField("item_title", "测试商品");
			inputDocument.setField("item_price", 100);
			//5.写入索引库
			cloudSolrServer.add(inputDocument);
			//6.提交
			cloudSolrServer.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(null != cloudSolrServer ){
					cloudSolrServer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
