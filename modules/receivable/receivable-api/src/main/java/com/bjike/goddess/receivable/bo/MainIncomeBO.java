package com.bjike.goddess.receivable.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 主营业务收入业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-10 10:19 ]
 * @Description: [ 主营业务收入业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MainIncomeBO extends BaseBO {

    /**
     * 时间
     */
    private String time;

    /**
     * 名字
     */
    private String name;

    /**
     * 金额
     */
    private Double money;


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
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