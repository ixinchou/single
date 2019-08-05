package com.ixchou.model.vo;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/08/05 00:47<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: 微信登录注册<br/>
 * <b>Description</b>:
 */
public class WxRegistryVo {

    private String signature;
    private String encryptedData;
    private String iv;
    private String nickName;
    private byte gender;
    private String loginCode;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public byte getGender() {
        return gender;
    }

    public void setGender(byte gender) {
        this.gender = gender;
    }

    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    @Override
    public String toString() {
        return "WxRegistryVo{" +
                "signature='" + signature + '\'' +
                ", encryptedData='" + encryptedData + '\'' +
                ", iv='" + iv + '\'' +
                ", nickName='" + nickName + '\'' +
                ", gender=" + gender +
                ", loginCode='" + loginCode + '\'' +
                '}';
    }
}
