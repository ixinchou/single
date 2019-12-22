package com.ixchou.mappings;

import com.ixchou.model.entity.TRelation;

import java.util.List;

public interface TRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TRelation record);

    int insertSelective(TRelation record);

    TRelation selectByPrimaryKey(Integer id);

    List<TRelation> selectByHostKey(Integer host);

    int updateByPrimaryKeySelective(TRelation record);

    int updateByPrimaryKey(TRelation record);
}