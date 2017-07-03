package com.bjike.goddess.businessinteraction.bo;

import java.io.Serializable;

/**
 * @Author: [tanghaixiang]
 * @Date: [2017-03-27 15:09]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CollectData implements Serializable{

    /**
     * 汇总字段名
     */
    private String name;

    /**
     * 数量
     */
    private Long counts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCounts() {
        return counts;
    }

    public void setCounts(Long counts) {
        this.counts = counts;
    }
}
