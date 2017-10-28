package com.bjike.goddess.receivable.vo;

/**
 * 主营业务收入表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-10 10:19 ]
 * @Description: [ 主营业务收入表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MainIncomeVO {

    /**
     * id
     */
    private String id;
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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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