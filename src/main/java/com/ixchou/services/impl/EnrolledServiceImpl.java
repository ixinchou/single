package com.ixchou.services.impl;

import com.ixchou.mappings.TMemberMapper;
import com.ixchou.mappings.TPayMapper;
import com.ixchou.model.entity.TEnrolled;
import com.ixchou.model.entity.TMember;
import com.ixchou.model.entity.TPay;
import com.ixchou.model.vo.EnrolledVo;
import com.ixchou.util.StringUtil;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <b>Author</b>: Xiang Liguo<br/>
 * <b>Date</b>: 2020/01/02 15:07<br/>
 * <b>Version</b>: 1.0<br/>
 * <b>Subject</b>: 报名相关<br/>
 * <b>Description</b>:
 */
@Service
@DependsOn("springContextAware")
public class EnrolledServiceImpl extends BaseServiceImpl<TEnrolled> {

    @Resource
    private TMemberMapper memberMapper;

    @Resource
    private TPayMapper payMapper;

    /**
     * 添加报名信息
     */
    public int enroll(EnrolledVo vo) {
        if (StringUtil.isEmpty(vo.getSessionId())) {
            return -1;
        }
        if (vo.getCourseId() <= 0) {
            return -2;
        }
        if (vo.getType() <= 0) {
            return -3;
        }
        TMember member = memberMapper.selectBySessionId(vo.getSessionId());
        if (null == member) {
            return -4;
        }
        TEnrolled enrolled = new TEnrolled();
        enrolled.setCourseId(vo.getCourseId());
        if (vo.getType() == 1) {
            // 给自己报名
            enrolled.setType(vo.getType());
            enrolled.setMemberId(member.getId());
        } else if (vo.getType() == 2) {
            // 给孩子报名
            if (vo.getChildId() <= 0) {
                return -5;
            }
            enrolled.setType(vo.getType());
            enrolled.setMemberId(vo.getChildId());
        }
        int inserted = insert(enrolled);
        if (inserted <= 0) {
            // 插入失败
            return 0;
        }
        // 添加价格信息
        TPay pay = new TPay();
        pay.setEnrolledId(enrolled.getId());
        // 默认未支付
        pay.setIsPaid(False);
        pay.setPrice(vo.getPrice());
        payMapper.insertSelective(pay);
        return 1;
    }
}
