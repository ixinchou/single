package com.ixchou.services.impl;

import com.ixchou.model.entity.TAttachment;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/10/07 01:27<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: 附件服务<br/>
 * <b>Description</b>:
 */
@Service
@DependsOn("springContextAware")
public class AttachmentServiceImpl extends BaseServiceImpl<TAttachment> {

}
