package com.ixchou.util.http.response;

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

    private String code;
    private String message;
    private long timestamp = System.currentTimeMillis();
    private T data;

    public HttpResponse() {

    }

    public HttpResponse(HttpCode code) {
        this(code, null);
    }

    private HttpResponse(T data) {
        this(HttpCode.Success, data);
    }

    private HttpResponse(HttpCode code, T data) {
        this.code = code.getCode();
        this.message = code.getReason();
        this.data = data;
    }

    /**
     * 返回空内容的成功操作
     *
     * @param message 消息内容
     */
    public static HttpResponse success(String message) {
        return new HttpResponse(fixUnknownCode(HttpCode.Success, message));
    }

    private static HttpCode fixUnknownCode(HttpCode code, String message) {
        HttpCode unknown = HttpCode.Unknown;
        unknown.setReason(message);
        unknown.setCode(code.getCode());
        return unknown;
    }

    /**
     * 返回带数据的成功操作
     *
     * @param data 数据
     */
    public static <T> HttpResponse success(T data, String message) {
        return new HttpResponse<>(fixUnknownCode(HttpCode.Success, message), data);
    }

    /**
     * 返回带数据的成功操作
     *
     * @param data 数据
     */
    public static <T> HttpResponse success(T data) {
        return new HttpResponse<>(data);
    }

    /**
     * 返回操作失败
     *
     * @param code 消息错误码
     */
    public static HttpResponse failure(HttpCode code) {
        return new HttpResponse(code);
    }

    /**
     * 返回操作失败
     *
     * @param code 消息错误码
     */
    public static HttpResponse failure(HttpCode code, String message) {
        return failure(fixUnknownCode(code, message));
    }

    /**
     * 返回操作失败
     *
     * @param message 消息内容
     */
    public static HttpResponse failure(String message) {
        return new HttpResponse(fixUnknownCode(HttpCode.Failure, message));
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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
