package com.ixchou.model.vo;

import java.util.List;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/12/21 10:40<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: 个人风采相关VO<br/>
 * <b>Description</b>:
 */
public class MomentVo {
    private int id;
    private int content;
    private byte type;
    private String title;
    private List<Integer> attachments;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContent() {
        return content;
    }

    public void setContent(int content) {
        this.content = content;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Integer> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Integer> attachments) {
        this.attachments = attachments;
    }
}
