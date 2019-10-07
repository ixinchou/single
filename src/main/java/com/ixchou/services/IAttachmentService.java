package com.ixchou.services;

import com.ixchou.model.entity.TAttachment;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/10/07 01:26<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: 文件相关服务<br/>
 * <b>Description</b>:
 */
public interface IAttachmentService {

    int insert(TAttachment attachment);

    TAttachment query(String signature);
}
