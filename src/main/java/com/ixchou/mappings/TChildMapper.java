package com.ixchou.mappings;

import com.ixchou.model.entity.TChild;

import java.util.List;

public interface TChildMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TChild record);

    int insertSelective(TChild record);

    TChild selectByPrimaryKey(Integer id);

    List<TChild> selectByMemberId(Integer memberId);

    int updateByPrimaryKeySelective(TChild record);

    int updateByPrimaryKey(TChild record);
}