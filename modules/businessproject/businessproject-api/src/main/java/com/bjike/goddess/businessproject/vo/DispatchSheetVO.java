package com.bjike.goddess.businessproject.vo;

import com.bjike.goddess.businessproject.enums.MakeContract;
import com.bjike.goddess.businessproject.enums.ProjectStatus;

/**
 * 商务项目派工单信息管理表现层对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-21 10:06 ]
 * @Description: [ 商务项目派工单信息管理表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DispatchSheetVO {

    /**
     * id
     */
    private String id;
    /**
     * 内部项目编号
     */
    private String innerProjectNum;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 业务方向科目
     */
    private String businessSubject;

    /**
     * 合同外部项目名称
     */
    private String outerProject;

    /**
     * 合同外部项目编号
     */
    private String outProjectNum;

    /**
     * 对应销售合同编号
     */
    private String saleContractNum;

    /**
     * 合作方式
     */
    private String businessCooperate;

    /**
     * 内部项目名称
     */
    private String innerProject;

    /**
     * 地区
     */
    private String area;

    /**
     * 所属项目组
     */
    private String projectGroup;

    /**
     * 签订时间
     */
    private String siginTime;

    /**
     * 项目负责人
     */
    private String projectCharge;

    /**
     * 派工单名称
     */
    private String dispatchProject;

    /**
     * 派工单编号
     */
    private String dispatchNum;

    /**
     * 总包单位名称
     */
    private String majorCompany;

    /**
     * 分包单位名称
     */
    private String subCompany;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 派工内容
     */
    private String dispatchText;

    /**
     * 开工日期
     */
    private String startProjectTime;

    /**
     * 完工日期
     */
    private String endProjectTime;

    /**
     * 派工金额
     */
    private Double money;

    /**
     * 是否完工
     */
    private String completeProject;

    /**
     * 合同是否已归档
     */
    private String fileCondition;

    /**
     * 合同归档数量
     */
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
     * 立项情况
     */
    private MakeContract makeContract;
    /**
     * 派工单号
     */
    private String taskNum;
    /**
     * 项目状态
     */
    private ProjectStatus projectStatus;
    /**
     * 合同规模数量
     */
    private Double contractScale;
    /**
     * 规模数量
     */
    private Double scale;
    /**
     * 专业
     */
    private String major;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;

    public MakeContract getMakeContract() {
        return makeContract;
    }

    public void setMakeContract(MakeContract makeContract) {
        this.makeContract = makeContract;
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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