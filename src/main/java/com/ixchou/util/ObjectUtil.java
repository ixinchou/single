package com.ixchou.util;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.lang.reflect.Field;

/**
 * <b>Author</b>: Xiang Liguo<br/>
 * <b>Date</b>: 2019/09/29 08:37<br/>
 * <b>Version</b>: 1.0<br/>
 * <b>Subject</b>: 实体对象属性相关工具类<br/>
 * <b>Description</b>:
 */
public class ObjectUtil {

    /**
     * 判断对象是否为空
     *
     * @param entity 实体对象
     * @param <T>    对象的类型
     * @return 返回对象是否为空
     */
    public static <T> boolean isNull(T entity) {
        return null == entity;
    }

    /**
     * 在对象实例中获取指定属性值
     *
     * @param object       对象实例
     * @param propertyName 属性值
     * @return 返回属性
     */
    public static Object getPropertyValue(@Nullable Object object, @NonNull String propertyName) {
        if (isNull(object)) {
            return null;
        }
        try {
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (propertyName.equals(field.getName())) {
                    field.setAccessible(true);
                    return field.get(object);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 给实例对象的指定属性设置新的值
     *
     * @param object       实例对象
     * @param propertyName 属性名称
     * @param value        新的属性值
     */
    public static void setPropertyValue(@Nullable Object object, @NonNull String propertyName, @Nullable Object value) {
        if (isNull(object)) {
            return;
        }
        try {
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (propertyName.equals(field.getName())) {
                    field.setAccessible(true);
                    Class<?> type = field.getType();
                    if (type.getName().equals(Integer.class.getName())) {
                        assert value != null;
                        field.set(object, Integer.valueOf((String) value));
                    } else if (type.getName().equals(Long.class.getName())) {
                        assert value != null;
                        field.set(object, Long.valueOf((String) value));
                    } else {
                        field.set(object, value);
                    }
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断实体对象的某个属性是否为空
     *
     * @param entity   实体对象
     * @param property 属性名称
     * @param <T>      对象的类型
     * @return 返回是否为空
     */
    public static <T> boolean isPropertyNull(T entity, String property) {
        if (isNull(entity)) {
            return true;
        }

        try {
            Field[] fields = entity.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.getName().equals(property)) {
                    // 如果属性名称和指定的相同则判断
                    return isPropertyNull(field, entity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private static boolean isPropertyNull(Field field, Object object) throws IllegalAccessException {
        field.setAccessible(true);
        // 属性值不为 null
        if (null != field.get(object)) {
            // 如果属性类型是字符串，还需要判断字符串值是否为 ''
            if (field.getType().equals(String.class)) {
                // 判断字符串是否为空
                String value = (String) field.get(object);
                // 字符串不为 null 或 '' 则说明对象有属性值，返回 false
                return StringUtil.isEmpty(value);
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断实体对象的所有属性是否都为 null
     *
     * @param entity 实体对象
     * @param <T>    对象的类型
     * @return 如果所有属性都为 null 则返回 true，只要某个属性不为 null 则返回 false
     */
    public static <T> boolean isObjectNull(T entity) {
        if (isNull(entity)) {
            // 如果对象本身就为空则返回 true
            return true;
        }
        try {
            Field[] fields = entity.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (!isPropertyNull(field, entity)) {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
