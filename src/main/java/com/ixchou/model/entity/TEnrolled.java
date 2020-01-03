package com.ixchou.model.entity;

import com.ixchou.model.BaseModel;

import java.util.List;

public class TEnrolled extends BaseModel {

    private Byte type;

    private Integer courseId;

    private Integer memberId;

    private List<TPay> pays;

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public List<TPay> getPays() {
        return pays;
    }

    public void setPays(List<TPay> pays) {
        this.pays = pays;
    }
}