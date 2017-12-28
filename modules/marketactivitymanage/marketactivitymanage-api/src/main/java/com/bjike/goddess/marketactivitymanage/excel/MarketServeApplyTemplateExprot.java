package com.bjike.goddess.marketactivitymanage.excel;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.marketactivitymanage.type.AuditType;

import java.time.LocalDateTime;

/**
 * 市场招待申请
 *
 * @Author: [lijuntao]
 * @Date: [2017-06-08 10:30]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class MarketServeApplyTemplateExprot extends BaseTO {

    /**
     * 计划活动时间点
     */
    @ExcelHeader(name = "计划活动时间点", notNull = true)
    private LocalDateTime planActivityTiming;

    /**
     * 地区
     */
    @ExcelHeader(name = "地区", notNull = true)
    private String area;

    /**
     * 目的
     */
    @ExcelHeader(name = "目的", notNull = true)
    private String purpose;

    /**
     * 市场信息编号
     */
    @ExcelHeader(name = "市场信息编号", notNull = true)
    private String marketInfoNo;

    /**
     * 项目代号
     */
    @ExcelHeader(name = "项目代号", notNull = true)
    private String projectCode;

    /**
     * 项目名称
     */
    @ExcelHeader(name = "项目名称", notNull = true)
    private String projectName;

    /**
     * 项目性质
     */
    @ExcelHeader(name = "项目性质", notNull = true)
    private String projectNature;

    /**
     * 预计参加人数
     */
    @ExcelHeader(name = "预计参加人数", notNull = true)
    private Integer predictAttendNo;

    /**
     * 计划活动类型
     */
    @ExcelHeader(name = "计划活动类型", notNull = true)
    private String planActivityType;

    /**
     * 分类
     */
    @ExcelHeader(name = "分类", notNull = true)
    private String classify;

    /**
     * 预计费用
     */
    @ExcelHeader(name = "预计费用", notNull = true)
    private Double predictCharge;

    /**
     * 招待负责人
     */
    @ExcelHeader(name = "招待负责人", notNull = true)
    private String servePrincipal;

    /**
     * 是否临时招待
     */
    @ExcelHeader(name = "是否临时招待", notNull = true)
    private String whetherTemporaryServe;

    /**
     * 运营商务部资金模块
     */
    @ExcelHeader(name = "运营商务部资金模块")
    private String yyFundModule;

    /**
     * 资金模块意见
     */
    @ExcelHeader(name = "资金模块意见")
    private String fundModuleOpinion;

    /**
     * 决策层
     */
    @ExcelHeader(name = "决策层")
    private String decisionLevel;

    /**
     * 决策层审核意见
     */
    @ExcelHeader(name = "决策层审核意见")
    private String executiveAuditOpinion;

    /**
     * 客户信息
     */
    @ExcelHeader(name = "客户信息")
    private String clientinfo;


    public LocalDateTime getPlanActivityTiming() {
        return planActivityTiming;
    }

    public void setPlanActivityTiming(LocalDateTime planActivityTiming) {
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

    public String getWhetherTemporaryServe() {
        return whetherTemporaryServe;
    }

    public void setWhetherTemporaryServe(String whetherTemporaryServe) {
        this.whetherTemporaryServe = whetherTemporaryServe;
    }

    public String getExecutiveAuditOpinion() {
        return executiveAuditOpinion;
    }

    public void setExecutiveAuditOpinion(String executiveAuditOpinion) {
        this.executiveAuditOpinion = executiveAuditOpinion;
    }
}
