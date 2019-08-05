package com.ixchou.mappings;

import com.ixchou.model.entity.TAttachment;

public interface TAttachmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TAttachment record);

    int insertSelective(TAttachment record);

    TAttachment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TAttachment record);

    int updateByPrimaryKey(TAttachment record);
}