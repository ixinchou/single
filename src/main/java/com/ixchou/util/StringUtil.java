package com.ixchou.util;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/08/05 01:02<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: <br/>
 * <b>Description</b>:
 */
public class StringUtil {

    /**
     * 判断字符串是否为空
     *
     * @param text 需要判断的字符串
     * @return 返回字符串是否为空
     */
    public static boolean isEmpty(String text) {
        return null == text || "".equals(text);
    }
}
