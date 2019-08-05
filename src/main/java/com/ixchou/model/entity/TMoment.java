package com.ixchou.model.entity;

import java.util.Date;

public class TMoment {
    private Integer id;

    private Integer courseid;

    private Integer postMember;

    private String title;

    private String content;

    private Date posttime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseid() {
        return courseid;
    }

    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }

    public Integer getPostMember() {
        return postMember;
    }

    public void setPostMember(Integer postMember) {
        this.postMember = postMember;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getPosttime() {
        return posttime;
    }

    public void setPosttime(Date posttime) {
        this.posttime = posttime;
    }
}