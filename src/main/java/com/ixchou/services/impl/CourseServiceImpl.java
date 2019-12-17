package com.ixchou.services.impl;

import com.ixchou.model.entity.TCourse;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/10/09 21:53<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: 课程、特长服务<br/>
 * <b>Description</b>:
 */
@Service
@DependsOn("springContextAware")
public class CourseServiceImpl extends BaseServiceImpl<TCourse> {
}
