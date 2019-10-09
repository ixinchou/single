package com.ixchou.controller;

import com.github.pagehelper.PageInfo;
import com.ixchou.services.IBaseService;
import com.ixchou.services.impl.BaseServiceImpl;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;

/**
 * <b>Author</b>: Xiang Liguo<br/>
 * <b>Date</b>: 2019/10/08 16:44<br/>
 * <b>Version</b>: 1.0<br/>
 * <b>Subject</b>: 附带所有增删改查方法的 Controller 基类<br/>
 * <b>Description</b>:
 */
public abstract class AbstractBaseController<T> {

    private IBaseService<T> service;
    //private Class<T> entityClass;

    //@SuppressWarnings("unchecked")
    public AbstractBaseController() {
        //entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * 获取一个未经初始化实际内容的空对象
     */
    protected T _getEmptyObject() {
        return service.getEmptyObject();
    }

    /**
     * 根据属性值进行查询
     */
    protected T _query(String propertyName, Object value) {
        return service.query(propertyName, value);
    }

    /**
     * 插入新数据
     */
    protected int _insert(T entity) {
        return service.insert(entity);
    }

    /**
     * 更改数据
     */
    protected int _update(T entity) {
        return service.update(entity);
    }

    /**
     * 删除数据
     */
    protected int _delete(Integer id) {
        return service.delete(id);
    }

    /**
     * 根据主键查询
     */
    protected T _select(Integer id) {
        return service.select(id);
    }

    /**
     * 分页列取
     */
    protected PageInfo<T> _list(Integer pageIndex, Integer pageSize) {
        return service.list(pageIndex, pageSize);
    }

    /**
     * 分页模糊查询
     */
    protected PageInfo<T> _fuzzySearch(T query, Integer pageIndex, Integer pageSize) {
        return service.fuzzySearch(query, pageIndex, pageSize);
    }

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void postConstruct() {
        try {
            Field[] fields = getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object object = field.get(this);
                if (object instanceof BaseServiceImpl) {
                    this.service = (IBaseService<T>) object;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
