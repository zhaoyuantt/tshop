package com.taotao.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbSysStatisticsMapper;
import com.taotao.pojo.TbSysStatistics;
import com.taotao.pojo.TbSysStatisticsExample;
import com.taotao.service.ItemStatisticsService;
import com.taotao.utils.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemStatisticsServiceImpl implements ItemStatisticsService {

    private static final Logger log = Logger.getLogger(ItemStatisticsServiceImpl.class);

    @Autowired
    private TbSysStatisticsMapper sysStatisticsMapper;

    @Override
    public TaotaoResult getItemStatisticsNum() {

        //设置查询条件
        TbSysStatisticsExample example = new TbSysStatisticsExample();
        TbSysStatisticsExample.Criteria criteria = example.createCriteria();
        criteria.andJobDateEqualTo(DateUtils.getDate());

        //执行查询
        List<TbSysStatistics> sysStatisticsList = sysStatisticsMapper.selectByExample(example);

        if(null == sysStatisticsList || 0 == sysStatisticsList.size()){
            return TaotaoResult.build(400,"无统计数据");
        }

        TbSysStatistics sysStatistics = sysStatisticsList.get(0);

        //处理返回结果
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("ITEMNUM",sysStatistics.getItemNum());
        jsonObject.fluentPut("ITEMUPNUM",sysStatistics.getItemUpNum());
        jsonObject.fluentPut("ITEMDOWNNUM",sysStatistics.getItemDownNum());
        jsonObject.fluentPut("ITEMPARAMNUM",sysStatistics.getItemParamNum());

        return TaotaoResult.ok(jsonObject);
    }
}
