package com.bjike.goddess.checkfunds.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 银企对账（核对）
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-10 03:53 ]
 * @Description: [ 银企对账（核对） ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BankReconciliationTO extends BaseTO {

    /**
     * 用户名
     */
    @NotBlank(groups = {ADD.class}, message = "用户名不能为空")
    private String name;
    /**
     * 对账年份
     */
    @NotNull(groups = {ADD.class}, message = "对账年份不能为空")
    private Integer year;
    /**
     * 对账月份
     */
    @NotNull(groups = {ADD.class}, message = "对账月份不能为空")
    private Integer month;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}