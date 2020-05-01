package com.taotao.mapper;

import com.taotao.pojo.TbAdminUserItem;
import com.taotao.pojo.TbAdminUserItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbAdminUserItemMapper {
    int countByExample(TbAdminUserItemExample example);

    int deleteByExample(TbAdminUserItemExample example);

    int deleteByPrimaryKey(String id);

    int insert(TbAdminUserItem record);

    int insertSelective(TbAdminUserItem record);

    List<TbAdminUserItem> selectByExample(TbAdminUserItemExample example);

    TbAdminUserItem selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TbAdminUserItem record, @Param("example") TbAdminUserItemExample example);

    int updateByExample(@Param("record") TbAdminUserItem record, @Param("example") TbAdminUserItemExample example);

    int updateByPrimaryKeySelective(TbAdminUserItem record);

    int updateByPrimaryKey(TbAdminUserItem record);
}