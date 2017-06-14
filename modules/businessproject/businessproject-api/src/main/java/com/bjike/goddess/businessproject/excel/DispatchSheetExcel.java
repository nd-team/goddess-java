package com.bjike.goddess.businessproject.excel;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

import java.time.LocalDate;

/**
 * @Author: [yewenbo]
 * @Date: [2017-06-08 14:52]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DispatchSheetExcel extends BaseTO {
    /**
     * 内部项目编号
     */
    @ExcelHeader(name = "内部项目编号", notNull = true)
    private String innerProjectNum;

    /**
     * 业务类型
     */
    @ExcelHeader(name = "业务类型", notNull = true)
    private String businessType;

    /**
     * 业务方向科目
     */
    @ExcelHeader(name = "业务方向科目", notNull = true)
    private String businessSubject;

    /**
     * 合同外部项目名称
     */
    @ExcelHeader(name = "合同外部项目名称", notNull = true)
    private String outerProject;

    /**
     * 合同外部项目编号
     */
    @ExcelHeader(name = "合同外部项目编号", notNull = true)
    private String outProjectNum;

    /**
     * 对应销售合同编号
     */
    @ExcelHeader(name = "对应销售合同编号", notNull = true)
    private String saleContractNum;

    /**
     * 合作方式
     */
    @ExcelHeader(name = "合作方式", notNull = true)
    private String businessCooperate;

    /**
     * 内部项目名称
     */
    @ExcelHeader(name = "内部项目名称", notNull = true)
    private String innerProject;

    /**
     * 地区
     */
    @ExcelHeader(name = "地区", notNull = true)
    private String area;

    /**
     * 所属项目组
     */
    @ExcelHeader(name = "所属项目组", notNull = true)
    private String projectGroup;

    /**
     * 签订时间
     */
    @ExcelHeader(name = "签订时间", notNull = true)
    private LocalDate siginTime;

    /**
     * 项目负责人
     */
    @ExcelHeader(name = "项目负责人", notNull = true)
    private String projectCharge;

    /**
     * 派工单名称
     */
    @ExcelHeader(name = "派工单名称", notNull = true)
    private String dispatchProject;

    /**
     * 派工单编号
     */
    @ExcelHeader(name = "派工单编号", notNull = true)
    private String dispatchNum;

    /**
     * 总包单位名称
     */
    @ExcelHeader(name = "总包单位名称", notNull = true)
    private String majorCompany;

    /**
     * 分包单位名称
     */
    @ExcelHeader(name = "分包单位名称", notNull = true)
    private String subCompany;

    /**
     * 客户名称
     */
    @ExcelHeader(name = "客户名称", notNull = true)
    private String customerName;

    /**
     * 派工内容
     */
    @ExcelHeader(name = "派工内容", notNull = true)
    private String dispatchText;

    /**
     * 开工日期
     */
    @ExcelHeader(name = "开工日期", notNull = true)
    private LocalDate startProjectTime;

    /**
     * 完工日期
     */
    @ExcelHeader(name = "完工日期", notNull = true)
    private LocalDate endProjectTime;

    /**
     * 派工金额
     */
    @ExcelHeader(name = "派工金额", notNull = true)
    private Double money;

    /**
     * 是否完工
     */
    @ExcelHeader(name = "是否完工", notNull = true)
    private String completeProject;

    /**
     * 合同是否已归档
     */
    @ExcelHeader(name = "合同是否已归档", notNull = true)
    private String fileCondition;

    /**
     * 合同归档数量
     */
    @ExcelHeader(name = "合同归档数量", notNull = true)
    private Double fileCount;

    /**
     * 备注
     */
    @ExcelHeader(name = "备注", notNull = true)
    private String remark;

    /**
     * 临时合同编号
     */
    @ExcelHeader(name = "临时合同编号", notNull = true)
    private String tempContractNum;

    public String getTempContractNum() {
        return tempContractNum;
    }

    public void setTempContractNum(String tempContractNum) {
        this.tempContractNum = tempContractNum;
    }

    public String getInnerProjectNum() {
        return innerProjectNum;
    }

    public void setInnerProjectNum(String innerProjectNum) {
        this.innerProjectNum = innerProjectNum;
    }

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

    public String getOuterProject() {
        return outerProject;
    }

    public void setOuterProject(String outerProject) {
        this.outerProject = outerProject;
    }

    public String getOutProjectNum() {
        return outProjectNum;
    }

    public void setOutProjectNum(String outProjectNum) {
        this.outProjectNum = outProjectNum;
    }

    public String getSaleContractNum() {
        return saleContractNum;
    }

    public void setSaleContractNum(String saleContractNum) {
        this.saleContractNum = saleContractNum;
    }

    public String getBusinessCooperate() {
        return businessCooperate;
    }

    public void setBusinessCooperate(String businessCooperate) {
        this.businessCooperate = businessCooperate;
    }

    public String getInnerProject() {
        return innerProject;
    }

    public void setInnerProject(String innerProject) {
        this.innerProject = innerProject;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public LocalDate getSiginTime() {
        return siginTime;
    }

    public void setSiginTime(LocalDate siginTime) {
        this.siginTime = siginTime;
    }

    public String getProjectCharge() {
        return projectCharge;
    }

    public void setProjectCharge(String projectCharge) {
        this.projectCharge = projectCharge;
    }

    public String getDispatchProject() {
        return dispatchProject;
    }

    public void setDispatchProject(String dispatchProject) {
        this.dispatchProject = dispatchProject;
    }

    public String getDispatchNum() {
        return dispatchNum;
    }

    public void setDispatchNum(String dispatchNum) {
        this.dispatchNum = dispatchNum;
    }

    public String getMajorCompany() {
        return majorCompany;
    }

    public void setMajorCompany(String majorCompany) {
        this.majorCompany = majorCompany;
    }

    public String getSubCompany() {
        return subCompany;
    }

    public void setSubCompany(String subCompany) {
        this.subCompany = subCompany;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDispatchText() {
        return dispatchText;
    }

    public void setDispatchText(String dispatchText) {
        this.dispatchText = dispatchText;
    }

    public LocalDate getStartProjectTime() {
        return startProjectTime;
    }

    public void setStartProjectTime(LocalDate startProjectTime) {
        this.startProjectTime = startProjectTime;
    }

    public LocalDate getEndProjectTime() {
        return endProjectTime;
    }

    public void setEndProjectTime(LocalDate endProjectTime) {
        this.endProjectTime = endProjectTime;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getCompleteProject() {
        return completeProject;
    }

    public void setCompleteProject(String completeProject) {
        this.completeProject = completeProject;
    }

    public String getFileCondition() {
        return fileCondition;
    }

    public void setFileCondition(String fileCondition) {
        this.fileCondition = fileCondition;
    }

    public Double getFileCount() {
        return fileCount;
    }

    public void setFileCount(Double fileCount) {
        this.fileCount = fileCount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
