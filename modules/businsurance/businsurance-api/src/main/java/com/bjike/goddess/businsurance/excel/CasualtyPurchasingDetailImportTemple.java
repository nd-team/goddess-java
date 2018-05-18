package com.bjike.goddess.businsurance.excel;

import com.bjike.goddess.businsurance.enums.BuyCasualtyStatus;
import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;


/**
 * 团体意外险购买详情导出模板
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-26 10:44 ]
 * @Description: [ 团体意外险购买详情导出 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CasualtyPurchasingDetailImportTemple extends BaseBO{

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
    private String entryDate;

    /**
     * 停止购买时间
     */
    @ExcelHeader(name = "停止购买时间",notNull = true)
    private String stopBuyTime;

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

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getStopBuyTime() {
        return stopBuyTime;
    }

    public void setStopBuyTime(String stopBuyTime) {
        this.stopBuyTime = stopBuyTime;
    }
}