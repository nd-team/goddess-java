package com.bjike.goddess.salarymanage.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

import java.time.LocalDate;

/**
* 薪资管理数据传输对象
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-07-31 01:45 ]
* @Description:	[ 薪资管理数据传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class SalaryInformationDTO extends BaseDTO {

    /**
     * 计薪周期开始时间
     */
    private String payStarTime;

    /**
     * 计薪周期结束时间
     */
    private String payEndTime;


    /**
     * 员工姓名
     */
    private String employeeName;

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

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}