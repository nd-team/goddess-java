package com.bjike.goddess.reimbursementprepare.vo;

/**
 * 资金准备与实际支付差异
 *
 * @Author: [chenjunhao]
 * @Date: [2017-07-05 08:53]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DifferencesVO {
    /**
     * id
     */
    private String id;
    /**
     * 时间
     */
    private String time;
    /**
     * 科目
     */
    private String subject;
    /**
     * 项目组
     */
    private String projectGroup;
    /**
     * 本月准备金
     */
    private Double currentReserve;
    /**
     * 实际支付金额
     */
    private Double payMoney;
    /**
     * 差异
     */
    private Double difference;
    /**
     * 增长率
     */
    private String growth;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public Double getCurrentReserve() {
        return currentReserve;
    }

    public void setCurrentReserve(Double currentReserve) {
        this.currentReserve = currentReserve;
    }

    public Double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    public Double getDifference() {
        return difference;
    }

    public void setDifference(Double difference) {
        this.difference = difference;
    }

    public String getGrowth() {
        return growth;
    }

    public void setGrowth(String growth) {
        this.growth = growth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
