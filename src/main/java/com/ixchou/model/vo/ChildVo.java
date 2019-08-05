package com.ixchou.model.vo;

import java.util.Date;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/08/05 17:30<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: 孩子信息相关 vo<br/>
 * <b>Description</b>:
 */
public class ChildVo {

    private byte sex;
    private String name;
    private String sessionId;

    public byte getSex() {
        return sex;
    }

    public void setSex(byte sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
