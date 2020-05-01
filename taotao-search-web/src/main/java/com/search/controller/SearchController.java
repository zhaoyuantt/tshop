package com.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.common.pojo.SearchResult;
import com.taotao.search.service.SearchService;

/**
 * 商品搜索的Controller
 * @author zhaoyuan
 * @date 2019年4月26日 上午10:15:30
 */
@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	@Value("${SEARCH_RESULT_ROWS}")
	private Integer SEARCH_RESULT_ROWS;
	
	/**
	* 搜索
    */
    @RequestMapping("/search")
	public String search(@RequestParam("q") String queryString,
						 @RequestParam(defaultValue = "1") int page,
						 Model model){
		try {
			//get请求转码
			//queryString = new String(queryString.getBytes("iso8859-1"), "utf-8");
			//int i = 1/0;
			SearchResult  result = searchService.search(queryString, page, SEARCH_RESULT_ROWS);
			
			//把结果传递给页面
			model.addAttribute("query", queryString);
			model.addAttribute("totalPages", result.getPageCount());
			model.addAttribute("itemList", result.getItemList());
			model.addAttribute("page", page);
			
			//返回逻辑视图
			return "search";
			
		} catch (Exception e) {
			e.printStackTrace();
			return "search";
		}
	}
	
}
