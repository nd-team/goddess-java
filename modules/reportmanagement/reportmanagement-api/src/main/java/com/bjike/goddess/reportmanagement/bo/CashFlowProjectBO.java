package com.bjike.goddess.reportmanagement.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 现金流量项目表业务传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-20 03:06 ]
 * @Description: [ 现金流量项目表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CashFlowProjectBO extends BaseBO {

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 项目
     */
    private String projectName;

    /**
     * 行次
     */
    private Integer num;

    /**
     * 金额
     */
    private Double money;

    public CashFlowProjectBO() {
    }

    public CashFlowProjectBO(String projectName, Integer num, Double money, String id) {
        this.projectName = projectName;
        this.num = num;
        this.money = money;
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String StartTime) {
        this.startTime = StartTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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
}