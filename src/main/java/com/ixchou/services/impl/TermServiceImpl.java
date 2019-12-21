package com.ixchou.services.impl;

import com.ixchou.model.entity.TTerm;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/12/20 21:59<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: <br/>
 * <b>Description</b>:
 */
@Service
@DependsOn("springContextAware")
public class TermServiceImpl extends BaseServiceImpl<TTerm> {
}
