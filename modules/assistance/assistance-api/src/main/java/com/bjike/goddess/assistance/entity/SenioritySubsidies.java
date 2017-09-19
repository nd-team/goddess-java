package com.bjike.goddess.assistance.entity;

import com.bjike.goddess.assistance.enums.SubsidiesStatus;
import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.organize.enums.StaffStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 工龄补助
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-16 11:34 ]
 * @Description: [ 工龄补助 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "assistance_senioritysubsidies")
public class SenioritySubsidies extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 姓名
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String name;

    /**
     * 员工编号
     */
    @Column(name = "empNo", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '员工编号'")
    private String empNo;

    /**
     * 项目组/部门
     */
    @Column(name = "department", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目组/部门'")
    private String department;

    /**
     * 岗位
     */
    @Column(name = "jobs", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '岗位'")
    private String jobs;

    /**
     * 入职时间
     */
    @Column(name = "entryDate", nullable = false, columnDefinition = "DATE   COMMENT '入职时间'")
    private String entryDate;

    /**
     * 开始发放补助日期
     */
    @Column(name = "startIssueDate", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '开始发放补助日期'")
    private String startIssueDate;

    /**
     * 本公司工龄（月）
     */
    @Column(name = "companyLength", nullable = false, columnDefinition = "INT(2)   COMMENT '本公司工龄（月）'")
    private Integer companyLength;

    /**
     * 应获得补助
     */
    @Column(name = "gainGrant", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '应获得补助'")
    private Double gainGrant;

    /**
     * 补助状态
     */
    @Column(name = "subsidiesStatus", nullable = false, columnDefinition = "INT(2)   COMMENT '补助状态'")
    private SubsidiesStatus subsidiesStatus;

    /**
     * 员工状态
     */
    @Column(name = "staffStatus", nullable = false, columnDefinition = "INT(2)   COMMENT '员工状态'")
    private StaffStatus staffStatus;

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

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getStartIssueDate() {
        return startIssueDate;
    }

    public void setStartIssueDate(String startIssueDate) {
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

    public StaffStatus getStaffStatus() {
        return staffStatus;
    }

    public void setStaffStatus(StaffStatus staffStatus) {
        this.staffStatus = staffStatus;
    }
}