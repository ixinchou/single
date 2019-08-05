package com.ixchou.model.vo;

import com.ixchou.model.entity.TMember;

import java.util.Date;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/08/05 13:03<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: <br/>
 * <b>Description</b>:
 */
public class MemberVo {

    private Boolean isUploadable;

    private Boolean wxSex;

    private Date lastLogin;

    private Date registerTime;

    private String phone;

    private String sessionId;

    private String userName;

    private String wxNickName;

    public MemberVo() {
        super();
    }

    public MemberVo(TMember member) {
        isUploadable = member.getIsUploadable();
        wxSex = member.getWxSex();
        lastLogin = member.getLastLogin();
        registerTime = member.getRegisterTime();
        phone = member.getPhone();
        sessionId = member.getSessionId();
        userName = member.getUserName();
        wxNickName = member.getWxNickName();
    }

    public Boolean getUploadable() {
        return isUploadable;
    }

    public void setUploadable(Boolean uploadable) {
        isUploadable = uploadable;
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
        this.phone = phone;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWxNickName() {
        return wxNickName;
    }

    public void setWxNickName(String wxNickName) {
        this.wxNickName = wxNickName;
    }
}
