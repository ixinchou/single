package com.ixchou.model.entity;

import com.ixchou.model.BaseModel;

public class TRelation extends BaseModel {

    private Integer host;

    private Integer attachment;

    public Integer getHost() {
        return host;
    }

    public void setHost(Integer host) {
        this.host = host;
    }

    public Integer getAttachment() {
        return attachment;
    }

    public void setAttachment(Integer attachment) {
        this.attachment = attachment;
    }
}