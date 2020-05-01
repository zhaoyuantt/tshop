package com.taotao.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.UserStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户相关统计Controller
 * @author zhaoyuan
 * @date 2019,Nov 4:41 pm
 */
@Controller
@RequestMapping("/user")
public class UserStatisticsController {

    @Autowired
    private UserStatisticsService userStatisticsService;

    @RequestMapping(value = "/statistics/index",method = RequestMethod.GET)
    @ResponseBody
    public TaotaoResult getUserStatisticsNum(){
        try {
            TaotaoResult result = userStatisticsService.getUserStatisticsNum();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500,e.getMessage());
        }
    }
}
