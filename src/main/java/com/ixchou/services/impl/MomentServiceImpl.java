package com.ixchou.services.impl;

import com.github.pagehelper.PageInfo;
import com.ixchou.model.entity.TMoment;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/12/21 10:29<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: 个人风采相关服务<br/>
 * <b>Description</b>:
 */
@Service
@DependsOn("springContextAware")
public class MomentServiceImpl extends BaseServiceImpl<TMoment> {

    /**
     * 根据类型查询个人风采列表
     */
    public PageInfo<TMoment> list(Byte type, Integer pageIndex, Integer pageSize) {
        return list("list", type, pageIndex, pageSize);
    }
}
