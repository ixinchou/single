package com.ixchou.model.entity;

import com.ixchou.model.BaseModel;

public class TTerm extends BaseModel {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}