package com.bjike.goddess.receivable.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 主营业务收入
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-10 10:19 ]
 * @Description: [ 主营业务收入 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "receivable_mainincome")
public class MainIncome extends BaseEntity {

    /**
     * 时间
     */
    @Column(name = "time", nullable = false, columnDefinition = "DATE   COMMENT '时间'")
    private LocalDate time;

    /**
     * 名字
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '名字'")
    private String name;

    /**
     * 金额
     */
    @Column(name = "money", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '金额'")
    private Double money;


    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}