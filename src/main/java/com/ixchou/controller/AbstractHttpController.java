package com.ixchou.controller;

import com.github.pagehelper.PageInfo;
import com.ixchou.util.ObjectUtil;
import com.ixchou.util.StringUtil;
import com.ixchou.util.http.response.HttpCode;
import com.ixchou.util.http.response.HttpResponse;
import com.ixchou.util.http.response.Pagination;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/10/09 23:02<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: 实现 HttpResponse 基本接口的控制器基类<br/>
 * <b>Description</b>:
 */
public abstract class AbstractHttpController<T> extends AbstractBaseController<T> {

    protected static final String PK = "id";

    /**
     * 添加内容
     */
    HttpResponse __insert(T entity) {
        String propertyName = checkSameRecordPropertyName();
        T exist = null;
        if (!StringUtil.isEmpty(propertyName)) {
            exist = _query(propertyName, ObjectUtil.getPropertyValue(entity, propertyName));
        }
        if (null != exist) {
            return HttpResponse.failure(insertExistCode());
        }
        if (_insert(entity) > 0) {
            return HttpResponse.success(entity, insertSuccessMessage());
        }
        return HttpResponse.failure(HttpCode.DatabaseInsertFail);
    }

    /**
     * 查找相同对象的属性，通过这个指定的属性查找对象
     */
    protected abstract String checkSameRecordPropertyName();

    /**
     * 插入成功之后的描述文字
     */
    protected abstract String insertSuccessMessage();

    /**
     * 删除成功之后的描述文字
     */
    protected abstract String deleteSuccessMessage();

    /**
     * 更新成功之后的描述文字
     */
    protected abstract String updateSuccess();

    /**
     * 插入时有相同记录后的错误码
     */
    protected abstract HttpCode insertExistCode();

    /**
     * 模糊查询时设定需要查询的属性
     */
    protected abstract void setFuzzyProperties(T entity, String queryValue);

    /**
     * 删除内容
     */
    HttpResponse __delete(Integer id) {
        if (_delete(id) > 0) {
            return HttpResponse.success(deleteSuccessMessage());
        }
        return HttpResponse.failure(HttpCode.DatabaseDeleteFail);
    }

    /**
     * 更改内容
     */
    HttpResponse __update(T entity) {
        String name = checkSameRecordPropertyName();
        T exist;
        if (StringUtil.isEmpty(name)) {
            exist = _select((Integer) ObjectUtil.getPropertyValue(entity, PK));
        } else {
            exist = _query(checkSameRecordPropertyName(), ObjectUtil.getPropertyValue(entity, checkSameRecordPropertyName()));
        }
        if (null != exist) {
            Integer existId = (Integer) ObjectUtil.getPropertyValue(exist, PK);
            Integer updateId = (Integer) ObjectUtil.getPropertyValue(entity, PK);
            assert existId != null;
            if (!existId.equals(updateId)) {
                return HttpResponse.failure(insertExistCode());
            }
        }
        if (_update(entity) > 0) {
            return HttpResponse.success(entity, updateSuccess());
        }
        return HttpResponse.failure(HttpCode.DatabaseUpdateFail);
    }

    HttpResponse __select(Integer id) {
        T t = _select(id);
        if (null == t) {
            return HttpResponse.failure(HttpCode.DatabaseSelectNone);
        }
        return HttpResponse.success(t);
    }

    /**
     * 分页查询内容
     */
    HttpResponse __list(Integer pageIndex, Integer pageSize, String query) {
        PageInfo<T> info;
        if (StringUtil.isEmpty(query)) {
            info = _list(pageIndex, pageSize);
        } else {
            T entity = _getEmptyObject();
            if (null != entity) {
                setFuzzyProperties(entity, query);
                info = _fuzzySearch(entity, pageIndex, pageSize);
            } else {
                info = _list(pageIndex, pageSize);
            }
        }
        return HttpResponse.success(new Pagination<>(info));
    }
}
