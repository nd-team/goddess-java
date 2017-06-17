package com.bjike.goddess.supplier.vo;

import java.io.Serializable;

/**
 * @Author: [dengjunren]
 * @Date: [2017-06-12 11:49]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SupplierInfoCollectTitleVO {

    /**
     * 汇总值
     */
    private Integer number;

    /**
     * 汇总字段
     */
    private String title;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
