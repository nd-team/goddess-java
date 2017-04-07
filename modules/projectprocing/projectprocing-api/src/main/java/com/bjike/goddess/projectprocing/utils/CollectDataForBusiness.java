package com.bjike.goddess.projectprocing.utils;

import java.io.Serializable;

/**
 * @Author: [tanghaixiang]
 * @Date: [2017-03-31 20:53]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CollectDataForBusiness implements Serializable {

    /**
     * 业务方向科目名
     */
    private String name;

    /**
     * 个数
     */
    private Integer counts;

    /**
     * 结算金额
     */
    private Double settleMoney;

    /**
     * 未结算金额
     */
    private Double noSettleMoney;

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

    public Double getSettleMoney() {
        return settleMoney;
    }

    public void setSettleMoney(Double settleMoney) {
        this.settleMoney = settleMoney;
    }

    public Double getNoSettleMoney() {
        return noSettleMoney;
    }

    public void setNoSettleMoney(Double noSettleMoney) {
        this.noSettleMoney = noSettleMoney;
    }
}
