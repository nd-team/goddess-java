package com.bjike.goddess.marketactivitymanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.marketactivitymanage.type.AuditType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 市场招待记录
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-18T10:56:16.065 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MarketServeRecordTO extends BaseTO {

    public interface FUNDMODULE {
    }//运营商务部资金模块审核

    public interface EXECUTIVE {
    } //决策层审核

    /**
     * 计划活动时间点
     */
    @Pattern(groups = {ADD.class, EDIT.class}, regexp = "^[1-9]\\d{3}\\-(0?[1-9]|1[0-2])\\-(0?[1-9]|[12]\\d|3[01])\\s*(0?[1-9]|1\\d|2[0-3])(\\:(0?[1-9]|[1-5]\\d)){2}$", message = "日期格式必须符合yy-MM-dd HH:mm:ss 如2015-01-27 10:11:12")
    private String planActivityTiming;

    /**
     * 实际活动时间点
     */
    @Pattern(groups = {ADD.class, EDIT.class}, regexp = "^[1-9]\\d{3}\\-(0?[1-9]|1[0-2])\\-(0?[1-9]|[12]\\d|3[01])\\s*(0?[1-9]|1\\d|2[0-3])(\\:(0?[1-9]|[1-5]\\d)){2}$", message = "日期格式必须符合yy-MM-dd HH:mm:ss 如2015-01-27 10:11:12")
    private String actualActivityTiming;

    /**
     * 地区
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "地区不能为空")
    private String area;

    /**
     * 目的
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "目的不能为空")
    private String purpose;

    /**
     * 市场信息编号
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "市场信息编号不能为空")
    private String marketInfoNo;

    /**
     * 项目代号
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "项目代号不能为空")
    private String projectCode;

    /**
     * 项目名称
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "项目名称不能为空")
    private String projectName;

    /**
     * 项目性质
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "项目性质不能为空")
    private String projectNature;

    /**
     * 预计参加人数
     */
    @Min(groups = {ADD.class, EDIT.class}, value = 1, message = "预计参加人数必须大于0")
    private Integer predictAttendNo;

    /**
     * 实际参加人数
     */
    @Min(groups = {ADD.class, EDIT.class}, value = 1, message = "实际参加人数必须大于0")
    private Integer actualAttendNo;

    /**
     * 计划活动类型
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "计划活动类型不能为空")
    private String planActivityType;

    /**
     * 实际活动类型
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "实际活动类型不能为空")
    private String actualActivityType;

    /**
     * 分类
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "分类不能为空")
    private String classify;

    /**
     * 预计费用
     */
    @NotNull(groups = {ADD.class, EDIT.class},message = "预计费用必须大于0.0")
    private Double predictCharge;

    /**
     * 招待负责人
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "招待负责人不能为空")
    private String servePrincipal;

    /**
     * 是否临时招待
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否临时招待")
    private Boolean whetherTemporaryServe;

    /**
     * 运营商务部资金模块
     */
    @NotBlank(groups = {MarketServeRecordTO.FUNDMODULE.class}, message = "运营商务部资金模块不能为空")
    private String yyFundModule;

    /**
     * 资金模块意见
     */
    @NotBlank(groups = {MarketServeRecordTO.FUNDMODULE.class}, message = "资金模块意见不能为空")
    private String fundModuleOpinion;

    /**
     * 决策层
     */
    @NotBlank(groups = {MarketServeRecordTO.EXECUTIVE.class}, message = "决策层不能为空")
    private String decisionLevel;

    /**
     * 决策层审核意见
     */
    @NotNull(groups = {MarketServeRecordTO.EXECUTIVE.class}, message = "决策层审核意见不能为空")
    private AuditType executiveAuditOpinion;


    public String getPlanActivityTiming() {
        return planActivityTiming;
    }

    public void setPlanActivityTiming(String planActivityTiming) {
        this.planActivityTiming = planActivityTiming;
    }

    public String getActualActivityTiming() {
        return actualActivityTiming;
    }

    public void setActualActivityTiming(String actualActivityTiming) {
        this.actualActivityTiming = actualActivityTiming;
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

    public Integer getActualAttendNo() {
        return actualAttendNo;
    }

    public void setActualAttendNo(Integer actualAttendNo) {
        this.actualAttendNo = actualAttendNo;
    }

    public String getPlanActivityType() {
        return planActivityType;
    }

    public void setPlanActivityType(String planActivityType) {
        this.planActivityType = planActivityType;
    }

    public String getActualActivityType() {
        return actualActivityType;
    }

    public void setActualActivityType(String actualActivityType) {
        this.actualActivityType = actualActivityType;
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

    public Boolean getWhetherTemporaryServe() {
        return whetherTemporaryServe;
    }

    public void setWhetherTemporaryServe(Boolean whetherTemporaryServe) {
        this.whetherTemporaryServe = whetherTemporaryServe;
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

    public AuditType getExecutiveAuditOpinion() {
        return executiveAuditOpinion;
    }

    public void setExecutiveAuditOpinion(AuditType executiveAuditOpinion) {
        this.executiveAuditOpinion = executiveAuditOpinion;
    }
}