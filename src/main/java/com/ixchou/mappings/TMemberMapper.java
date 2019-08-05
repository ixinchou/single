package com.ixchou.mappings;

import com.ixchou.model.entity.TMember;

public interface TMemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TMember record);

    int insertSelective(TMember record);

    TMember selectByPrimaryKey(Integer id);

    TMember selectBySessionId(String sessionId);

    TMember selectByWxOpenId(String openid);

    int updateByPrimaryKeySelective(TMember record);

    int updateByPrimaryKey(TMember record);
}