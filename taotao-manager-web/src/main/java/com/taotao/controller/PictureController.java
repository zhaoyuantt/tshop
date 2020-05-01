package com.taotao.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.utils.FastDFSClient;
import com.taotao.utils.JsonUtils;

/**
 * 图片上传的Controller
 * @author zhaoyuan
 * @date 2019-02-24 3:09 PM zhaoyuan
 */
@Controller
@RequestMapping("/pic")
public class PictureController {

	//图片服务器地址
	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;
	
	/**
	 * 上传图片
	 * @param uploadFile
	 * @return
	 */
	@RequestMapping("/upload")
	@ResponseBody
	public String picUpload(MultipartFile uploadFile){
		//图片名
		String originalFilename = uploadFile.getOriginalFilename();
		//图片扩展名
		String extName = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
		
		try {
			//加载tracker服务器配置文件
			FastDFSClient fastDFSClient = new FastDFSClient("classpath:fastdfs\\client.conf");
			//上传
			String url = fastDFSClient.uploadFile(uploadFile.getBytes(), extName, null);
			String imageUrl = IMAGE_SERVER_URL + url;
			HashMap<String,Object> result = new HashMap<>();
			result.put("error", 0);
			result.put("url",imageUrl);
			return JsonUtils.objectToJson(result);
		} catch (Exception e) {
			e.printStackTrace();
			HashMap<String,Object> errorResult = new HashMap<>();
			errorResult.put("error", 1);
			errorResult.put("message","上传图片失败");
			return JsonUtils.objectToJson(errorResult);
		}
	}
	
}
