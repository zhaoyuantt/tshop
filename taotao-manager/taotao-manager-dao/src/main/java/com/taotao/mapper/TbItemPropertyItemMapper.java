package com.taotao.mapper;

import com.taotao.pojo.TbItemPropertyItem;
import com.taotao.pojo.TbItemPropertyItemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbItemPropertyItemMapper {
    int countByExample(TbItemPropertyItemExample example);

    int deleteByExample(TbItemPropertyItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbItemPropertyItem record);

    int insertSelective(TbItemPropertyItem record);

    List<TbItemPropertyItem> selectByExample(TbItemPropertyItemExample example);

    TbItemPropertyItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbItemPropertyItem record, @Param("example") TbItemPropertyItemExample example);

    int updateByExample(@Param("record") TbItemPropertyItem record, @Param("example") TbItemPropertyItemExample example);

    int updateByPrimaryKeySelective(TbItemPropertyItem record);

    int updateByPrimaryKey(TbItemPropertyItem record);
}