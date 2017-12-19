package com.bjike.goddess.businessprojectmanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 项目合同基本信息
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2017-12-11 11:43 ]
 * @Description: [ 项目合同基本信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "businessprojectmanage_businesscontractpro")
public class BusinessContractPro extends BaseEntity {

    /**
     * 商务合同信息表id
     */
    @Column(name = "businessContractId", columnDefinition = "VARCHAR(255)   COMMENT '商务合同信息表id'")
    private String businessContractId;

    /**
     * 测算分类
     */
    @Column(name = "measureClassify", columnDefinition = "VARCHAR(255)   COMMENT '测算分类'")
    private String measureClassify;

    /**
     * 测算是否通过
     */
    @Column(name = "is_measurePass", columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '测算是否通过'", insertable = false)
    private Boolean measurePass;

    /**
     * 签订时间
     */
    @Column(name = "signedTime", columnDefinition = "DATE   COMMENT '签订时间'")
    private LocalDate signedTime;

    /**
     * 通报时间
     */
    @Column(name = "notificationTime", columnDefinition = "DATE   COMMENT '通报时间'")
    private LocalDate notificationTime;

    /**
     * 是否通报
     */
    @Column(name = "is_notification", columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否通报'", insertable = false)
    private Boolean notification;

    /**
     * 地区
     */
    @Column(name = "area", columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 业务类型
     */
    @Column(name = "businessType", columnDefinition = "VARCHAR(255)   COMMENT '业务类型'")
    private String businessType;

    /**
     * 业务方向科目
     */
    @Column(name = "businessSubject", columnDefinition = "VARCHAR(255)   COMMENT '业务方向科目'")
    private String businessSubject;

    /**
     * 总包单位名称
     */
    @Column(name = "majorCompany", columnDefinition = "VARCHAR(255)   COMMENT '总包单位名称'")
    private String majorCompany;

    /**
     * 分包单位名称
     */
    @Column(name = "subCompany", columnDefinition = "VARCHAR(255)   COMMENT '分包单位名称'")
    private String subCompany;

    /**
     * 是否有共同分包单位
     */
    @Column(name = "is_commonSubcontractor", columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否有共同分包单位'", insertable = false)
    private Boolean commonSubcontractor;

    /**
     * 共同分包单位名称
     */
    @Column(name = "commonSubcontractorName", columnDefinition = "VARCHAR(255)   COMMENT '共同分包单位名称'")
    private String commonSubcontractorName;

    /**
     * 派工归属清理是否完成
     */
    @Column(name = "taskFinish", columnDefinition = "VARCHAR(255)   COMMENT '派工归属清理是否完成'")
    private Boolean taskFinish;

    /**
     * 客户名称
     */
    @Column(name = "customerName", columnDefinition = "VARCHAR(255)   COMMENT '客户名称'")
    private String customerName;

    /**
     * 所属项目组
     */
    @Column(name = "projectGroup", columnDefinition = "VARCHAR(255)   COMMENT '所属项目组'")
    private String projectGroup;

    /**
     * 营运商
     */
    @Column(name = "operator", columnDefinition = "VARCHAR(255)   COMMENT '营运商'")
    private String operator;

    /**
     * 类型
     */
    @Column(name = "type", columnDefinition = "VARCHAR(255)   COMMENT '类型'")
    private String type;

    /**
     * 专业/工期
     */
    @Column(name = "major", columnDefinition = "VARCHAR(255)   COMMENT '专业/工期'")
    private String major;

    /**
     * 是否有合同派工合同
     */
    @Column(name = "is_taskContract", columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否有合同派工合同'", insertable = false)
    private Boolean taskContract;

    /**
     * 市场编号
     */
    @Column(name = "marketNum", columnDefinition = "VARCHAR(255)   COMMENT '市场编号'")
    private String marketNum;

    /**
     * 内部项目编号
     */
    @Column(name = "internalProjectNum", columnDefinition = "VARCHAR(255)   COMMENT '内部项目编号'")
    private String internalProjectNum;

    /**
     * 内部合同编号
     */
    @Column(name = "internalContractNum", columnDefinition = "VARCHAR(255)   COMMENT '内部合同编号'")
    private String internalContractNum;

    /**
     * 是否有合同立项
     */
    @Column(name = "", columnDefinition = "VARCHAR(255)   COMMENT '是否有合同立项'")
    private Integer makeContract;

    /**
     * 销售合同编号
     */
    @Column(name = "salesContractNum", columnDefinition = "VARCHAR(255)   COMMENT '销售合同编号'")
    private String salesContractNum;

    /**
     * 单次合同编号
     */
    @Column(name = "singleContractNum", columnDefinition = "VARCHAR(255)   COMMENT '单次合同编号'")
    private String singleContractNum;

    /**
     * 单次合同名称
     */
    @Column(name = "singleContractName", columnDefinition = "VARCHAR(255)   COMMENT '单次合同名称'")
    private String singleContractName;

    /**
     * 派工界面A
     */
    @Column(name = "dispatchInterfaceA", columnDefinition = "VARCHAR(255)   COMMENT '派工界面A'")
    private String dispatchInterfaceA;

    /**
     * 派工界面B
     */
    @Column(name = "dispatchInterfaceB", columnDefinition = "VARCHAR(255)   COMMENT '派工界面B'")
    private String dispatchInterfaceB;

    /**
     * 派工界面C
     */
    @Column(name = "dispatchInterfaceC", columnDefinition = "VARCHAR(255)   COMMENT '派工界面C'")
    private String dispatchInterfaceC;

    /**
     * 规模数量
     */
    @Column(name = "scale", columnDefinition = "DECIMAL(10,2)   COMMENT '规模数量'")
    private Double scale;

    /**
     * 合同规模数量
     */
    @Column(name = "", columnDefinition = "VARCHAR(255)   COMMENT '合同规模数量'")
    private Integer scaleContract;

    /**
     * 合同规模数是否有差异
     */
    @Column(name = "is_scaleBalance", columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '合同规模数是否有差异'", insertable = false)
    private Boolean scaleBalance;

    /**
     * 是否解决差异问题
     */
    @Column(name = "is_solutionBalance", columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否解决差异问题'", insertable = false)
    private Boolean solutionBalance;

    /**
     * 派工金额
     */
    @Column(name = "taskMoney", columnDefinition = "DECIMAL(10,2)   COMMENT '派工金额'")
    private Double taskMoney;

    /**
     * 立项金额
     */
    @Column(name = "makeMoney", columnDefinition = "DECIMAL(10,2)   COMMENT '立项金额'")
    private Double makeMoney;

    /**
     * 预估金额
     */
    @Column(name = "forecastMoney", columnDefinition = "DECIMAL(10,2)   COMMENT '预估金额'")
    private Double forecastMoney;

    /**
     * 预估转正完成金额
     */
    @Column(name = "forecastFinishMoney", columnDefinition = "DECIMAL(10,2)   COMMENT '预估转正完成金额'")
    private Double forecastFinishMoney;

    /**
     * 预估转正进行金额
     */
    @Column(name = "forecastMarchMoney", columnDefinition = "DECIMAL(10,2)   COMMENT '预估转正进行金额'")
    private Double forecastMarchMoney;

    /**
     * 预估市场亏损金额
     */
    @Column(name = "estimatedMarketLosses", columnDefinition = "DECIMAL(10,2)   COMMENT '预估市场亏损金额'")
    private Double estimatedMarketLosses;

    /**
     * 预估确认担保人
     */
    @Column(name = "guarantor", columnDefinition = "VARCHAR(255)   COMMENT '预估确认担保人'")
    private String guarantor;

    /**
     * 预估确认担保人审核意见
     */
    @Column(name = "guarantorIdea", columnDefinition = "VARCHAR(255)   COMMENT '预估确认担保人审核意见'")
    private String guarantorIdea;

    /**
     * 预估项目是否确认实施
     */
    @Column(name = "is_implement", columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '预估项目是否确认实施'", insertable = false)
    private Boolean implement;

    /**
     * 项目经理意见
     */
    @Column(name = "managerIdea", columnDefinition = "VARCHAR(255)   COMMENT '项目经理意见'")
    private String managerIdea;

    /**
     * 规划模块分析意见
     */
    @Column(name = "planIdea", columnDefinition = "VARCHAR(255)   COMMENT '规划模块分析意见'")
    private String planIdea;

    /**
     * 预算模块分析意见
     */
    @Column(name = "budgetIdea", columnDefinition = "VARCHAR(255)   COMMENT '预算模块分析意见'")
    private String budgetIdea;

    /**
     * 是否分批结算
     */
    @Column(name = "is_partial", columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否分批结算'", insertable = false)
    private Boolean partial;

    /**
     * 分批结算金额
     */
    @Column(name = "partialMoney", columnDefinition = "DECIMAL(10,2)   COMMENT '分批结算金额'")
    private Double partialMoney;

    /**
     * 合同持续时长
     */
    @Column(name = "contractPersist", columnDefinition = "DECIMAL(10,2)   COMMENT '合同持续时长'")
    private Double contractPersist;

    /**
     * 是否为持续
     */
    @Column(name = "is_persist", columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否为持续'", insertable = false)
    private Boolean persist;

    /**
     * 预估开工时间
     */
    @Column(name = "expectStartDate", columnDefinition = "DATE   COMMENT '预估开工时间'")
    private LocalDate expectStartDate;

    /**
     * 实际开工日期
     */
    @Column(name = "realityStartDate", columnDefinition = "DATE   COMMENT '实际开工日期'")
    private LocalDate realityStartDate;

    /**
     * 实际完工日期
     */
    @Column(name = "realityCompleteTime", columnDefinition = "DATE   COMMENT '实际完工日期'")
    private LocalDate realityCompleteTime;

    /**
     * 预计完工时间
     */
    @Column(name = "expectCompleteTime", columnDefinition = "DATE   COMMENT '预计完工时间'")
    private LocalDate expectCompleteTime;

    /**
     * 未进场
     */
    @Column(name = "notApproach", columnDefinition = "VARCHAR(255)   COMMENT '未进场'")
    private String notApproach;

    /**
     * 进场
     */
    @Column(name = "approach", columnDefinition = "VARCHAR(255)   COMMENT '进场'")
    private String approach;

    /**
     * 停工
     */
    @Column(name = "shutdown", columnDefinition = "VARCHAR(255)   COMMENT '停工'")
    private String shutdown;

    /**
     * 进行
     */
    @Column(name = "march", columnDefinition = "VARCHAR(255)   COMMENT '进行'")
    private String march;

    /**
     * 完工
     */
    @Column(name = "complete", columnDefinition = "VARCHAR(255)   COMMENT '完工'")
    private String complete;

    /**
     * 到货
     */
    @Column(name = "goods", columnDefinition = "VARCHAR(255)   COMMENT '到货'")
    private String goods;

    /**
     * 初验
     */
    @Column(name = "initialTest", columnDefinition = "VARCHAR(255)   COMMENT '初验'")
    private String initialTest;

    /**
     * 终验
     */
    @Column(name = "finalTest", columnDefinition = "VARCHAR(255)   COMMENT '终验'")
    private String finalTest;

    /**
     * 是否正在走结算流程
     */
    @Column(name = "is_settlementProcess", columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否正在走结算流程'", insertable = false)
    private Boolean settlementProcess;

    /**
     * 是否到账
     */
    @Column(name = "is_account", columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否到账'", insertable = false)
    private Boolean account;

    /**
     * 是否闭单
     */
    @Column(name = "is_closeSingle", columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否闭单'", insertable = false)
    private Boolean closeSingle;

    /**
     * 合作方式
     */
    @Column(name = "businessCooperate", columnDefinition = "VARCHAR(255)   COMMENT '合作方式'")
    private String businessCooperate;

    /**
     * 合同外部项目名称
     */
    @Column(name = "outerProject", columnDefinition = "VARCHAR(255)   COMMENT '合同外部项目名称'")
    private String outerProject;

    /**
     * 内部项目名称
     */
    @Column(name = "innerProject", columnDefinition = "VARCHAR(255)   COMMENT '内部项目名称'")
    private String innerProject;

    /**
     * 商务评估（项目名称）
     */
    @Column(name = "businessAssessProject", columnDefinition = "VARCHAR(255)   COMMENT '商务评估（项目名称）'")
    private String businessAssessProject;

    /**
     * 商务评估（当前情况）
     */
    @Column(name = "businessAssessCase", columnDefinition = "VARCHAR(255)   COMMENT '商务评估（当前情况）'")
    private String businessAssessCase;

    /**
     * 商务回复反馈
     */
    @Column(name = "businessReplyFeedback", columnDefinition = "VARCHAR(255)   COMMENT '商务回复反馈'")
    private String businessReplyFeedback;

    /**
     * 项目负责人
     */
    @Column(name = "projectCharge", columnDefinition = "VARCHAR(255)   COMMENT '项目负责人'")
    private String projectCharge;

    /**
     * 经手人
     */
    @Column(name = "handlers", columnDefinition = "VARCHAR(255)   COMMENT '经手人'")
    private String handlers;

    /**
     * 合同是否已归档
     */
    @Column(name = "is_archive", columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '合同是否已归档'", insertable = false)
    private Boolean archive;

    /**
     * 合同归档数量
     */
    @Column(name = "archiveNum", columnDefinition = "VARCHAR(255)   COMMENT '合同归档数量'")
    private Double archiveNum;

    /**
     * 存储位置
     */
    @Column(name = "storageLocation", columnDefinition = "VARCHAR(255)   COMMENT '存储位置'")
    private String storageLocation;

    /**
     * 状态
     */
    @Column(name = "is_status", columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '状态'", insertable = false)
    private Boolean status;

    /**
     * 计划商务总结开始时间
     */
    @Column(name = "summarizeStartDate", columnDefinition = "DATE   COMMENT '计划商务总结开始时间'")
    private LocalDate summarizeStartDate;

    /**
     * 计划商务总结完成时间
     */
    @Column(name = "summarizeEndDate", columnDefinition = "DATE   COMMENT '计划商务总结完成时间'")
    private LocalDate summarizeEndDate;

    /**
     * 实际商务总结完成时间
     */
    @Column(name = "summarizeFinishDate", columnDefinition = "DATE   COMMENT '实际商务总结完成时间'")
    private LocalDate summarizeFinishDate;

    /**
     * 是否总结完成
     */
    @Column(name = "is_summarizeFinish", columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否总结完成'", insertable = false)
    private Boolean summarizeFinish;

    /**
     * 实施状态
     */
    @Column(name = "", columnDefinition = "VARCHAR(255)   COMMENT '实施状态'")
    private String progressStatus;


    public String getBusinessContractId() {
        return businessContractId;
    }

    public void setBusinessContractId(String businessContractId) {
        this.businessContractId = businessContractId;
    }

    public String getMeasureClassify() {
        return measureClassify;
    }

    public void setMeasureClassify(String measureClassify) {
        this.measureClassify = measureClassify;
    }

    public Boolean getMeasurePass() {
        return measurePass;
    }

    public void setMeasurePass(Boolean measurePass) {
        this.measurePass = measurePass;
    }

    public LocalDate getSignedTime() {
        return signedTime;
    }

    public void setSignedTime(LocalDate signedTime) {
        this.signedTime = signedTime;
    }

    public LocalDate getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(LocalDate notificationTime) {
        this.notificationTime = notificationTime;
    }

    public Boolean getNotification() {
        return notification;
    }

    public void setNotification(Boolean notification) {
        this.notification = notification;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public Boolean getCommonSubcontractor() {
        return commonSubcontractor;
    }

    public void setCommonSubcontractor(Boolean commonSubcontractor) {
        this.commonSubcontractor = commonSubcontractor;
    }

    public String getCommonSubcontractorName() {
        return commonSubcontractorName;
    }

    public void setCommonSubcontractorName(String commonSubcontractorName) {
        this.commonSubcontractorName = commonSubcontractorName;
    }

    public Boolean getTaskFinish() {
        return taskFinish;
    }

    public void setTaskFinish(Boolean taskFinish) {
        this.taskFinish = taskFinish;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Boolean getTaskContract() {
        return taskContract;
    }

    public void setTaskContract(Boolean taskContract) {
        this.taskContract = taskContract;
    }

    public String getMarketNum() {
        return marketNum;
    }

    public void setMarketNum(String marketNum) {
        this.marketNum = marketNum;
    }

    public String getInternalProjectNum() {
        return internalProjectNum;
    }

    public void setInternalProjectNum(String internalProjectNum) {
        this.internalProjectNum = internalProjectNum;
    }

    public String getInternalContractNum() {
        return internalContractNum;
    }

    public void setInternalContractNum(String internalContractNum) {
        this.internalContractNum = internalContractNum;
    }

    public Integer getMakeContract() {
        return makeContract;
    }

    public void setMakeContract(Integer makeContract) {
        this.makeContract = makeContract;
    }

    public String getSalesContractNum() {
        return salesContractNum;
    }

    public void setSalesContractNum(String salesContractNum) {
        this.salesContractNum = salesContractNum;
    }

    public String getSingleContractNum() {
        return singleContractNum;
    }

    public void setSingleContractNum(String singleContractNum) {
        this.singleContractNum = singleContractNum;
    }

    public String getSingleContractName() {
        return singleContractName;
    }

    public void setSingleContractName(String singleContractName) {
        this.singleContractName = singleContractName;
    }

    public String getDispatchInterfaceA() {
        return dispatchInterfaceA;
    }

    public void setDispatchInterfaceA(String dispatchInterfaceA) {
        this.dispatchInterfaceA = dispatchInterfaceA;
    }

    public String getDispatchInterfaceB() {
        return dispatchInterfaceB;
    }

    public void setDispatchInterfaceB(String dispatchInterfaceB) {
        this.dispatchInterfaceB = dispatchInterfaceB;
    }

    public String getDispatchInterfaceC() {
        return dispatchInterfaceC;
    }

    public void setDispatchInterfaceC(String dispatchInterfaceC) {
        this.dispatchInterfaceC = dispatchInterfaceC;
    }

    public Double getScale() {
        return scale;
    }

    public void setScale(Double scale) {
        this.scale = scale;
    }

    public Integer getScaleContract() {
        return scaleContract;
    }

    public void setScaleContract(Integer scaleContract) {
        this.scaleContract = scaleContract;
    }

    public Boolean getScaleBalance() {
        return scaleBalance;
    }

    public void setScaleBalance(Boolean scaleBalance) {
        this.scaleBalance = scaleBalance;
    }

    public Boolean getSolutionBalance() {
        return solutionBalance;
    }

    public void setSolutionBalance(Boolean solutionBalance) {
        this.solutionBalance = solutionBalance;
    }

    public Double getTaskMoney() {
        return taskMoney;
    }

    public void setTaskMoney(Double taskMoney) {
        this.taskMoney = taskMoney;
    }

    public Double getMakeMoney() {
        return makeMoney;
    }

    public void setMakeMoney(Double makeMoney) {
        this.makeMoney = makeMoney;
    }

    public Double getForecastMoney() {
        return forecastMoney;
    }

    public void setForecastMoney(Double forecastMoney) {
        this.forecastMoney = forecastMoney;
    }

    public Double getForecastFinishMoney() {
        return forecastFinishMoney;
    }

    public void setForecastFinishMoney(Double forecastFinishMoney) {
        this.forecastFinishMoney = forecastFinishMoney;
    }

    public Double getForecastMarchMoney() {
        return forecastMarchMoney;
    }

    public void setForecastMarchMoney(Double forecastMarchMoney) {
        this.forecastMarchMoney = forecastMarchMoney;
    }

    public Double getEstimatedMarketLosses() {
        return estimatedMarketLosses;
    }

    public void setEstimatedMarketLosses(Double estimatedMarketLosses) {
        this.estimatedMarketLosses = estimatedMarketLosses;
    }

    public String getGuarantor() {
        return guarantor;
    }

    public void setGuarantor(String guarantor) {
        this.guarantor = guarantor;
    }

    public String getGuarantorIdea() {
        return guarantorIdea;
    }

    public void setGuarantorIdea(String guarantorIdea) {
        this.guarantorIdea = guarantorIdea;
    }

    public Boolean getImplement() {
        return implement;
    }

    public void setImplement(Boolean implement) {
        this.implement = implement;
    }

    public String getManagerIdea() {
        return managerIdea;
    }

    public void setManagerIdea(String managerIdea) {
        this.managerIdea = managerIdea;
    }

    public String getPlanIdea() {
        return planIdea;
    }

    public void setPlanIdea(String planIdea) {
        this.planIdea = planIdea;
    }

    public String getBudgetIdea() {
        return budgetIdea;
    }

    public void setBudgetIdea(String budgetIdea) {
        this.budgetIdea = budgetIdea;
    }

    public Boolean getPartial() {
        return partial;
    }

    public void setPartial(Boolean partial) {
        this.partial = partial;
    }

    public Double getPartialMoney() {
        return partialMoney;
    }

    public void setPartialMoney(Double partialMoney) {
        this.partialMoney = partialMoney;
    }

    public Double getContractPersist() {
        return contractPersist;
    }

    public void setContractPersist(Double contractPersist) {
        this.contractPersist = contractPersist;
    }

    public Boolean getPersist() {
        return persist;
    }

    public void setPersist(Boolean persist) {
        this.persist = persist;
    }

    public LocalDate getExpectStartDate() {
        return expectStartDate;
    }

    public void setExpectStartDate(LocalDate expectStartDate) {
        this.expectStartDate = expectStartDate;
    }

    public LocalDate getRealityStartDate() {
        return realityStartDate;
    }

    public void setRealityStartDate(LocalDate realityStartDate) {
        this.realityStartDate = realityStartDate;
    }

    public LocalDate getRealityCompleteTime() {
        return realityCompleteTime;
    }

    public void setRealityCompleteTime(LocalDate realityCompleteTime) {
        this.realityCompleteTime = realityCompleteTime;
    }

    public LocalDate getExpectCompleteTime() {
        return expectCompleteTime;
    }

    public void setExpectCompleteTime(LocalDate expectCompleteTime) {
        this.expectCompleteTime = expectCompleteTime;
    }

    public String getNotApproach() {
        return notApproach;
    }

    public void setNotApproach(String notApproach) {
        this.notApproach = notApproach;
    }

    public String getApproach() {
        return approach;
    }

    public void setApproach(String approach) {
        this.approach = approach;
    }

    public String getShutdown() {
        return shutdown;
    }

    public void setShutdown(String shutdown) {
        this.shutdown = shutdown;
    }

    public String getMarch() {
        return march;
    }

    public void setMarch(String march) {
        this.march = march;
    }

    public String getComplete() {
        return complete;
    }

    public void setComplete(String complete) {
        this.complete = complete;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public String getInitialTest() {
        return initialTest;
    }

    public void setInitialTest(String initialTest) {
        this.initialTest = initialTest;
    }

    public String getFinalTest() {
        return finalTest;
    }

    public void setFinalTest(String finalTest) {
        this.finalTest = finalTest;
    }

    public Boolean getSettlementProcess() {
        return settlementProcess;
    }

    public void setSettlementProcess(Boolean settlementProcess) {
        this.settlementProcess = settlementProcess;
    }

    public Boolean getAccount() {
        return account;
    }

    public void setAccount(Boolean account) {
        this.account = account;
    }

    public Boolean getCloseSingle() {
        return closeSingle;
    }

    public void setCloseSingle(Boolean closeSingle) {
        this.closeSingle = closeSingle;
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

    public String getInnerProject() {
        return innerProject;
    }

    public void setInnerProject(String innerProject) {
        this.innerProject = innerProject;
    }

    public String getBusinessAssessProject() {
        return businessAssessProject;
    }

    public void setBusinessAssessProject(String businessAssessProject) {
        this.businessAssessProject = businessAssessProject;
    }

    public String getBusinessAssessCase() {
        return businessAssessCase;
    }

    public void setBusinessAssessCase(String businessAssessCase) {
        this.businessAssessCase = businessAssessCase;
    }

    public String getBusinessReplyFeedback() {
        return businessReplyFeedback;
    }

    public void setBusinessReplyFeedback(String businessReplyFeedback) {
        this.businessReplyFeedback = businessReplyFeedback;
    }

    public String getProjectCharge() {
        return projectCharge;
    }

    public void setProjectCharge(String projectCharge) {
        this.projectCharge = projectCharge;
    }

    public String getHandlers() {
        return handlers;
    }

    public void setHandlers(String handlers) {
        this.handlers = handlers;
    }

    public Boolean getArchive() {
        return archive;
    }

    public void setArchive(Boolean archive) {
        this.archive = archive;
    }

    public Double getArchiveNum() {
        return archiveNum;
    }

    public void setArchiveNum(Double archiveNum) {
        this.archiveNum = archiveNum;
    }

    public String getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(String storageLocation) {
        this.storageLocation = storageLocation;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public LocalDate getSummarizeStartDate() {
        return summarizeStartDate;
    }

    public void setSummarizeStartDate(LocalDate summarizeStartDate) {
        this.summarizeStartDate = summarizeStartDate;
    }

    public LocalDate getSummarizeEndDate() {
        return summarizeEndDate;
    }

    public void setSummarizeEndDate(LocalDate summarizeEndDate) {
        this.summarizeEndDate = summarizeEndDate;
    }

    public LocalDate getSummarizeFinishDate() {
        return summarizeFinishDate;
    }

    public void setSummarizeFinishDate(LocalDate summarizeFinishDate) {
        this.summarizeFinishDate = summarizeFinishDate;
    }

    public Boolean getSummarizeFinish() {
        return summarizeFinish;
    }

    public void setSummarizeFinish(Boolean summarizeFinish) {
        this.summarizeFinish = summarizeFinish;
    }

    public String getProgressStatus() {
        return progressStatus;
    }

    public void setProgressStatus(String progressStatus) {
        this.progressStatus = progressStatus;
    }
}