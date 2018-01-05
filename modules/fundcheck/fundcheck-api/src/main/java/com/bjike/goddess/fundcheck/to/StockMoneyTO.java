package com.bjike.goddess.fundcheck.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 收到股东款
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-30 04:51 ]
 * @Description: [ 收到股东款 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class StockMoneyTO extends BaseTO {

    /**
     * 日期
     */
    @NotBlank(message = "日期不能为空",groups = {ADD.class, EDIT.class})
    private String date;
    /**
     * 一级
     */
    @NotBlank(message = "一级不能为空",groups = {ADD.class, EDIT.class})
    private String firstName;
    /**
     * 二级
     */
    @NotBlank(message = "二级不能为空",groups = {ADD.class, EDIT.class})
    private String secondName;

    /**
     * 股东名
     */
    @NotBlank(message = "股东名不能为空",groups = {ADD.class, EDIT.class})
    private String stockName;

    /**
     * 金额
     */
    @NotNull(message = "金额不能为空",groups = {ADD.class, EDIT.class})
    private Double money;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}