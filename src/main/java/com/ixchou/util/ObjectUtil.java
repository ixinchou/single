package com.ixchou.util;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * <b>Author</b>: Xiang Liguo<br/>
 * <b>Date</b>: 2019/09/29 08:37<br/>
 * <b>Version</b>: 1.0<br/>
 * <b>Subject</b>: 实体对象属性相关工具类<br/>
 * <b>Description</b>: 判断属性是否为空时，会自动忽略掉所有静态属性的判断
 */
public class ObjectUtil {

    /**
     * 判断对象是否为空
     *
     * @param entity 实体对象
     * @param <T>    对象的类型
     * @return 返回对象是否为空
     */
    @SuppressWarnings("WeakerAccess")
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
        Class<?> clazz = object.getClass();
        while (!Object.class.equals(clazz)) {
            try {
                Field field = clazz.getDeclaredField(propertyName);
                if (null != field) {
                    return getPropertyValue(object, field);
                }
            } catch (Exception e) {
//                e.printStackTrace();
            }
            clazz = clazz.getSuperclass();
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
        Class<?> clazz = object.getClass();
        while (!Object.class.equals(clazz)) {
            try {
                Field field = clazz.getDeclaredField(propertyName);
                if (null != field) {
                    setPropertyValue(object, field, value);
                    return;
                }
            } catch (Exception e) {
//                e.printStackTrace();
            }
            clazz = clazz.getSuperclass();
        }
    }

    /**
     * 设置属性值
     */
    private static void setPropertyValue(@NonNull Object object, @NonNull Field field, @Nullable Object value) throws IllegalAccessException {
        field.setAccessible(true);
        field.set(object, value);
    }

    /**
     * 获取属性值
     */
    private static Object getPropertyValue(@NonNull Object object, @NonNull Field field) throws IllegalAccessException {
        field.setAccessible(true);
        return field.get(object);
    }

    /**
     * 判断实体对象的某个属性是否为空
     *
     * @param entity   实体对象
     * @param property 属性名称
     * @param <T>      对象的类型
     * @return 返回是否为空
     */
    @SuppressWarnings("unused")
    public static <T> boolean isPropertyNull(T entity, String property) {
        Object value = getPropertyValue(entity, property);
        if (null == value) {
            return true;
        }
        try {
            Field field = entity.getClass().getField(property);
            return isPropertyNull(field, entity, true);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return true;
    }

    private static boolean isPropertyNull(Field field, Object object, boolean ignore0LengthString) throws IllegalAccessException {
        if (Modifier.isStatic(field.getModifiers())) {
            // 忽略静态属性
            return true;
        }
        field.setAccessible(true);
        // 属性值不为 null
        if (null != field.get(object)) {
            // 如果属性类型是字符串，还需要判断字符串值是否为 ''
            if (field.getType().equals(String.class)) {
                // 判断字符串是否为空
                String value = (String) field.get(object);
                // 字符串不为 null 或 '' 则说明对象有属性值，返回 false
                // 不忽略空值字符串时，只判断字符串是否为null，不判断字符串是否为''
                return ignore0LengthString ? StringUtil.isEmpty(value) : null == value;
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
        return isObjectNull(entity, true);
    }

    /**
     * 判断实体对象的所有属性是否都为 null
     *
     * @param entity              实体对象
     * @param ignore0LengthString 判断为空时是否忽略长度为0(非null字符串)的字符串
     * @param <T>                 对象的类型
     * @return 如果所有属性都为 null 则返回 true，只要某个属性不为 null 则返回 false
     */
    @SuppressWarnings("WeakerAccess")
    public static <T> boolean isObjectNull(T entity, boolean ignore0LengthString) {
        if (isNull(entity)) {
            // 如果对象本身就为空则返回 true
            return true;
        }
        Class<?> clazz = entity.getClass();
        while (!Object.class.equals(clazz)) {
            try {
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    if (!isPropertyNull(field, entity, ignore0LengthString)) {
                        return false;
                    }
                }
            } catch (Exception e) {
//                e.printStackTrace();
            }
            clazz = clazz.getSuperclass();
        }
        return true;
    }
}
