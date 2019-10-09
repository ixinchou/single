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

    private static final String PK = "id";

    /**
     * 添加内容
     */
    protected HttpResponse __insert(T entity) {
        T exist = _query(checkExistProperty(), ObjectUtil.getPropertyValue(entity, checkExistProperty()));
        if (null != exist) {
            return HttpResponse.failure(existCode());
        }
        if (_insert(entity) > 0) {
            return HttpResponse.success(entity, insertSuccess());
        }
        return HttpResponse.failure(HttpCode.DatabaseInsertFail);
    }

    protected abstract String checkExistProperty();

    protected abstract String insertSuccess();

    protected abstract String deleteSuccess();

    protected abstract String updateSuccess();

    protected abstract HttpCode existCode();

    protected abstract void setFuzzyProperties(T entity, String queryValue);

    /**
     * 删除内容
     */
    protected HttpResponse __delete(Integer id) {
        if (_delete(id) > 0) {
            return HttpResponse.success(deleteSuccess());
        }
        return HttpResponse.failure(HttpCode.DatabaseUpdateFail);
    }

    /**
     * 更改内容
     */
    protected HttpResponse __update(T entity) {
        T exist = _query(checkExistProperty(), ObjectUtil.getPropertyValue(entity, checkExistProperty()));
        if (null != exist) {
            Integer existId = (Integer) ObjectUtil.getPropertyValue(exist, PK);
            Integer updateId = (Integer) ObjectUtil.getPropertyValue(entity, PK);
            assert existId != null;
            if (!existId.equals(updateId)) {
                return HttpResponse.failure(existCode());
            }
        }
        if (_update(entity) > 0) {
            return HttpResponse.success(entity, updateSuccess());
        }
        return HttpResponse.failure(HttpCode.DatabaseUpdateFail);
    }

    /**
     * 分页查询内容
     */
    protected HttpResponse __list(Integer pageIndex, Integer pageSize, String query) {
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
