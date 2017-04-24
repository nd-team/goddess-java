package com.bjike.goddess.capability.bo;

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
    private Integer counts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }
}
