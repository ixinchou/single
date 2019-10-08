package com.ixchou.services;

import com.github.pagehelper.PageInfo;

/**
 * <b>Author</b>: Xiang Liguo<br/>
 * <b>Date</b>: 2019/10/08 13:45<br/>
 * <b>Version</b>: 1.0<br/>
 * <b>Subject</b>: Service 基类方法接口定义<br/>
 * <b>Description</b>:
 */
public interface IBaseService<T> {
    /**
     * True 的 byte 值
     */
    Byte True = 1;
    /**
     * False 的 byte 值
     */
    Byte False = 0;

    /**
     * 插入新纪录
     */
    int insert(T entity);

    /**
     * 根据 id 查询纪录
     */
    T select(Integer id);

    /**
     * 根据某个属性查询
     */
    T query(String propertyName, Object value);

    /**
     * 分页选择
     */
    PageInfo<T> list(Integer pageIndex, Integer pageSize);

    /**
     * 分页模糊查询
     */
    PageInfo<T> fuzzySearch(T query, Integer pageIndex, Integer pageSize);

    /**
     * 更改纪录
     */
    int update(T entity);
}
