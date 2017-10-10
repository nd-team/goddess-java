package com.bjike.goddess.businsurance.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 团体意外险购买详情
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-26 10:44 ]
 * @Description: [ 团体意外险购买详情 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CasualtyPurchasingDetailTO extends BaseTO {

    /**
     * 被保人姓名
     */
    @NotBlank(message = "被保人姓名不能为空",groups = {ADD.class})
    private String beApplicantName;

    /**
     * 员工编号
     */
    @NotBlank(message = "员工编号不能为空",groups = {ADD.class})
    private String employeeNo;

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空",groups = {ADD.class})
    private String area;

    /**
     * 项目组部门
     */
    @NotBlank(message = "项目组部门不能为空",groups = {ADD.class})
    private String department;

    /**
     * 岗位
     */
    @NotBlank(message = "岗位不能为空",groups = {ADD.class})
    private String jobs;

    /**
     * 入职日期
     */
    @NotBlank(message = "入职日期不能为空",groups = {ADD.class})
    private String entryDate;

    /**
     * 停止购买时间
     */
    @NotBlank(message = "停止购买时间不能为空",groups = {ADD.class, EDIT.class})
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