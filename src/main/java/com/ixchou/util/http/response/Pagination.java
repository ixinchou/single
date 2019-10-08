package com.ixchou.util.http.response;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/10/08 23:53<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: 分页信息重新包装<br/>
 * <b>Description</b>:
 */
public class Pagination<T> {
    // 页码
    private int pageIndex;
    // 页大小
    private int pageSize;
    // 当前页记录数
    private int pageRecords;
    // 总页数
    private int totalPages;
    // 总记录数
    private long totalRecords;
    // 数据内容
    private List<T> list;

    public Pagination() {
    }

    public Pagination(PageInfo<T> info) {
        this.pageIndex = info.getPageNum();
        this.pageSize = info.getPageSize();
        this.totalRecords = info.getTotal();
        this.pageRecords = info.getSize();
        this.list = info.getList();
        this.totalPages = info.getPages();
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getPageRecords() {
        return pageRecords;
    }

    public void setPageRecords(int pageRecords) {
        this.pageRecords = pageRecords;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
