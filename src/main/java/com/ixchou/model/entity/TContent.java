package com.ixchou.model.entity;

import com.ixchou.model.BaseModel;

public class TContent extends BaseModel {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}