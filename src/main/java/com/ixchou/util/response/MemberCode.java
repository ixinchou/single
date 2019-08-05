package com.ixchou.util.response;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/08/05 10:01<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: <br/>
 * <b>Description</b>:
 */
public class MemberCode {
    /**
     * 用户不存在
     */
    public static final String MemberNotExists = "650";
    /**
     * 通过sessionId查询时没有指定sessionId
     */
    public static final String SessionIdEmpty = "651";
    /**
     * 名字不能改为空
     */
    public static final String NameCannotUpdateToEmpty = "652";
}
