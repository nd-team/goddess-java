package com.bjike.goddess.businessproject.excel;

import com.bjike.goddess.businessproject.enums.BusinessCooperate;
import com.bjike.goddess.businessproject.enums.BusinessType;
import com.bjike.goddess.businessproject.enums.ContractProperty;
import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

/**
 * 商务项目合同签订与立项导出
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-20T20:13:56.346 ]
 * @Description: [ 商务项目合同签订与立项导出 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SiginManageExport extends BaseBO {

    /**
     * 业务类型
     */
    @ExcelHeader(name = "业务类型",notNull = true)
    private String businessType;

    /**
     * 业务方向科目
     */
    @ExcelHeader(name = "业务方向科目",notNull = true)
    private String businessSubject;

    /**
     * 合作方式
     */
    @ExcelHeader(name = "合作方式",notNull = true)
    private String businessCooperate;

    /**
     * 外部项目名称
     */
    @ExcelHeader(name = "外部项目名称",notNull = true)
    private String outerProject;

    /**
     * 甲方公司名称
     */
    @ExcelHeader(name = "甲方公司名称",notNull = true)
    private String firstCompany;

    /**
     * 乙方公司名称
     */
    @ExcelHeader(name = "乙方公司名称",notNull = true)
    private String secondCompany;

    /**
     * 地区
     */
    @ExcelHeader(name = "地区",notNull = true)
    private String area;

    /**
     * 合同金额
     */
    @ExcelHeader(name = "合同金额",notNull = true)
    private Double money;

    /**
     * 开工时间
     */
    @ExcelHeader(name = "开工时间",notNull = true)
    private String startProjectTime;

    /**
     * 完工时间
     */
    @ExcelHeader(name = "完工时间",notNull = true)
    private String endProjectTime;

    /**
     * 签订状态
     */
    @ExcelHeader(name = "签订状态",notNull = true)
    private String siginStatus;

    /**
     * 合同属性
     */
    @ExcelHeader(name = "合同属性",notNull = true)
    private String contractProperty;

    /**
     * 立项情况
     */
    @ExcelHeader(name = "立项情况",notNull = true)
    private String makeProject;

    /**
     * 内部项目名称
     */
    @ExcelHeader(name = "内部项目名称",notNull = true)
    private String innerProject;

    /**
     * 项目组
     */
    @ExcelHeader(name = "项目组",notNull = true)
    private String projectGroup;

    /**
     * 项目负责人
     */
    @ExcelHeader(name = "项目负责人",notNull = true)
    private String projectCharge;

    /**
     * 备注
     */
    @ExcelHeader(name = "备注" )
    private String remark;

    /**
     * 总经办
     */
    @ExcelHeader(name = "总经办" )
    private String manager;

    /**
     * 审核意见
     */
    @ExcelHeader(name = "审核意见" )
    private String auditAdvice;



    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBusinessSubject() {
        return businessSubject;
    }

    public void setBusinessSubject(String businessSubject) {
        this.businessSubject = businessSubject;
    }

    public String getBusinessCooperate() {
        return businessCooperate;
    }

    public void setBusinessCooperate(String businessCooperate) {
        this.businessCooperate = businessCooperate;
    }

    public String getOuterProject() {
        return outerProject;
    }

    public void setOuterProject(String outerProject) {
        this.outerProject = outerProject;
    }

    public String getFirstCompany() {
        return firstCompany;
    }

    public void setFirstCompany(String firstCompany) {
        this.firstCompany = firstCompany;
    }

    public String getSecondCompany() {
        return secondCompany;
    }

    public void setSecondCompany(String secondCompany) {
        this.secondCompany = secondCompany;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getStartProjectTime() {
        return startProjectTime;
    }

    public void setStartProjectTime(String startProjectTime) {
        this.startProjectTime = startProjectTime;
    }

    public String getEndProjectTime() {
        return endProjectTime;
    }

    public void setEndProjectTime(String endProjectTime) {
        this.endProjectTime = endProjectTime;
    }

    public String getSiginStatus() {
        return siginStatus;
    }

    public void setSiginStatus(String siginStatus) {
        this.siginStatus = siginStatus;
    }

    public String getContractProperty() {
        return contractProperty;
    }

    public void setContractProperty(String contractProperty) {
        this.contractProperty = contractProperty;
    }

    public String getMakeProject() {
        return makeProject;
    }

    public void setMakeProject(String makeProject) {
        this.makeProject = makeProject;
    }

    public String getInnerProject() {
        return innerProject;
    }

    public void setInnerProject(String innerProject) {
        this.innerProject = innerProject;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getProjectCharge() {
        return projectCharge;
    }

    public void setProjectCharge(String projectCharge) {
        this.projectCharge = projectCharge;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getAuditAdvice() {
        return auditAdvice;
    }

    public void setAuditAdvice(String auditAdvice) {
        this.auditAdvice = auditAdvice;
    }

}