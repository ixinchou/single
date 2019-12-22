package com.ixchou.mappings;

import com.ixchou.model.entity.TAttachment;

import java.util.List;

public interface TAttachmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(TAttachment record);

    TAttachment selectByPrimaryKey(Integer id);

    TAttachment selectBySignature(String signature);

    List<TAttachment> selectByHostId(Integer host);

    int updateByPrimaryKeySelective(TAttachment record);
}