package com.ixchou.model.vo;

/**
 * <b>Author</b>: Xiang Liguo<br/>
 * <b>Date</b>: 2019/12/19 13:12<br/>
 * <b>Version</b>: 1.0<br/>
 * <b>Subject</b>: 课程<br/>
 * <b>Description</b>:
 */
public class CourseVo {
    private int id;
    private String name;
    private String classType;
    private int classFee;
    private String classTime;
    private int cover;
    private int content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public int getClassFee() {
        return classFee;
    }

    public void setClassFee(int classFee) {
        this.classFee = classFee;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    public int getCover() {
        return cover;
    }

    public void setCover(int cover) {
        this.cover = cover;
    }

    public int getContent() {
        return content;
    }

    public void setContent(int content) {
        this.content = content;
    }
}
