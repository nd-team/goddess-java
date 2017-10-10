package com.bjike.goddess.businsurance.excel;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

import java.time.LocalDate;


/**
 * 团体意外险购买详情导入
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-26 10:44 ]
 * @Description: [ 团体意外险购买详情导入 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CasualtyPurchasingDetailExcel extends BaseTO{

    /**
     * 被保人姓名
     */
    @ExcelHeader(name = "被保人姓名",notNull = true)
    private String beApplicantName;

    /**
     * 员工编号
     */
    @ExcelHeader(name = "员工编号",notNull = true)
    private String employeeNo;

    /**
     * 地区
     */
    @ExcelHeader(name = "地区",notNull = true)
    private String area;

    /**
     * 项目组部门
     */
    @ExcelHeader(name = "项目组部门",notNull = true)
    private String department;

    /**
     * 岗位
     */
    @ExcelHeader(name = "岗位",notNull = true)
    private String jobs;

    /**
     * 入职日期
     */
    @ExcelHeader(name = "入职日期",notNull = true)
    private LocalDate entryDate;

    /**
     * 停止购买时间
     */
    @ExcelHeader(name = "停止购买时间",notNull = true)
    private LocalDate stopBuyTime;

    public String getBeApplicantName() {
        return beApplicantName;
    }

    public void setBeApplicantName(String beApplicantName) {
        this.beApplicantName = beApplicantName;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJobs() {
        return jobs;
    }

    public void setJobs(String jobs) {
        this.jobs = jobs;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDate getStopBuyTime() {
        return stopBuyTime;
    }

    public void setStopBuyTime(LocalDate stopBuyTime) {
        this.stopBuyTime = stopBuyTime;
    }
}