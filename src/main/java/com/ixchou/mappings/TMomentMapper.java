package com.ixchou.mappings;

import com.ixchou.model.entity.TMoment;

public interface TMomentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TMoment record);

    int insertSelective(TMoment record);

    TMoment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TMoment record);

    int updateByPrimaryKey(TMoment record);
}