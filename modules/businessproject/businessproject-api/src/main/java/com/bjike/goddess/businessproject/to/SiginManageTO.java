package com.bjike.goddess.businessproject.to;

import com.bjike.goddess.businessproject.enums.BusinessCooperate;
import com.bjike.goddess.businessproject.enums.BusinessType;
import com.bjike.goddess.businessproject.enums.ContractProperty;
import com.bjike.goddess.businessproject.enums.ProjectStatus;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 商务项目合同签订与立项管理
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-20T20:13:56.348 ]
 * @Description: [ 商务项目合同签订与立项管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SiginManageTO extends BaseTO {

    public interface TestAdd{}

    /**
     * 业务类型
     */
    @NotNull(groups = {SiginManageTO.TestAdd.class} , message = "业务类型不能为空")
    private BusinessType businessType;

    /**
     * 业务方向科目
     */
    @NotBlank(groups = {SiginManageTO.TestAdd.class} , message = "业务方向科目不能为空")
    private String businessSubject;

    /**
     * 合作方式
     */
    @NotNull(groups = {SiginManageTO.TestAdd.class} , message = "合作方式不能为空")
    private BusinessCooperate businessCooperate;

    /**
     * 外部项目名称
     */
    @NotBlank(groups = {SiginManageTO.TestAdd.class} , message = "外部项目名称不能为空")
    private String outerProject;

    /**
     * 甲方公司名称
     */
    @NotBlank(groups = {SiginManageTO.TestAdd.class} , message = "甲方公司名称不能为空")
    private String firstCompany;

    /**
     * 乙方公司名称
     */
    @NotBlank(groups = {SiginManageTO.TestAdd.class} , message = "乙方公司名称不能为空")
    private String secondCompany;

    /**
     * 地区
     */
    @NotBlank(groups = {SiginManageTO.TestAdd.class} , message = "地区不能为空")
    private String area;

    /**
     * 合同金额
     */
    @NotNull(groups = {SiginManageTO.TestAdd.class} , message = "合同金额不能为空,且为数字")
    private Double money;

    /**
     * 开工时间
     */
    @NotBlank(groups = {SiginManageTO.TestAdd.class} , message = "开工时间不能为空,格式为年月日")
    private String startProjectTime;

    /**
     * 完工时间
     */
    @NotBlank(groups = {SiginManageTO.TestAdd.class} , message = "完工时间不能为空,格式为年月日")
    private String endProjectTime;

    /**
     * 签订状态(已签订/未签订) xx
     */
    @NotBlank(groups = {SiginManageTO.TestAdd.class} , message = "签订状态不能为空(已签订/未签订)")
    private String siginStatus;

    /**
     * 合同属性
     */
    @NotNull(groups = {SiginManageTO.TestAdd.class} , message = "合同属性不能为空")
    private ContractProperty contractProperty;

    /**
     * 立项情况 xx
     */
    @NotBlank(groups = {SiginManageTO.TestAdd.class} , message = "立项情况不能为空")
    private String makeProject;

    /**
     * 内部项目名称
     */
    @NotBlank(groups = {SiginManageTO.TestAdd.class} , message = "内部项目名称不能为空")
    private String innerProject;

    /**
     * 项目组
     */
    @NotBlank(groups = {SiginManageTO.TestAdd.class} , message = "项目组不能为空")
    private String projectGroup;

    /**
     * 项目负责人
     */
    @NotBlank(groups = {SiginManageTO.TestAdd.class} , message = "项目负责人不能为空")
    private String projectCharge;
    /**
     * 派工单号
     */
    private String taskNum;
    /**
     * 项目状态
     */
    @NotNull(groups = {SiginManageTO.TestAdd.class} , message = "项目状态不能为空")
    private ProjectStatus projectStatus;
    /**
     * 合同规模数量
     */
    @NotNull(groups = {SiginManageTO.TestAdd.class} , message = "合同规模数量不能为空")
    private Double contractScale;
    /**
     * 规模数量
     */
    @NotNull(groups = {SiginManageTO.TestAdd.class} , message = "规模数量不能为空")
    private Double scale;
    /**
     * 专业
     */
    @NotBlank(groups = {SiginManageTO.TestAdd.class} , message = "专业不能为空")
    private String major;


    /**
     * 备注
     */
    private String remark;

    /**
     * 总经办 xx
     */
    private String manager;

    /**
     * 审核意见 xx
     */
    private String auditAdvice;

    /**
     * 创建时间 xx
     */
    private String createTime;

    /**
     * 修改时间 xx
     */
    private String modifyTime;

    public String getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(String taskNum) {
        this.taskNum = taskNum;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }

    public Double getContractScale() {
        return contractScale;
    }

    public void setContractScale(Double contractScale) {
        this.contractScale = contractScale;
    }

    public Double getScale() {
        return scale;
    }

    public void setScale(Double scale) {
        this.scale = scale;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
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

    public BusinessCooperate getBusinessCooperate() {
        return businessCooperate;
    }

    public void setBusinessCooperate(BusinessCooperate businessCooperate) {
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

    public ContractProperty getContractProperty() {
        return contractProperty;
    }

    public void setContractProperty(ContractProperty contractProperty) {
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