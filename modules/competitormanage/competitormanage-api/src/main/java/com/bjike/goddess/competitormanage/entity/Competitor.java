package com.bjike.goddess.competitormanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.competitormanage.enums.BusinessType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 竞争对手信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-21 04:49 ]
 * @Description: [ 竞争对手信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "competitormanage_competitor")
public class Competitor extends BaseEntity {

    /**
     * 数据状态
     */
    @Column(columnDefinition = "TINYINT(2) DEFAULT 0 COMMENT '数据状态'", nullable = false, insertable = false)
    private Status status;


    /**
     * 市场信息收集序号
     */
    @Column(name = "markInfoCode", nullable = false, unique = true, columnDefinition = "VARCHAR(255)   COMMENT '市场信息收集序号'")
    private String markInfoCode;

    /**
     * 项目名称
     */
    @Column(name = "project", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String project;

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 竞争对手名称
     */
    @Column(name = "competitor", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '竞争对手名称'")
    private String competitor;

    /**
     * 业务类型
     */
    @Column(name = "businessType", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '业务类型'")
    private BusinessType businessType;

    /**
     * 基本资料描述
     */
    @Column(name = "basicInfoDesc", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '基本资料描述'")
    private String basicInfoDesc;

    /**
     * 电话
     */
    @Column(name = "phoneNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '电话'")
    private String phoneNum;

    /**
     * 地址
     */
    @Column(name = "address", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地址'")
    private String address;

    /**
     * 组织结构
     */
    @Column(name = "organization", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '组织结构'")
    private String organization;

    /**
     * 级别定义方式
     */
    @Column(name = "leveldefinition", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '级别定义方式'")
    private String leveldefinition;

    /**
     * 其他备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '其他备注'")
    private String remark;

    /**
     * 主管部门
     */
    @Column(name = "directDepartment", columnDefinition = "VARCHAR(255)   COMMENT '主管部门'")
    private String directDepartment;

    /**
     * 主管名称
     */
    @Column(name = "director", columnDefinition = "VARCHAR(255)   COMMENT '主管名称'")
    private String director;

    /**
     * 主管职权
     */
    @Column(name = "directAuthority", columnDefinition = "VARCHAR(255)   COMMENT '主管职权'")
    private String directAuthority;

    /**
     * 负责事项
     */
    @Column(name = "chargeItems", columnDefinition = "VARCHAR(255)   COMMENT '负责事项'")
    private String chargeItems;

    /**
     * 客户信息表序号
     */
    @Column(name = "customerInfoCode", columnDefinition = "VARCHAR(255)   COMMENT '客户信息表序号'")
    private String customerInfoCode;

    /**
     * 分管部门
     */
    @Column(name = "branchedDepartment", columnDefinition = "VARCHAR(255)   COMMENT '分管部门'")
    private String branchedDepartment;

    /**
     * 负责人名称
     */
    @Column(name = "chargeMan", columnDefinition = "VARCHAR(255)   COMMENT '负责人名称'")
    private String chargeMan;

    /**
     * 负责人职权
     */
    @Column(name = "chargeManAuthority", columnDefinition = "VARCHAR(255)   COMMENT '负责人职权'")
    private String chargeManAuthority;

    /**
     * 接口人
     */
    @Column(name = "interfaceMan", columnDefinition = "VARCHAR(255)   COMMENT '接口人'")
    private String interfaceMan;


    public String getMarkInfoCode() {
        return markInfoCode;
    }

    public void setMarkInfoCode(String markInfoCode) {
        this.markInfoCode = markInfoCode;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCompetitor() {
        return competitor;
    }

    public void setCompetitor(String competitor) {
        this.competitor = competitor;
    }

    public BusinessType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(BusinessType businessType) {
        this.businessType = businessType;
    }

    public String getBasicInfoDesc() {
        return basicInfoDesc;
    }

    public void setBasicInfoDesc(String basicInfoDesc) {
        this.basicInfoDesc = basicInfoDesc;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getLeveldefinition() {
        return leveldefinition;
    }

    public void setLeveldefinition(String leveldefinition) {
        this.leveldefinition = leveldefinition;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDirectDepartment() {
        return directDepartment;
    }

    public void setDirectDepartment(String directDepartment) {
        this.directDepartment = directDepartment;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDirectAuthority() {
        return directAuthority;
    }

    public void setDirectAuthority(String directAuthority) {
        this.directAuthority = directAuthority;
    }

    public String getChargeItems() {
        return chargeItems;
    }

    public void setChargeItems(String chargeItems) {
        this.chargeItems = chargeItems;
    }

    public String getCustomerInfoCode() {
        return customerInfoCode;
    }

    public void setCustomerInfoCode(String customerInfoCode) {
        this.customerInfoCode = customerInfoCode;
    }

    public String getBranchedDepartment() {
        return branchedDepartment;
    }

    public void setBranchedDepartment(String branchedDepartment) {
        this.branchedDepartment = branchedDepartment;
    }

    public String getChargeMan() {
        return chargeMan;
    }

    public void setChargeMan(String chargeMan) {
        this.chargeMan = chargeMan;
    }

    public String getChargeManAuthority() {
        return chargeManAuthority;
    }

    public void setChargeManAuthority(String chargeManAuthority) {
        this.chargeManAuthority = chargeManAuthority;
    }

    public String getInterfaceMan() {
        return interfaceMan;
    }

    public void setInterfaceMan(String interfaceMan) {
        this.interfaceMan = interfaceMan;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}