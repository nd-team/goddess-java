package com.bjike.goddess.firmreward.vo;

/**
 * 奖金预算表现层对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-12 05:10 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BonusBudgetVO {

    /**
     * id
     */
    private String id;
    /**
     * 月份
     */
    private String month;

    /**
     * 总预算
     */
    private Double totalBudget;

    /**
     * 奖金:经验值
     */
    private String ratio;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(Double totalBudget) {
        this.totalBudget = totalBudget;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }
}