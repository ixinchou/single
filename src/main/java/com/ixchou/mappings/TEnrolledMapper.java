package com.ixchou.mappings;

import com.ixchou.model.entity.TEnrolled;

public interface TEnrolledMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TEnrolled record);

    int insertSelective(TEnrolled record);

    TEnrolled selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TEnrolled record);

    int updateByPrimaryKey(TEnrolled record);
}