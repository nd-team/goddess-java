package com.bjike.goddess.marketactivitymanage.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.marketactivitymanage.type.AuditType;

import javax.validation.constraints.NotNull;

/**
 * 市场招待申请
 *
 * @Author: [lijuntao]
 * @Date: [2017-06-08 10:30]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class MarketServeApplyImprotTO extends BaseTO {

    public interface TestAdd {
    }

    /**
     * 计划活动时间点
     */
    @NotNull(groups = {MarketServeApplyImprotTO.TestAdd.class}, message = "计划活动时间点不能为空")
    private String planActivityTiming;

    /**
     * 地区
     */
    @NotNull(groups = {MarketServeApplyImprotTO.TestAdd.class}, message = "地区不能为空")
    private String area;

    /**
     * 目的
     */
    @NotNull(groups = {MarketServeApplyImprotTO.TestAdd.class}, message = "目的不能为空")
    private String purpose;

    /**
     * 市场信息编号
     */
    @NotNull(groups = {MarketServeApplyImprotTO.TestAdd.class}, message = "市场信息编号不能为空")
    private String marketInfoNo;

    /**
     * 项目代号
     */
    @NotNull(groups = {MarketServeApplyImprotTO.TestAdd.class}, message = "项目代号不能为空")
    private String projectCode;

    /**
     * 项目名称
     */
    @NotNull(groups = {MarketServeApplyImprotTO.TestAdd.class}, message = "项目名称不能为空")
    private String projectName;

    /**
     * 项目性质
     */
    @NotNull(groups = {MarketServeApplyImprotTO.TestAdd.class}, message = "项目性质不能为空")
    private String projectNature;

    /**
     * 预计参加人数
     */
    @NotNull(groups = {MarketServeApplyImprotTO.TestAdd.class}, message = "预计参加人数不能为空")
    private Integer predictAttendNo;

    /**
     * 计划活动类型
     */
    @NotNull(groups = {MarketServeApplyImprotTO.TestAdd.class}, message = "计划活动类型不能为空")
    private String planActivityType;

    /**
     * 分类
     */
    @NotNull(groups = {MarketServeApplyImprotTO.TestAdd.class}, message = "分类不能为空")
    private String classify;

    /**
     * 预计费用
     */
    @NotNull(groups = {MarketServeApplyImprotTO.TestAdd.class}, message = "预计费用不能为空")
    private Double predictCharge;

    /**
     * 招待负责人
     */
    @NotNull(groups = {MarketServeApplyImprotTO.TestAdd.class}, message = "招待负责人不能为空")
    private String servePrincipal;

    /**
     * 是否临时招待
     */
    @NotNull(groups = {MarketServeApplyImprotTO.TestAdd.class}, message = "是否临时招待不能为空")
    private Boolean whetherTemporaryServe;

    /**
     * 运营商务部资金模块
     */
    private String yyFundModule;

    /**
     * 资金模块意见
     */
    private String fundModuleOpinion;

    /**
     * 决策层
     */
    private String decisionLevel;

    /**
     * 决策层审核意见
     */
    private AuditType executiveAuditOpinion;

    /**
     * 客户信息
     */
    private String clientinfo;


    public String getPlanActivityTiming() {
        return planActivityTiming;
    }

    public void setPlanActivityTiming(String planActivityTiming) {
        this.planActivityTiming = planActivityTiming;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getMarketInfoNo() {
        return marketInfoNo;
    }

    public void setMarketInfoNo(String marketInfoNo) {
        this.marketInfoNo = marketInfoNo;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectNature() {
        return projectNature;
    }

    public void setProjectNature(String projectNature) {
        this.projectNature = projectNature;
    }

    public Integer getPredictAttendNo() {
        return predictAttendNo;
    }

    public void setPredictAttendNo(Integer predictAttendNo) {
        this.predictAttendNo = predictAttendNo;
    }

    public String getPlanActivityType() {
        return planActivityType;
    }

    public void setPlanActivityType(String planActivityType) {
        this.planActivityType = planActivityType;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public Double getPredictCharge() {
        return predictCharge;
    }

    public void setPredictCharge(Double predictCharge) {
        this.predictCharge = predictCharge;
    }

    public String getServePrincipal() {
        return servePrincipal;
    }

    public void setServePrincipal(String servePrincipal) {
        this.servePrincipal = servePrincipal;
    }

    public String getYyFundModule() {
        return yyFundModule;
    }

    public void setYyFundModule(String yyFundModule) {
        this.yyFundModule = yyFundModule;
    }

    public String getFundModuleOpinion() {
        return fundModuleOpinion;
    }

    public void setFundModuleOpinion(String fundModuleOpinion) {
        this.fundModuleOpinion = fundModuleOpinion;
    }

    public String getDecisionLevel() {
        return decisionLevel;
    }

    public void setDecisionLevel(String decisionLevel) {
        this.decisionLevel = decisionLevel;
    }

    public String getClientinfo() {
        return clientinfo;
    }

    public void setClientinfo(String clientinfo) {
        this.clientinfo = clientinfo;
    }

    public Boolean getWhetherTemporaryServe() {
        return whetherTemporaryServe;
    }

    public void setWhetherTemporaryServe(Boolean whetherTemporaryServe) {
        this.whetherTemporaryServe = whetherTemporaryServe;
    }

    public AuditType getExecutiveAuditOpinion() {
        return executiveAuditOpinion;
    }

    public void setExecutiveAuditOpinion(AuditType executiveAuditOpinion) {
        this.executiveAuditOpinion = executiveAuditOpinion;
    }
}
