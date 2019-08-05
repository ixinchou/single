package com.ixchou.model.vo;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/08/05 14:52<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: 需要解密的微信手机信息<br/>
 * <b>Description</b>:
 */
public class WxPhoneEncryptedVo {

    private String encryptedData;
    private String iv;
    private String sessionId;

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

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
