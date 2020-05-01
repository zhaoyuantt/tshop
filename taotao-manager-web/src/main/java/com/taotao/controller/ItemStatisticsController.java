package com.taotao.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ItemStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 商品相关信息统计的Controller
 * @author zhaoyuan
 * @date 2019,Nov 26 4:35 pm
 */

@Controller
@RequestMapping("/item")
public class ItemStatisticsController {

    @Autowired
    private ItemStatisticsService itemStatisticsService;

    @RequestMapping("/statistics/item")
    @ResponseBody
    public TaotaoResult getItemStatisticsNum(){
        try {
            TaotaoResult result = itemStatisticsService.getItemStatisticsNum();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500,e.getMessage());
        }
    }

}
