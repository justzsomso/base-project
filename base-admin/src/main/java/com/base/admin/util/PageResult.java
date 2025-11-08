package com.base.admin.util;

import java.util.List;

/**
 * 分页结果封装类
 */
public class PageResult<T> {
    /**
     * 当前页数据
     */
    private List<T> data;
    
    /**
     * 总记录数
     */
    private long total;
    
    /**
     * 每页记录数
     */
    private int pageSize;
    
    /**
     * 当前页码
     */
    private int pageNum;
    
    /**
     * 总页数
     */
    private int totalPages;

    public PageResult() {
    }

    public PageResult(List<T> data, long total, int pageSize, int pageNum) {
        this.data = data;
        this.total = total;
        this.pageSize = pageSize;
        this.pageNum = pageNum;
        this.totalPages = (int) Math.ceil((double) total / pageSize);
    }

    // Getters and Setters
    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}