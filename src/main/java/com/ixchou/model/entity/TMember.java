package com.ixchou.model.entity;

import java.util.Date;

public class TMember {
    private Integer id;

    private Boolean isUploadable;

    private Boolean wxSex;

    private Date lastLogin;

    private Date registerTime;

    private String phone;

    private String sessionId;

    private String userName;

    private String wxId;

    private String wxSession;

    private String wxNickName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIsUploadable() {
        return isUploadable;
    }

    public void setIsUploadable(Boolean isUploadable) {
        this.isUploadable = isUploadable;
    }

    public Boolean getWxSex() {
        return wxSex;
    }

    public void setWxSex(Boolean wxSex) {
        this.wxSex = wxSex;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
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

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId == null ? null : sessionId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
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