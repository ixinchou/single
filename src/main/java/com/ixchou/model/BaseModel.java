package com.ixchou.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;

/**
 * <b>Author</b>: Xiang Liguo<br/>
 * <b>Date</b>: 2019/12/18 13:38<br/>
 * <b>Version</b>: 1.0<br/>
 * <b>Subject</b>: 所有类的基类<br/>
 * <b>Description</b>:
 */
public class BaseModel implements Serializable {

    private Integer id;

    @JsonIgnore
    private Byte isDeleted;

    @JsonIgnore
    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
