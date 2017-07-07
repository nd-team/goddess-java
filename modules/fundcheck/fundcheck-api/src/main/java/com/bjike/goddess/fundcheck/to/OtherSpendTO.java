package com.bjike.goddess.fundcheck.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 其他支出
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-01 01:57 ]
 * @Description: [ 其他支出 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OtherSpendTO extends BaseTO {

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