package com.bjike.goddess.reportmanagement.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 现金流量资料表业务传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-20 03:02 ]
 * @Description: [ 现金流量资料表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CashFlowDataBO extends BaseBO {

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 资料
     */
    private String data;

    /**
     * 行次
     */
    private Integer num;

    /**
     * 金额
     */
    private Double money;

    public CashFlowDataBO() {
    }

    public CashFlowDataBO(String data, Integer num, Double money, String id) {
        this.data = data;
        this.num = num;
        this.money = money;
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
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


    @Override
    public String toString() {
        return "CashFlowDataBO{" +
                "startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", data='" + data + '\'' +
                ", num=" + num +
                ", money=" + money +
                '}';
    }
}