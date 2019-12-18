package com.ixchou.services.impl;

import com.ixchou.model.entity.TContent;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

/**
 * <b>Author</b>: Xiang Liguo<br/>
 * <b>Date</b>: 2019/12/18 09:06<br/>
 * <b>Version</b>: 1.0<br/>
 * <b>Subject</b>: <br/>
 * <b>Description</b>:
 */
@Service
@DependsOn("springContextAware")
public class ContentServiceImpl extends BaseServiceImpl<TContent> {

    /**
     * 内容类别
     */
    public interface Type {
        /**
         * 校训
         */
        int MOTTO = 1;
        /**
         * 课程
         */
        int COURSE = 2;
        /**
         * 日志
         */
        int MOMENT = 3;
    }
}
