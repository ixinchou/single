package com.ixchou.model.vo;

import com.ixchou.model.entity.TMember;
import com.ixchou.services.IBaseService;

import java.util.Date;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/08/05 13:03<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: <br/>
 * <b>Description</b>:
 */
public class MemberVo {

    private Integer memberId;

    private Boolean uploadAble;

    private Boolean wxSex;

    private Date lastLogin;

    private Date registerTime;

    private String phone;

    private String sessionId;

    private String userName;

    private String wxNickName;

    private String wxAvatar;

    public MemberVo() {
        super();
    }

    public MemberVo(TMember member) {
        memberId = member.getId();
        uploadAble = member.getIsUploadAble().equals(IBaseService.True);
        wxSex = member.getWxSex().equals(IBaseService.True);
        lastLogin = member.getLastLoginTime();
        registerTime = member.getCreateTime();
        phone = member.getPhone();
        sessionId = member.getSessionId();
        userName = member.getUserName();
        wxNickName = member.getWxNickName();
        wxAvatar = member.getWxAvatar();
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Boolean getUploadAble() {
        return uploadAble;
    }

    public void setUploadAble(Boolean uploadAble) {
        this.uploadAble = uploadAble;
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

    public String getWxAvatar() {
        return wxAvatar;
    }

    public void setWxAvatar(String wxAvatar) {
        this.wxAvatar = wxAvatar;
    }
}
