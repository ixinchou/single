package com.ixchou.model.entity;

import java.util.Date;

public class TMember {
    private Integer id;

    private Boolean isDeleted;

    private Boolean isUploadAble;

    private Boolean wxSex;

    private Date lastLoginTime;

    private Date registerTime;

    private String phone;

    private String userName;

    private String sessionId;

    private String wxId;

    private String wxSession;

    private String wxNickName;

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

    public Boolean getIsUploadAble() {
        return isUploadAble;
    }

    public void setIsUploadAble(Boolean isUploadAble) {
        this.isUploadAble = isUploadAble;
    }

    public Boolean getWxSex() {
        return wxSex;
    }

    public void setWxSex(Boolean wxSex) {
        this.wxSex = wxSex;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId == null ? null : sessionId.trim();
    }

    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId == null ? null : wxId.trim();
    }

    public String getWxSession() {
        return wxSession;
    }

    public void setWxSession(String wxSession) {
        this.wxSession = wxSession == null ? null : wxSession.trim();
    }

    public String getWxNickName() {
        return wxNickName;
    }

    public void setWxNickName(String wxNickName) {
        this.wxNickName = wxNickName == null ? null : wxNickName.trim();
    }
}