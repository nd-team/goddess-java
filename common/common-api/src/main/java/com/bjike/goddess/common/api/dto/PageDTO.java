package com.bjike.goddess.common.api.dto;

import java.io.Serializable;

/**
 * 分页数据传输，基础dto继承该类
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public abstract class PageDTO implements Serializable {
    /**
     * 每显示数量
     */
    protected Integer limit = 10;
    /**
     * 当前页
     */
    protected Integer page = 1;

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getPage() {
        return this.page = this.page - 1;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
