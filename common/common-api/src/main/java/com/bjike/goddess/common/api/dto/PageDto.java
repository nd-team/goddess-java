package com.bjike.goddess.common.api.dto;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [分页数据传输，基础dto继承该类]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public class PageDto {
    private int limit = 10;//每显示数量
    private int page = 1;//当前页

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getPage() {
        return this.page = this.page-1;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
