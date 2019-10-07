package com.ixchou.util.http.response;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/08/04 18:51<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: <br/>
 * <b>Description</b>:
 */
public enum HttpCode {
    /**
     * 成功
     */
    Success("000", "成功"),
    /**
     * 未知内容，需要手动填写
     */
    Unknown("", ""),
    /**
     * 404
     */
    Err404("404", "您的请求不存在！"),
    /**
     * 解密手机号码时 session id 不能为空
     */
    WxLoginCodeNullOfPhone("600", "解密手机号码时 session 信息不能为空"),
    /**
     * 查询微信用户时 session id 不能为空
     */
    WxLoginCodeNullOfQuery("601", "查询用户信息时 session 信息不能为空"),
    /**
     * 获取微信登录信息失败
     */
    WxGetSessionFailure("602", "获取微信登录信息失败"),
    /**
     * SessionId 不能为空
     */
    MemberSessionIdNull("650", "Session 不能为空"),
    /**
     * 您的名字不能为空
     */
    MemberNameNull("651", "名字不能为空"),
    /**
     * 请先绑定您的微信
     */
    MemberNotBind("652", "请先绑定您的微信"),
    /**
     * 已有同名孩子的信息
     */
    MemberChildExist("653", "已有同名孩子的信息"),
    /**
     * 添加校训失败，请联系管理员
     */
    MottoInsertFailure("660", "添加校训失败"),
    /**
     * 请使用更改方式
     */
    MottoInsertUseUpdate("661", "请改用更改方式"),
    /**
     * 校训内容修改失败
     */
    MottoUpdateFailure("662", "校训内容修改失败"),
    /**
     * 请改用添加校训方式
     */
    MottoUpdateUseInsert("663", "请改用添加校训方式"),
    /**
     * 暂时没有校训内容，请先添加
     */
    MottoNotExist("654", "暂时没有校训内容，请先添加"),
    /**
     * 操作失败
     */
    Failure("999", "操作失败");

    HttpCode(String code, String reason) {
        this.code = code;
        this.reason = reason;
    }

    private String code;
    private String reason;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
