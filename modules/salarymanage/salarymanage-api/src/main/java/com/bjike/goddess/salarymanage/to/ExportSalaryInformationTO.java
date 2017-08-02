package com.bjike.goddess.salarymanage.to;

/**
 * 薪资基本资料导出条件
 * Created by haikuang on 17-8-2.
 */
public class ExportSalaryInformationTO {
    /**
     * 计薪周期开始时间
     */
    private String payStarTime;

    /**
     * 计薪周期结束时间
     */
    private String payEndTime;


    public String getPayStarTime() {
        return payStarTime;
    }

    public void setPayStarTime(String payStarTime) {
        this.payStarTime = payStarTime;
    }

    public String getPayEndTime() {
        return payEndTime;
    }

    public void setPayEndTime(String payEndTime) {
        this.payEndTime = payEndTime;
    }
}
