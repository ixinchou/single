package com.ixchou.mappings;

import com.ixchou.model.entity.TMember;

import java.util.List;

public interface TMemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TMember record);

    int insertSelective(TMember record);

    TMember selectByPrimaryKey(Integer id);

    TMember selectBySessionId(String sessionId);

    List<TMember> list();

    List<TMember> fuzzySearch(TMember record);

    int updateByPrimaryKeySelective(TMember record);

    int updateByPrimaryKey(TMember record);
}