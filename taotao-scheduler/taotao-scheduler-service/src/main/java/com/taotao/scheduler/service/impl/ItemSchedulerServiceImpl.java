package com.taotao.scheduler.service.impl;

import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.mapper.TbSysStatisticsMapper;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.pojo.TbSysStatistics;
import com.taotao.scheduler.service.ItemSchedulerService;
import com.taotao.utils.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 商品相关信息数量定时统计接口实现
 *
 * @author zhaoyuan
 * @date 2019, Nov 26 10:29 am
 */
@Service
public class ItemSchedulerServiceImpl implements ItemSchedulerService {

    private static final Logger log = Logger.getLogger(ItemSchedulerServiceImpl.class);

    @Autowired
    private TbSysStatisticsMapper sysStatisticsMapper;
    @Autowired
    private TbItemMapper itemMapper;
    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    @Override
    public void addItemRelationNum() {
        //商品数量
        //商品状态，1-正常，2-下架，3-删除
        TbItemExample itemExample = new TbItemExample();
        long itemCount = itemMapper.countByExample(itemExample);

        //上架商品商量
        TbItemExample.Criteria itemExampleCriteria = itemExample.createCriteria();
        itemExampleCriteria.andStatusEqualTo((byte) 1);
        long itemUpCount = itemMapper.countByExample(itemExample);

        //下架商品数量
        TbItemExample itemUpExample = new TbItemExample();
        TbItemExample.Criteria itemUpExampleCriteria = itemUpExample.createCriteria();
        itemUpExampleCriteria.andStatusEqualTo((byte) 2);
        int itemDownCount = itemMapper.countByExample(itemUpExample);

        //商品规格参数数量
        TbItemParamItemExample itemParamItemExample = new TbItemParamItemExample();
        long itemParamCount = itemParamItemMapper.countByExample(itemParamItemExample);

        /*JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("ITEMNUM",itemCount);
        jsonObject.fluentPut("ITEMDOWNNUM",itemDownCount);
        jsonObject.fluentPut("ITEMUPNUM",itemUpCount);
        jsonObject.fluentPut("ITEMPARAMNUM",itemParamCount);*/

        TbSysStatistics sysStatistics = new TbSysStatistics();
        sysStatistics.setItemNum(itemCount);
        sysStatistics.setItemDownNum(itemDownCount);
        sysStatistics.setItemUpNum(itemUpCount);
        sysStatistics.setItemParamNum(itemParamCount);
        sysStatistics.setJobTime(new Date());
        sysStatistics.setJobDate(DateUtils.getDate());

        sysStatisticsMapper.insert(sysStatistics);

    }
}
