package com.ixchou.util.response;

import java.io.Serializable;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/08/04 18:47<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: 服务端返回内容结构<br/>
 * <b>Description</b>:
 */
public class HttpResponse<T> implements Serializable {

    private static final long serialVersionUID = -1L;

    public static final String SUCCESS = "Success";

    private String code;
    private String message;
    private T data;

    public HttpResponse() {

    }

    public HttpResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public HttpResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 返回空内容的成功操作
     *
     * @param message 消息内容
     */
    public static HttpResponse success(String message) {
        return new HttpResponse(HttpCode.Success, message);
    }

    /**
     * 返回带数据的成功操作
     *
     * @param message    消息内容
     * @param singleData 数据
     */
    public static <T> HttpResponse success(String message, T singleData) {
        return new HttpResponse<>(HttpCode.Success, message, singleData);
    }

    /**
     * 返回带数据的成功操作
     *
     * @param singleData 数据
     */
    public static <T> HttpResponse success(T singleData) {
        return new HttpResponse<>(HttpCode.Success, SUCCESS, singleData);
    }

    /**
     * 返回操作失败
     *
     * @param message 消息内容
     */
    public static HttpResponse failure(String message) {
        return new HttpResponse(HttpCode.Failure, message);
    }

    /**
     * 返回指定类型的失败消息
     *
     * @param code    错误码
     * @param message 消息描述内容
     */
    public static HttpResponse failure(String code, String message) {
        return new HttpResponse(code, message);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
