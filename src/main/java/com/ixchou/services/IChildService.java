package com.ixchou.services;

import com.ixchou.model.entity.TChild;
import com.ixchou.model.vo.ChildVo;

import java.util.List;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/08/05 17:30<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: 孩子的服务类<br/>
 * <b>Description</b>:
 */
public interface IChildService {

    /**
     * 指定用户添加一个孩子的信息
     *
     * @param vo 用户添加的孩子的信息
     */
    int addChild(ChildVo vo);

    List<TChild> find(String sessionId);

    int deleteChild(Integer childId);
}
