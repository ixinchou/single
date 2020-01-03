package com.ixchou.model.vo;

import java.math.BigDecimal;

/**
 * <b>Author</b>: Xiang Liguo<br/>
 * <b>Date</b>: 2020/01/02 15:52<br/>
 * <b>Version</b>: 1.0<br/>
 * <b>Subject</b>: 报名<br/>
 * <b>Description</b>:
 */
public class EnrolledVo {
    // 报名类型：1=给自己报名，2=给孩子报名
    private Byte type;
    private Integer courseId;
    private String sessionId;
    private Integer childId;
    private BigDecimal price;

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getChildId() {
        return childId;
    }

    public void setChildId(Integer childId) {
        this.childId = childId;
    }
}
