package com.ixchou.services.impl;

import com.ixchou.mappings.TAttachmentMapper;
import com.ixchou.model.entity.TAttachment;
import com.ixchou.services.IAttachmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/10/07 01:27<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: 附件服务<br/>
 * <b>Description</b>:
 */
@Service
public class AttachmentServiceImpl implements IAttachmentService {

    @Resource
    TAttachmentMapper mapper;

    @Override
    public int insert(TAttachment attachment) {
        return mapper.insertSelective(attachment);
    }

    @Override
    public TAttachment query(String signature) {
        return mapper.selectBySignature(signature);
    }
}
