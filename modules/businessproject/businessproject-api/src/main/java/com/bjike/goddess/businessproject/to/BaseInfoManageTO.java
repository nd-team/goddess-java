package com.bjike.goddess.businessproject.to;

import com.bjike.goddess.businessproject.enums.*;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 商务项目合同基本信息管理
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-20T20:34:51.344 ]
 * @Description: [ 商务项目合同基本信息管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BaseInfoManageTO extends BaseTO {

    public interface TestAdd{}
    public interface TestEdit{}

    /**
     * 合同档案编号
     */
    @NotBlank(groups = {BaseInfoManageTO.TestEdit.class} , message = "合同档案编号不能为空" )
    private String contractNum;

    /**
     * 业务类型
     */
    @NotBlank(groups = {BaseInfoManageTO.TestAdd.class,BaseInfoManageTO.TestEdit.class} , message = "业务类型不能为空" )
    private BusinessType businessType;

    /**
     * 业务方向科目
     */
    @NotBlank(groups = {BaseInfoManageTO.TestAdd.class,BaseInfoManageTO.TestEdit.class} , message = "业务方向科目不能为空" )
    private String businessSubject;

    /**
     * 合同外部项目名称
     */
    @NotBlank(groups = {BaseInfoManageTO.TestAdd.class,BaseInfoManageTO.TestEdit.class} , message = "合同外部项目名称不能为空" )
    private String outerProject;

    /**
     * 合同外部项目编号
     */
    @NotBlank(groups = {BaseInfoManageTO.TestAdd.class,BaseInfoManageTO.TestEdit.class} , message = "合同外部项目编号不能为空" )
    private String outProjectNum;

    /**
     * 对应销售合同编号
     */
    @NotBlank(groups = {BaseInfoManageTO.TestAdd.class,BaseInfoManageTO.TestEdit.class} , message = "对应销售合同编号不能为空" )
    private String saleContractNum;

    /**
     * 合作方式
     */
    @NotNull(groups = {BaseInfoManageTO.TestAdd.class,BaseInfoManageTO.TestEdit.class} , message = "合作方式不能为空" )
    private BusinessCooperate businessCooperate;

    /**
     * 内部项目名称
     */
    @NotBlank(groups = {BaseInfoManageTO.TestAdd.class,BaseInfoManageTO.TestEdit.class} , message = "内部项目名称不能为空" )
    private String innerProject;

    /**
     * 内部项目编号
     */
    @NotBlank(groups = {BaseInfoManageTO.TestEdit.class} , message = "内部项目编号不能为空" )
    private String innerProjectNum;

    /**
     * 地区
     */
    @NotBlank(groups = {BaseInfoManageTO.TestAdd.class,BaseInfoManageTO.TestEdit.class} , message = "地区不能为空" )
    private String area;

    /**
     * 所属项目组
     */
    @NotBlank(groups = {BaseInfoManageTO.TestAdd.class,BaseInfoManageTO.TestEdit.class} , message = "所属项目组不能为空" )
    private String projectGroup;

    /**
     * 签订年份
     */
    private String siginYear;

    /**
     * 签订时间(格式年月日)
     */
    @NotBlank(groups = {BaseInfoManageTO.TestAdd.class,BaseInfoManageTO.TestEdit.class} , message = "签订时间(格式年月日)不能为空" )
    private String siginTime;

    /**
     * 合同金额
     */
    @NotNull(groups = {BaseInfoManageTO.TestAdd.class,BaseInfoManageTO.TestEdit.class} , message = "合同金额不能为空,且为数字" )
    private Double money;

    /**
     * 开工时间(格式年月日)
     */
    @NotBlank(groups = {BaseInfoManageTO.TestAdd.class,BaseInfoManageTO.TestEdit.class} , message = "开工时间(格式年月日)不能为空" )
    private String startProjectTime;

    /**
     * 完工时间(格式年月日)
     */
    @NotBlank(groups = {BaseInfoManageTO.TestAdd.class,BaseInfoManageTO.TestEdit.class} , message = "完工时间(格式年月日)不能为空" )
    private String endProjectTime;

    /**
     * 合同期限
     */
    @NotBlank(groups = {BaseInfoManageTO.TestAdd.class,BaseInfoManageTO.TestEdit.class} , message = "合同期限不能为空" )
    private String contractRang;

    /**
     * 甲方公司名称
     */
    @NotBlank(groups = {BaseInfoManageTO.TestAdd.class,BaseInfoManageTO.TestEdit.class} , message = "甲方公司名称不能为空" )
    private String firstCompany;

    /**
     * 甲方联系人
     */
    @NotBlank(groups = {BaseInfoManageTO.TestAdd.class,BaseInfoManageTO.TestEdit.class} , message = "甲方联系人不能为空" )
    private String firstRelation;

    /**
     * 甲方联系人电话
     */
    @NotBlank(groups = {BaseInfoManageTO.TestAdd.class,BaseInfoManageTO.TestEdit.class} , message = "甲方联系人电话不能为空" )
    private String firstTel;

    /**
     * 乙方公司名称
     */
    @NotBlank(groups = {BaseInfoManageTO.TestAdd.class,BaseInfoManageTO.TestEdit.class} , message = "乙方公司名称不能为空" )
    private String secondCompany;

    /**
     * 项目负责人
     */
    @NotBlank(groups = {BaseInfoManageTO.TestAdd.class,BaseInfoManageTO.TestEdit.class} , message = "项目负责人不能为空" )
    private String projectCharge;

    /**
     * 项目负责人电话
     */
    @NotBlank(groups = {BaseInfoManageTO.TestAdd.class,BaseInfoManageTO.TestEdit.class} , message = "项目负责人电话不能为空" )
    private String projectChargeTel;

    /**
     * 客户名称
     */
    @NotBlank(groups = {BaseInfoManageTO.TestAdd.class,BaseInfoManageTO.TestEdit.class} , message = "客户名称不能为空" )
    private String customerName;

    /**
     * 合同内容
     */
    @NotBlank(groups = {BaseInfoManageTO.TestAdd.class,BaseInfoManageTO.TestEdit.class} , message = "合同内容不能为空" )
    private String contractText;

    /**
     * 税率
     */
    @NotNull(groups = {BaseInfoManageTO.TestAdd.class,BaseInfoManageTO.TestEdit.class} , message = "税率不能为空,且为数字" )
    private Double rate;

    /**
     * 合同属性
     */
    @NotNull(groups = {BaseInfoManageTO.TestAdd.class,BaseInfoManageTO.TestEdit.class} , message = "合同属性不能为空" )
    private ContractProperty contractProperty;

    /**
     * 支付方式
     */
    @NotNull(groups = {BaseInfoManageTO.TestAdd.class,BaseInfoManageTO.TestEdit.class} , message = "支付方式不能为空" )
    private PayWays payWays;

    /**
     * 付款比例
     */
    @NotBlank(groups = {BaseInfoManageTO.TestAdd.class,BaseInfoManageTO.TestEdit.class} , message = "付款比例不能为空" )
    private String payRate;

    /**
     * 结算费用来源
     */
    @NotNull(groups = {BaseInfoManageTO.TestAdd.class,BaseInfoManageTO.TestEdit.class} , message = "结算费用来源不能为空" )
    private PayFeeOrigin payFeeOrigin;

    /**
     * 合同是否已归档(已归档/未归档)
     */
    @NotBlank(groups = {BaseInfoManageTO.TestAdd.class,BaseInfoManageTO.TestEdit.class} , message = "合同是否已归档不能为空" )
    private String fileCondition;

    /**
     * 合同归档数量
     */
    @NotNull(groups = {BaseInfoManageTO.TestAdd.class,BaseInfoManageTO.TestEdit.class} , message = "合同归档数量不能为空,且为数字" )
    private Double fileCount;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    public BusinessType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(BusinessType businessType) {
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

    public BusinessCooperate getBusinessCooperate() {
        return businessCooperate;
    }

    public void setBusinessCooperate(BusinessCooperate businessCooperate) {
        this.businessCooperate = businessCooperate;
    }

    public String getInnerProject() {
        return innerProject;
    }

    public void setInnerProject(String innerProject) {
        this.innerProject = innerProject;
    }

    public String getInnerProjectNum() {
        return innerProjectNum;
    }

    public void setInnerProjectNum(String innerProjectNum) {
        this.innerProjectNum = innerProjectNum;
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

    public String getSiginYear() {
        return siginYear;
    }

    public void setSiginYear(String siginYear) {
        this.siginYear = siginYear;
    }

    public String getSiginTime() {
        return siginTime;
    }

    public void setSiginTime(String siginTime) {
        this.siginTime = siginTime;
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

    public String getContractRang() {
        return contractRang;
    }

    public void setContractRang(String contractRang) {
        this.contractRang = contractRang;
    }

    public String getFirstCompany() {
        return firstCompany;
    }

    public void setFirstCompany(String firstCompany) {
        this.firstCompany = firstCompany;
    }

    public String getFirstRelation() {
        return firstRelation;
    }

    public void setFirstRelation(String firstRelation) {
        this.firstRelation = firstRelation;
    }

    public String getFirstTel() {
        return firstTel;
    }

    public void setFirstTel(String firstTel) {
        this.firstTel = firstTel;
    }

    public String getSecondCompany() {
        return secondCompany;
    }

    public void setSecondCompany(String secondCompany) {
        this.secondCompany = secondCompany;
    }

    public String getProjectCharge() {
        return projectCharge;
    }

    public void setProjectCharge(String projectCharge) {
        this.projectCharge = projectCharge;
    }

    public String getProjectChargeTel() {
        return projectChargeTel;
    }

    public void setProjectChargeTel(String projectChargeTel) {
        this.projectChargeTel = projectChargeTel;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContractText() {
        return contractText;
    }

    public void setContractText(String contractText) {
        this.contractText = contractText;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public ContractProperty getContractProperty() {
        return contractProperty;
    }

    public void setContractProperty(ContractProperty contractProperty) {
        this.contractProperty = contractProperty;
    }

    public PayWays getPayWays() {
        return payWays;
    }

    public void setPayWays(PayWays payWays) {
        this.payWays = payWays;
    }

    public String getPayRate() {
        return payRate;
    }

    public void setPayRate(String payRate) {
        this.payRate = payRate;
    }

    public PayFeeOrigin getPayFeeOrigin() {
        return payFeeOrigin;
    }

    public void setPayFeeOrigin(PayFeeOrigin payFeeOrigin) {
        this.payFeeOrigin = payFeeOrigin;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}