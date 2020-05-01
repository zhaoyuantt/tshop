package com.taotao.scheduler.service.impl;

import com.taotao.mapper.TbAdminUserMapper;
import com.taotao.mapper.TbUserMapper;
import com.taotao.mapper.TbUserStatisticsMapper;
import com.taotao.pojo.TbAdminUserExample;
import com.taotao.pojo.TbUserExample;
import com.taotao.pojo.TbUserStatistics;
import com.taotao.scheduler.service.UserStatisticsService;
import com.taotao.utils.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserStatisticsServiceImpl implements UserStatisticsService {

    private static final Logger log = Logger.getLogger(UserStatisticsServiceImpl.class);

    @Autowired
    private TbUserMapper userMapper;
    @Autowired
    private TbAdminUserMapper adminUserMapper;
    @Autowired
    private TbUserStatisticsMapper userStatisticsMapper;

    @Override
    public void addUserStatisticsNum(){
        //后台管理用户
        TbAdminUserExample adminUserExample = new TbAdminUserExample();
        int adminUserCount = adminUserMapper.countByExample(adminUserExample);

        //前台用户
        TbUserExample userExample = new TbUserExample();
        long userCount = userMapper.countByExample(userExample);

        TbUserStatistics userStatistics = new TbUserStatistics();
        userStatistics.setAdminUserNum((short) adminUserCount);
        userStatistics.setUserNum(userCount);
        userStatistics.setJobTime(new Date());
        userStatistics.setJobDate(DateUtils.getDate());

        userStatisticsMapper.insert(userStatistics);
    }
}
