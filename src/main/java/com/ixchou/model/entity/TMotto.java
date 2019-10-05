package com.ixchou.model.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class TMotto {
    private Integer id;

    @ApiModelProperty(hidden = true)
    private Boolean isDeleted;

    @ApiModelProperty(hidden = true)
    private Integer postMember;

    @ApiModelProperty(hidden = true)
    private Integer updateTimes;

    @ApiModelProperty(hidden = true)
    private Date postTime;

    @ApiModelProperty(hidden = true)
    private Date lastUpdate;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

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

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}