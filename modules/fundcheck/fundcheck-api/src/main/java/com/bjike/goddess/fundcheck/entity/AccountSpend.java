package com.bjike.goddess.fundcheck.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 账务支出
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-01 02:02 ]
 * @Description: [ 账务支出 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "fundcheck_accountspend")
public class AccountSpend extends BaseEntity {

    /**
     * 日期
     */
    @Column(name = "date", nullable = false, columnDefinition = "DATE   COMMENT '日期'")
    private LocalDate date;

    /**
     * 营业费用
     */
    @Column(name = "operatExpenses", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '营业费用'")
    private Double operatExpenses;

    /**
     * 支付给股东(还款)
     */
    @Column(name = "payStock", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '支付给股东(还款)'")
    private Double payStock;

    /**
     * 其他支出
     */
    @Column(name = "otherSpend", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '其他支出'")
    private Double otherSpend;

    /**
     * 合计
     */
    @Column(name = "total", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '合计'")
    private Double total;


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getOperatExpenses() {
        return operatExpenses;
    }

    public void setOperatExpenses(Double operatExpenses) {
        this.operatExpenses = operatExpenses;
    }

    public Double getPayStock() {
        return payStock;
    }

    public void setPayStock(Double payStock) {
        this.payStock = payStock;
    }

    public Double getOtherSpend() {
        return otherSpend;
    }

    public void setOtherSpend(Double otherSpend) {
        this.otherSpend = otherSpend;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}