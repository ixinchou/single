package com.ixchou.services.impl;

import com.ixchou.model.entity.TRelation;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/12/22 00:25<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: 附件关系服务<br/>
 * <b>Description</b>:
 */
@Service
@DependsOn("springContextAware")
public class RelationServiceImpl extends BaseServiceImpl<TRelation> {
}
