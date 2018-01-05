package com.bjike.goddess.reimbursementprepare.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 资金准备审核汇总
 *
 * @Author: [chenjunhao]
 * @Date: [2017-05-05 16:30]
 * @Description: [资金准备审核汇总 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class MoneyReadyCountBO extends BaseBO {
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
     * 上月准备金
     */
    private Double lastMonthReserveSum;
    /**
     * 本月准备金
     */
    private Double currentMonthReserveSum;
    /**
     * 差额
     */
    private Double differences;
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

    public Double getLastMonthReserveSum() {
        return lastMonthReserveSum;
    }

    public void setLastMonthReserveSum(Double lastMonthReserveSum) {
        this.lastMonthReserveSum = lastMonthReserveSum;
    }

    public Double getCurrentMonthReserveSum() {
        return currentMonthReserveSum;
    }

    public void setCurrentMonthReserveSum(Double currentMonthReserveSum) {
        this.currentMonthReserveSum = currentMonthReserveSum;
    }

    public Double getDifferences() {
        return differences;
    }

    public void setDifferences(Double differences) {
        this.differences = differences;
    }

    public String getGrowth() {
        return growth;
    }

    public void setGrowth(String growth) {
        this.growth = growth;
    }
}
