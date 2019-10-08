package com.ixchou.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ixchou.aware.SpringContextAware;
import com.ixchou.services.IBaseService;
import com.ixchou.util.ObjectUtil;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * <b>Author</b>: Xiang Liguo<br/>
 * <b>Date</b>: 2019/10/08 16:15<br/>
 * <b>Version</b>: 1.0<br/>
 * <b>Subject</b>: 所有 Service 的基类<br/>
 * <b>Description</b>:
 */
public abstract class BaseServiceImpl<T> implements IBaseService<T> {

    private Class<T> entityClass;
    private String nameSpace;
    private SqlSession session;

    @SuppressWarnings("unchecked")
    BaseServiceImpl() {
        entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        nameSpace = "com.ixchou.mappings." + entityClass.getSimpleName() + "Mapper.";
        String beanName = StringUtils.capitalize(entityClass.getSimpleName() + "Mapper");
        try {
            MapperFactoryBean mapperFactoryBean = SpringContextAware.getBean("&" + beanName);
            session = mapperFactoryBean.getSqlSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int insert(T entity) {
        return session.insert(nameSpace + "insertSelective", entity);
    }

    @Override
    public T select(Integer id) {
        return session.selectOne(nameSpace + "selectByPrimaryKey", id);
    }

    @Override
    public T query(@NonNull String propertyName, @NonNull Object value) {
        try {
            T object = entityClass.newInstance();
            ObjectUtil.setPropertyValue(object, propertyName, value);
            return query(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public T query(T entity) {
        if (ObjectUtil.isObjectNull(entity)) {
            return null;
        }
        List<T> list = fuzzySearchList(entity, 1, 2);
        return (null == list || list.size() < 1) ? null : list.get(0);
    }

    @Override
    public PageInfo<T> list(Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        List<T> list = session.selectList(nameSpace + "list");
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<T> fuzzySearch(T query, Integer pageIndex, Integer pageSize) {
        if (ObjectUtil.isObjectNull(query)) {
            // 如果查询对象的所有属性都为空，则直接查询所有列表
            return list(pageIndex, pageSize);
        }
        return new PageInfo<>(fuzzySearchList(query, pageIndex, pageSize));
    }

    private List<T> fuzzySearchList(T query, Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        return session.selectList(nameSpace + "fuzzySearch", query);
    }

    @Override
    public int update(T entity) {
        return session.update(nameSpace + "updateByPrimaryKeySelective", entity);
    }
}
