package com.ixchou.model.entity;

import com.ixchou.model.BaseModel;

import java.util.List;

public class TMoment extends BaseModel {

    private Byte type;

    private Integer contentId;

    private String title;

    private TContent content;

    private List<TAttachment> attachments;

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public TContent getContent() {
        return content;
    }

    public void setContent(TContent content) {
        this.content = content;
    }

    public List<TAttachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<TAttachment> attachments) {
        this.attachments = attachments;
    }
}