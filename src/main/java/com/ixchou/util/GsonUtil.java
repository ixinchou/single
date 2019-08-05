package com.ixchou.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/08/05 02:27<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: <br/>
 * <b>Description</b>:
 */
public class GsonUtil {

    /**
     * 包含时间格式转换的 gson 对象
     */
    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    public static <T> T fromString(String planText, Class<T> clazz) {
        return gson.fromJson(planText, clazz);
    }

    public static <T> String toString(Object object) {
        return gson.toJson(object);
    }
}
