package com.ixchou.services.impl;

import com.github.pagehelper.PageInfo;
import com.ixchou.mappings.TMemberMapper;
import com.ixchou.model.entity.TChild;
import com.ixchou.model.entity.TMember;
import com.ixchou.model.vo.ChildVo;
import com.ixchou.services.IChildService;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/08/05 17:32<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: <br/>
 * <b>Description</b>:
 */
@Service
@DependsOn("springContextAware")
public class ChildServiceImpl extends BaseServiceImpl<TChild> implements IChildService {

    @Resource
    private TMemberMapper memberMapper;

    @Override
    public int addChild(ChildVo vo) {
        TMember member = memberMapper.selectBySessionId(vo.getSessionId());
        if (null == member) {
            return -2;
        }
        // 查找同一个用户底下是否有相同名字的孩子
        TChild child = new TChild();
        child.setMemberId(member.getId());
        child.setName(vo.getName());
        TChild exists = query(child);
        if (null == exists) {
            // 不存在相同名字的才添加
            child.setBirthday(vo.getBirthday());
            child.setMemberId(member.getId());
            child.setName(vo.getName());
            child.setRegisterTime(new Date());
            child.setSex(vo.getSex());
            return insert(child);
        }
        return -1;
    }

    @Override
    public List<TChild> find(String sessionId) {
        TMember member = memberMapper.selectBySessionId(sessionId);
        if (null == member) {
            return new ArrayList<>();
        }
        TChild child = new TChild();
        child.setMemberId(member.getId());
        PageInfo<TChild> info = fuzzySearch(child, 1, 10);
        return info.getList();
    }

    @Override
    public int deleteChild(Integer childId) {
        return delete(childId);
    }
}
