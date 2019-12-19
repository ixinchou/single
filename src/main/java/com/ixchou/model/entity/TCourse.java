package com.ixchou.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ixchou.model.BaseModel;

public class TCourse extends BaseModel {

    private String name;

    private String classType;

    private String classTime;

    private Integer fee;

    @JsonIgnore
    private Integer coverId;

    private TAttachment cover;

    @JsonIgnore
    private Integer contentId;

    private TContent content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public Integer getCoverId() {
        return coverId;
    }

    public void setCoverId(Integer coverId) {
        this.coverId = coverId;
    }

    public TAttachment getCover() {
        return cover;
    }

    public void setCover(TAttachment cover) {
        this.cover = cover;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public TContent getContent() {
        return content;
    }

    public void setContent(TContent content) {
        this.content = content;
    }
}