package com.ixchou.model.vo;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/08/05 16:15<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: 解密之后的微信手机信息<br/>
 * <b>Description</b>:
 */
public class WxPhoneDecryptedVo {

    private String phoneNumber;
    private String purePhoneNumber;
    private String countryCode;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPurePhoneNumber() {
        return purePhoneNumber;
    }

    public void setPurePhoneNumber(String purePhoneNumber) {
        this.purePhoneNumber = purePhoneNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
