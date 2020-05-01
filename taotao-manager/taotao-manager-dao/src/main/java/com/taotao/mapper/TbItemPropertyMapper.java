package com.taotao.mapper;

import com.taotao.pojo.TbItemProperty;
import com.taotao.pojo.TbItemPropertyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbItemPropertyMapper {
    int countByExample(TbItemPropertyExample example);

    int deleteByExample(TbItemPropertyExample example);

    int deleteByPrimaryKey(Short id);

    int insert(TbItemProperty record);

    int insertSelective(TbItemProperty record);

    List<TbItemProperty> selectByExample(TbItemPropertyExample example);

    TbItemProperty selectByPrimaryKey(Short id);

    int updateByExampleSelective(@Param("record") TbItemProperty record, @Param("example") TbItemPropertyExample example);

    int updateByExample(@Param("record") TbItemProperty record, @Param("example") TbItemPropertyExample example);

    int updateByPrimaryKeySelective(TbItemProperty record);

    int updateByPrimaryKey(TbItemProperty record);
}