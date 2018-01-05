package com.bjike.goddess.salarymanage.to;

import java.io.Serializable;

/**
 * 薪资基本资料导出条件
 * Created by haikuang on 17-8-2.
 */
public class ExportSalaryInformationTO implements Serializable {
    /**
     * 计薪周期开始时间
     */
    private String payStartTime;

    /**
     * 计薪周期结束时间
     */
    private String payEndTime;


    public String getPayStartTime() {
        return payStartTime;
    }

    public void setPayStartTime(String payStartTime) {
        this.payStartTime = payStartTime;
    }

    public String getPayEndTime() {
        return payEndTime;
    }

    public void setPayEndTime(String payEndTime) {
        this.payEndTime = payEndTime;
    }
}
