package com.bjike.goddess.reportmanagement.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.reportmanagement.enums.ProfitType;
import com.bjike.goddess.reportmanagement.enums.Type;

/**
 * 利润表
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-22 06:03 ]
 * @Description: [ 利润表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProfitTO extends BaseTO {

    /**
     * 项目
     */
    private String project;

    /**
     * 本月数
     */
    private Double currentMonthAmount;

    /**
     * 本年累计数
     */
    private Double currentYearAmount;

    /**
     * 利润类型
     */
    private ProfitType profitType;

    /**
     * 运算类型
     */
    private Type type;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public ProfitType getProfitType() {
        return profitType;
    }

    public void setProfitType(ProfitType profitType) {
        this.profitType = profitType;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Double getCurrentMonthAmount() {
        return currentMonthAmount;
    }

    public void setCurrentMonthAmount(Double currentMonthAmount) {
        this.currentMonthAmount = currentMonthAmount;
    }

    public Double getCurrentYearAmount() {
        return currentYearAmount;
    }

    public void setCurrentYearAmount(Double currentYearAmount) {
        this.currentYearAmount = currentYearAmount;
    }
}