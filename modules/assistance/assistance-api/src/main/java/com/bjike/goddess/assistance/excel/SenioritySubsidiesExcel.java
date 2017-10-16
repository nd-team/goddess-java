package com.bjike.goddess.assistance.excel;

import com.bjike.goddess.assistance.enums.SubsidiesStatus;
import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.organize.enums.StaffStatus;

import java.time.LocalDate;


/**
 * 工龄补助导入
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-16 11:34 ]
 * @Description: [ 工龄补助导入 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SenioritySubsidiesExcel extends BaseTO {

    /**
     * 地区
     */
    @ExcelHeader(name = "地区", notNull = true)
    private String area;

    /**
     * 姓名
     */
    @ExcelHeader(name = "姓名", notNull = true)
    private String name;

    /**
     * 员工编号
     */
    @ExcelHeader(name = "员工编号", notNull = true)
    private String empNo;

    /**
     * 项目组/部门
     */
    @ExcelHeader(name = "项目组/部门", notNull = true)
    private String department;

    /**
     * 岗位
     */
    @ExcelHeader(name = "岗位", notNull = true)
    private String jobs;

    /**
     * 入职时间
     */
    @ExcelHeader(name = "入职时间", notNull = true)
    private LocalDate entryDate;

    /**
     * 开始发放补助日期
     */
    @ExcelHeader(name = "开始发放补助日期", notNull = true)
    private LocalDate startIssueDate;

    /**
     * 本公司工龄（月）
     */
    @ExcelHeader(name = "本公司工龄（月）")
    private Integer companyLength;

    /**
     * 应获得补助
     */
    @ExcelHeader(name = "应获得补助")
    private Double gainGrant;

    /**
     * 补助状态
     */
    @ExcelHeader(name = "补助状态", notNull = true)
    private SubsidiesStatus subsidiesStatus;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
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

    public LocalDate getStartIssueDate() {
        return startIssueDate;
    }

    public void setStartIssueDate(LocalDate startIssueDate) {
        this.startIssueDate = startIssueDate;
    }

    public Integer getCompanyLength() {
        return companyLength;
    }

    public void setCompanyLength(Integer companyLength) {
        this.companyLength = companyLength;
    }

    public Double getGainGrant() {
        return gainGrant;
    }

    public void setGainGrant(Double gainGrant) {
        this.gainGrant = gainGrant;
    }

    public SubsidiesStatus getSubsidiesStatus() {
        return subsidiesStatus;
    }

    public void setSubsidiesStatus(SubsidiesStatus subsidiesStatus) {
        this.subsidiesStatus = subsidiesStatus;
    }

}