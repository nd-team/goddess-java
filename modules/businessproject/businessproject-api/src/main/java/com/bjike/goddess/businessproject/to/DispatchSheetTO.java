package com.bjike.goddess.businessproject.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 商务项目派工单信息管理
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-21 10:06 ]
 * @Description: [ 商务项目派工单信息管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DispatchSheetTO extends BaseTO {

    public interface TestAdd{}

    /**
     * 内部项目编号
     */
    @NotBlank(groups = {DispatchSheetTO.TestAdd.class} , message = "内部项目编号不能为空")
    private String innerProjectNum;

    /**
     * 业务类型
     */
    @NotBlank(groups = {DispatchSheetTO.TestAdd.class} , message = "业务类型不能为空")
    private String businessType;

    /**
     * 业务方向科目
     */
    @NotBlank(groups = {DispatchSheetTO.TestAdd.class} , message = "业务方向科目不能为空")
    private String businessSubject;

    /**
     * 合同外部项目名称
     */
    @NotBlank(groups = {DispatchSheetTO.TestAdd.class} , message = "合同外部项目名称不能为空")
    private String outerProject;

    /**
     * 合同外部项目编号
     */
    @NotBlank(groups = {DispatchSheetTO.TestAdd.class} , message = "合同外部项目编号不能为空")
    private String outProjectNum;

    /**
     * 对应销售合同编号
     */
    @NotBlank(groups = {DispatchSheetTO.TestAdd.class} , message = "对应销售合同编号不能为空")
    private String saleContractNum;

    /**
     * 合作方式
     */
    @NotBlank(groups = {DispatchSheetTO.TestAdd.class} , message = "合作方式不能为空")
    private String businessCooperate;

    /**
     * 内部项目名称
     */
    @NotBlank(groups = {DispatchSheetTO.TestAdd.class} , message = "内部项目名称不能为空")
    private String innerProject;

    /**
     * 地区
     */
    @NotBlank(groups = {DispatchSheetTO.TestAdd.class} , message = "地区不能为空")
    private String area;

    /**
     * 所属项目组
     */
    @NotBlank(groups = {DispatchSheetTO.TestAdd.class} , message = "所属项目组不能为空")
    private String projectGroup;

    /**
     * 签订时间（年月日）
     */
    @NotBlank(groups = {DispatchSheetTO.TestAdd.class} , message = "签订时间不能为空,且为年月日")
    private String siginTime;

    /**
     * 项目负责人
     */
    @NotBlank(groups = {DispatchSheetTO.TestAdd.class} , message = "项目负责人不能为空")
    private String projectCharge;

    /**
     * 派工单名称
     */
    @NotBlank(groups = {DispatchSheetTO.TestAdd.class} , message = "派工单名称不能为空")
    private String dispatchProject;

    /**
     * 派工单编号
     */
    @NotBlank(groups = {DispatchSheetTO.TestAdd.class} , message = "派工单编号不能为空")
    private String dispatchNum;

    /**
     * 总包单位名称
     */
    @NotBlank(groups = {DispatchSheetTO.TestAdd.class} , message = "总包单位名称不能为空")
    private String majorCompany;

    /**
     * 分包单位名称
     */
    @NotBlank(groups = {DispatchSheetTO.TestAdd.class} , message = "分包单位名称不能为空")
    private String subCompany;

    /**
     * 客户名称
     */
    @NotBlank(groups = {DispatchSheetTO.TestAdd.class} , message = "客户名称不能为空")
    private String customerName;

    /**
     * 派工内容
     */
    @NotBlank(groups = {DispatchSheetTO.TestAdd.class} , message = "派工内容不能为空")
    private String dispatchText;

    /**
     * 开工日期(年月日）
     */
    @NotBlank(groups = {DispatchSheetTO.TestAdd.class} , message = "开工日期不能为空,且为年月日")
    private String startProjectTime;

    /**
     * 完工日期(年月日）
     */
    @NotBlank(groups = {DispatchSheetTO.TestAdd.class} , message = "完工日期不能为空,且为年月日")
    private String endProjectTime;

    /**
     * 派工金额
     */
    @NotNull(groups = {DispatchSheetTO.TestAdd.class} , message = "派工金额不能为空,且为数字")
    private Double money;

    /**
     * 是否完工（已完工/未完工）
     */
    @NotBlank(groups = {DispatchSheetTO.TestAdd.class} , message = "是否完工不能为空")
    private String completeProject;

    /**
     * 合同是否已归档（已归档/未归档）
     */
    @NotBlank(groups = {DispatchSheetTO.TestAdd.class} , message = "合同是否已归档不能为空")
    private String fileCondition;

    /**
     * 合同归档数量
     */
    @NotNull(groups = {DispatchSheetTO.TestAdd.class} , message = "合同归档数量不能为空,且为数字")
    private Double fileCount;

    /**
     * 备注
     */
    private String remark;

    /**
     * 临时合同编号
     */
    private String tempContractNum;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


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

    public String getSiginTime() {
        return siginTime;
    }

    public void setSiginTime(String siginTime) {
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

    public String getTempContractNum() {
        return tempContractNum;
    }

    public void setTempContractNum(String tempContractNum) {
        this.tempContractNum = tempContractNum;
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