package com.taotao.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbUserStatisticsMapper;
import com.taotao.pojo.TbUserStatistics;
import com.taotao.pojo.TbUserStatisticsExample;
import com.taotao.service.UserStatisticsService;
import com.taotao.utils.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户相关的统计接口
 * @author zhaoyuan
 * @date 2019,Nov 27 3:58 pm
 */
@Service
public class UserStatisticsServiceImpl implements UserStatisticsService {

    private static final Logger log = Logger.getLogger(UserStatisticsServiceImpl.class);

    @Autowired
    private TbUserStatisticsMapper userStatisticsMapper;

    @Override
    public TaotaoResult getUserStatisticsNum() {
        TbUserStatisticsExample userStatisticsExample = new TbUserStatisticsExample();
        TbUserStatisticsExample.Criteria userStatisticsExampleCriteria = userStatisticsExample.createCriteria();
        userStatisticsExampleCriteria.andJobDateEqualTo(DateUtils.getDate());
        List<TbUserStatistics> userStatisticsList = userStatisticsMapper.selectByExample(userStatisticsExample);

        if(null == userStatisticsList || 0 == userStatisticsList.size()){
            return  TaotaoResult.build(400,"无查询到用户相关的统计数据");
        }

        JSONObject jsonObject = new JSONObject();

        TbUserStatistics userStatistics = userStatisticsList.get(0);

        jsonObject.fluentPut("ADMINUSERNUM",userStatistics.getAdminUserNum());
        jsonObject.fluentPut("SHOPUSERNUM",userStatistics.getUserNum());

        return TaotaoResult.ok(jsonObject);
    }
}
