package com.ixchou.mappings;

import com.ixchou.model.entity.TAttachment;

public interface TAttachmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(TAttachment record);

    TAttachment selectByPrimaryKey(Integer id);

    TAttachment selectBySignature(String signature);

    int updateByPrimaryKeySelective(TAttachment record);
}