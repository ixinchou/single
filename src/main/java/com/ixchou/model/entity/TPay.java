package com.ixchou.model.entity;

import com.ixchou.model.BaseModel;

import java.math.BigDecimal;

public class TPay extends BaseModel {

    private Integer enrolledId;

    private Byte isPaid;

    private BigDecimal price;

    public Integer getEnrolledId() {
        return enrolledId;
    }

    public void setEnrolledId(Integer enrolledId) {
        this.enrolledId = enrolledId;
    }

    public Byte getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Byte isPaid) {
        this.isPaid = isPaid;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}