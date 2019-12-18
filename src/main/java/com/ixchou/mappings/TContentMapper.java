package com.ixchou.mappings;

import com.ixchou.model.entity.TContent;

public interface TContentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TContent record);

    int insertSelective(TContent record);

    TContent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TContent record);

    int updateByPrimaryKeyWithBLOBs(TContent record);

    int updateByPrimaryKey(TContent record);
}