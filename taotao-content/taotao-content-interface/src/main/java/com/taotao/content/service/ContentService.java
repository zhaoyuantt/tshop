package com.taotao.content.service;

import java.util.List;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

/**
 * 商品内容管理接口
 * @author zhaoyuan
 * @date 2019年3月2日 下午6:49:34
 */
public interface ContentService {

	/**
	 * 内容添加
	 * @param content
	 * @return
	 */
	public TaotaoResult addContent(TbContent content);
	
	/**
	 * 首页内容列表查询
	 * @param cid   分类Id
	 * @return
	 */
	public List<TbContent> getContentListByCid(Long cid);
	
	/**
	 * 分页查询内容列表
	 * @param pageNum   当前页
	 * @param pageSize  每页显示多少条数据
	 * @param cid       内容分类Id
	 * @return
	 */
	EasyUIDataGridResult getContentList(int pageNum,int pageSize,long cid);
	
}
