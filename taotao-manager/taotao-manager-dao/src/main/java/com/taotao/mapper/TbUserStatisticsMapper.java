package com.taotao.mapper;

import com.taotao.pojo.TbUserStatistics;
import com.taotao.pojo.TbUserStatisticsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbUserStatisticsMapper {
    int countByExample(TbUserStatisticsExample example);

    int deleteByExample(TbUserStatisticsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbUserStatistics record);

    int insertSelective(TbUserStatistics record);

    List<TbUserStatistics> selectByExample(TbUserStatisticsExample example);

    TbUserStatistics selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbUserStatistics record, @Param("example") TbUserStatisticsExample example);

    int updateByExample(@Param("record") TbUserStatistics record, @Param("example") TbUserStatisticsExample example);

    int updateByPrimaryKeySelective(TbUserStatistics record);

    int updateByPrimaryKey(TbUserStatistics record);
}