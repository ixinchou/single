package com.ixchou.services.impl;

import com.ixchou.mappings.TChildMapper;
import com.ixchou.mappings.TMemberMapper;
import com.ixchou.model.entity.TChild;
import com.ixchou.model.entity.TMember;
import com.ixchou.model.vo.ChildVo;
import com.ixchou.services.IChildService;
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
public class ChildServiceImpl implements IChildService {

    @Resource
    private TMemberMapper memberMapper;
    @Resource
    private TChildMapper childMapper;

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
        TChild exists = childMapper.selectByMemberAndName(child);
        if (null == exists) {
            // 不存在相同名字的才添加
            child.setBirthday(vo.getBirthday());
            child.setMemberId(member.getId());
            child.setName(vo.getName());
            child.setRegisterTime(new Date());
            child.setSex(vo.getSex() > 0);
            return childMapper.insertSelective(child);
        }
        return -1;
    }

    @Override
    public List<TChild> find(String sessionId) {
        TMember member = memberMapper.selectBySessionId(sessionId);
        if (null == member) {
            return new ArrayList<>();
        }
        return childMapper.selectByMemberId(member.getId());
    }

    @Override
    public int deleteChild(Integer childId) {
        return childMapper.deleteByPrimaryKey(childId);
    }
}
