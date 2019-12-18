package com.ixchou.model.entity;

import com.ixchou.model.BaseModel;

public class TCourse extends BaseModel {

    private String name;

    private String classTime;

    private TAttachment cover;

    private String description;

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

    public TAttachment getCover() {
        return cover;
    }

    public void setCover(TAttachment cover) {
        this.cover = cover;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}