package com.ixchou.model.entity;

import com.ixchou.model.BaseModel;

public class TMotto extends BaseModel {

    private Integer postMember;

    private Integer updateTimes;

    private TContent content;

    public Integer getPostMember() {
        return postMember;
    }

    public void setPostMember(Integer postMember) {
        this.postMember = postMember;
    }

    public Integer getUpdateTimes() {
        return updateTimes;
    }

    public void setUpdateTimes(Integer updateTimes) {
        this.updateTimes = updateTimes;
    }

    public TContent getContent() {
        return content;
    }

    public void setContent(TContent content) {
        this.content = content;
    }
}