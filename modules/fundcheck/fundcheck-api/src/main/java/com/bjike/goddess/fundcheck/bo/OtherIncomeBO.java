package com.bjike.goddess.fundcheck.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 其他收入业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-30 04:53 ]
 * @Description: [ 其他收入业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OtherIncomeBO extends BaseBO {

    /**
     * 日期
     */
    private String date;

    /**
     * 类型
     */
    private String type;

    /**
     * 金额
     */
    private Double money;
    /**
     * 类型
     */
    private List<String> typeList;

    /**
     * 金额
     */
    private List<Double> moneyList;

    public List<String> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<String> typeList) {
        this.typeList = typeList;
    }

    public List<Double> getMoneyList() {
        return moneyList;
    }

    public void setMoneyList(List<Double> moneyList) {
        this.moneyList = moneyList;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}