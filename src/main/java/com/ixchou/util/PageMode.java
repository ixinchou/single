package com.ixchou.util;

import com.github.miemiedev.mybatis.paginator.domain.PageList;

import java.io.Serializable;
import java.util.List;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/08/04 19:02<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: 分页模式<br/>
 * <b>Description</b>:
 */
public class PageMode<T> implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 查询的页码
     */
    private int pageIndex;
    /**
     * 总记录数
     */
    private int totalCount;
    /**
     * 总页数
     */
    private int totalPages;
    /**
     * 当前查询的记录集合
     */
    private List<T> rows;

    public PageMode() {
    }

    public PageMode(PageList<T> list) {
        this.pageIndex = list.getPaginator().getPage();
        this.totalCount = list.getPaginator().getTotalCount();
        this.totalPages = list.getPaginator().getTotalPages();
        this.rows = list;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
