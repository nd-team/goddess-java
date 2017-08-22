package com.bjike.goddess.staffing.vo;

/**
 * @Author: [chenjunhao]
 * @Date: [2017-08-18 18:55]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class PriceVO {
    /**
     * 单价
     */
    private String price;
    /**
     * 计划成本
     */
    private String plan;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }
}
