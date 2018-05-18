package com.bjike.goddess.businessproject.excel;

import com.bjike.goddess.businessproject.enums.*;
import com.bjike.goddess.businessproject.to.SiginManageTO;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 合同签订与立项
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:11 ]
 * @Description: [ 合同签订与立项 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SiginManageExcel extends BaseTO {


    /**
     * 业务类型
     */
    @ExcelHeader(name = "业务类型",notNull = true)
    private BusinessType businessType;

    /**
     * 业务方向科目
     */
    @ExcelHeader(name = "业务方向科目",notNull = true)
    private String businessSubject;

    /**
     * 合作方式
     */
    @ExcelHeader(name = "合作方式",notNull = true)
    private BusinessCooperate businessCooperate;

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
    private LocalDate startProjectTime;

    /**
     * 完工时间
     */
    @ExcelHeader(name = "完工时间")
    private LocalDate endProjectTime;

    /**
     * 签订状态(已签订/未签订)
     */
    @ExcelHeader(name = "签订状态",notNull = true)
    private SiginStatus siginStatus;

    /**
     * 合同属性
     */
    @ExcelHeader(name = "合同属性",notNull = true)
    private ContractProperty contractProperty;

    /**
     * 立项情况
     */
    @ExcelHeader(name = "立项情况",notNull = true)
    private MakeContract makeProject;

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
     * 派工单号
     */
    @ExcelHeader(name = "派工单号" )
    private String taskNum;
    /**
     * 项目状态
     */
    @ExcelHeader(name = "项目状态" )
    private ProjectStatus projectStatus;
    /**
     * 合同规模数量
     */
    @ExcelHeader(name = "合同规模数量" )
    private Double contractScale;
    /**
     * 规模数量
     */
    @ExcelHeader(name = "规模数量" )
    private Double scale;
    /**
     * 专业
     */
    @ExcelHeader(name = "专业" )
    private String major;

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

    public SiginStatus getSiginStatus() {
        return siginStatus;
    }

    public void setSiginStatus(SiginStatus siginStatus) {
        this.siginStatus = siginStatus;
    }

    public ContractProperty getContractProperty() {
        return contractProperty;
    }

    public void setContractProperty(ContractProperty contractProperty) {
        this.contractProperty = contractProperty;
    }

    public MakeContract getMakeProject() {
        return makeProject;
    }

    public void setMakeProject(MakeContract makeProject) {
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
}
