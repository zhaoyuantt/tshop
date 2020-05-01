package com.taotao.mapper;

import com.taotao.pojo.TbSysStatistics;
import com.taotao.pojo.TbSysStatisticsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbSysStatisticsMapper {
    int countByExample(TbSysStatisticsExample example);

    int deleteByExample(TbSysStatisticsExample example);

    int deleteByPrimaryKey(Byte id);

    int insert(TbSysStatistics record);

    int insertSelective(TbSysStatistics record);

    List<TbSysStatistics> selectByExample(TbSysStatisticsExample example);

    TbSysStatistics selectByPrimaryKey(Byte id);

    int updateByExampleSelective(@Param("record") TbSysStatistics record, @Param("example") TbSysStatisticsExample example);

    int updateByExample(@Param("record") TbSysStatistics record, @Param("example") TbSysStatisticsExample example);

    int updateByPrimaryKeySelective(TbSysStatistics record);

    int updateByPrimaryKey(TbSysStatistics record);
}