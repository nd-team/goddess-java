package com.bjike.goddess.firmreward.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 奖金预算
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-12 05:10 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "firmreward_bonusbudget")
public class BonusBudget extends BaseEntity {

    /**
     * 月份
     */
    @Column(name = "month", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '月份'")
    private String month;

    /**
     * 总预算
     */
    @Column(name = "totalBudget", nullable = false, columnDefinition = "DECIMAL(10,2) COMMENT '总预算'")
    private Double totalBudget;

    /**
     * 奖金:经验值
     */
    @Column(name = "ratio", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '奖金:经验值'")
    private String ratio;


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