package com.bjike.goddess.fundcheck.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 期初余额
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-05 03:34 ]
 * @Description: [ 期初余额 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "fundcheck_beginbalance")
public class BeginBalance extends BaseEntity {

    /**
     * 日期
     */
    @Column(name = "date", nullable = false, columnDefinition = "DATE   COMMENT '日期'")
    private LocalDate date;

    /**
     * 期初余额
     */
    @Column(name = "beginBalance", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '期初余额'")
    private Double beginBalance;



    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getBeginBalance() {
        return beginBalance;
    }

    public void setBeginBalance(Double beginBalance) {
        this.beginBalance = beginBalance;
    }
}