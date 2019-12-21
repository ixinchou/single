package com.ixchou.mappings;

import com.ixchou.model.entity.TTerm;

import java.util.List;

public interface TTermMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TTerm record);

    int insertSelective(TTerm record);

    TTerm selectByPrimaryKey(Integer id);

    List<TTerm> list();

    int updateByPrimaryKeySelective(TTerm record);

    int updateByPrimaryKey(TTerm record);
}