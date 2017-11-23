package com.bjike.goddess.reportmanagement.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 现金流量资料表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-20 03:02 ]
 * @Description: [ 现金流量资料表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "reportmanagement_cashflowdata")
public class CashFlowData extends BaseEntity {

    /**
     * 开始时间
     */
    @Column(name = "startTime", nullable = false, columnDefinition = "DATE   COMMENT '开始时间'")
    private LocalDate startTime;

    /**
     * 结束时间
     */
    @Column(name = "endTime", nullable = false, columnDefinition = "DATE   COMMENT '结束时间'")
    private LocalDate endTime;

    /**
     * 资料
     */
    @Column(name = "data", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '资料'")
    private String data;

    /**
     * 行次
     */
    @Column(name = "num", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '行次'")
    private Integer num;

    /**
     * 金额
     */
    @Column(name = "money", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '金额'")
    private Double money;


    public LocalDate getstartTime() {
        return startTime;
    }

    public void setstartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}