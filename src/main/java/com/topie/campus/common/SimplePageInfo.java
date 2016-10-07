package com.topie.campus.common;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chenguojun on 9/13/16.
 */
public class SimplePageInfo<T> implements Serializable {

    private static final long serialVersionUID = 8551340316666346625L;

    private static final int DEFAULT_PAGESIZE = 8;

    private int pageNum;

    private int pageSize;

    private long total;

    private int pages;

    private List<T> list;

    public SimplePageInfo() {
    }

    public SimplePageInfo(int pageNum, int pageSize, long total, List<T> list) {
        this.pageNum = pageNum;
        this.pageSize = pageSize > 0 ? pageSize : DEFAULT_PAGESIZE;
        this.total = total;
        this.list = list;
        this.pages = (int) (this.total / this.pageSize);
        if (this.total % this.pageSize > 0) {
            this.pages++;
        }
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PageInfo{");
        sb.append("pageNum=").append(pageNum);
        sb.append(", pageSize=").append(pageSize);
        sb.append(", total=").append(total);
        sb.append(", pages=").append(pages);
        sb.append(", list=").append(list);
        sb.append('}');
        return sb.toString();
    }
}
