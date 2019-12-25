package com.ixchou.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ixchou.model.BaseModel;

public class TCourse extends BaseModel {

    private String name;

    private Integer classType;

    private String classTime;

    private Integer fee;

    private Byte rebate;

    private Boolean showOriginalPrice;

    @JsonIgnore
    private Integer coverId;

    private TAttachment cover;

    @JsonIgnore
    private Integer contentId;

    private TContent content;

    private TTerm term;

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

    public Integer getClassType() {
        return classType;
    }

    public void setClassType(Integer classType) {
        this.classType = classType;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public Byte getRebate() {
        return rebate;
    }

    public void setRebate(Byte rebate) {
        this.rebate = rebate;
    }

    public Boolean getShowOriginalPrice() {
        return showOriginalPrice;
    }

    public void setShowOriginalPrice(Boolean showOriginalPrice) {
        this.showOriginalPrice = showOriginalPrice;
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

    public TTerm getTerm() {
        return term;
    }

    public void setTerm(TTerm term) {
        this.term = term;
    }
}