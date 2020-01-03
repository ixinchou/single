package com.ixchou.mappings;

import com.ixchou.model.entity.TPay;

import java.util.List;

public interface TPayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TPay record);

    int insertSelective(TPay record);

    TPay selectByPrimaryKey(Integer id);

    List<TPay> selectByEnrolledId(Integer id);

    int updateByPrimaryKeySelective(TPay record);

    int updateByPrimaryKey(TPay record);
}