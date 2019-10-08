package com.ixchou.mappings;

import com.ixchou.model.entity.TMotto;

import java.util.List;

public interface TMottoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TMotto record);

    int insertSelective(TMotto record);

    TMotto selectByPrimaryKey(Integer id);

    List<TMotto> list();

    int updateByPrimaryKeySelective(TMotto record);

    int updateByPrimaryKeyWithBLOBs(TMotto record);

    int updateByPrimaryKey(TMotto record);
}