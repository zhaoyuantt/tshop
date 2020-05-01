package com.taotao.fastdfs;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import com.taotao.utils.FastDFSClient;

/**
 * FastDFS 的客户端的使用
 * @author zhaoyuan
 * @date 2019-02-24 1:31 PM
 */
public class TestFastDFS {

	/**
	 * 上传图片
	 * @throws Exception
	 */
	@Test
	public void testUploadFile() throws Exception{
		//1.添加依赖的jar包
		//2.创建一个配置文件，配置tracker服务器地址
		//3.加载配置文件
		ClientGlobal.init("E:\\workSpace-me\\taotao-manager-web\\src\\main\\resources\\fastdfs\\client.conf");
		//4.创建一个TrackerClient对象
		TrackerClient trackerClient = new TrackerClient();
		//5.使用TrackerClient对象获得trackerserver对象
		TrackerServer trackerServer = trackerClient.getConnection();
		//6.创建一个StorageServer的引用null就可以
		StorageServer storageServer = null;
		//7.创建一个StorageClient对象。trackerserver、StorageServer两个参数
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		//8.使用StorageClient对象上传文件
		String[]  strings = storageClient.upload_appender_file("G:\\images\\IMG_0677.JPG", "JPG", null);
	    for (int i = 0; i < strings.length; i++) {
			System.out.println(strings[i]);
		}
	}
	
	/**
	 * 上传图片工具类测试
	 * @throws Exception
	 */
	@Test
	public void testFastDFSClientUtil() throws Exception{
		FastDFSClient fastDFSClient = new FastDFSClient("E:\\workSpace-me\\taotao-manager-web\\src\\main\\resources\\fastdfs\\client.conf");
		String uploadFileRes = fastDFSClient.uploadFile("G:\\images\\IMG_0677.JPG", "JPG", null);
		System.out.println(uploadFileRes);
	}
	
}
