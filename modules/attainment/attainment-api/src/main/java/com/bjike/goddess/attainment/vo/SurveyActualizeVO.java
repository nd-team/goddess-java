package com.bjike.goddess.attainment.vo;

import com.bjike.goddess.attainment.enums.ScopeType;
import com.bjike.goddess.attainment.enums.SurveyStatus;

/**
 * 调研实施记录表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 10:58 ]
 * @Description: [ 调研实施记录表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SurveyActualizeVO {

    /**
     * id
     */
    private String id;

    /**
     * 调研计划id
     */
    private String plan_id;

    /**
     * 编号
     */
    private String serialNumber;

    /**
     * 发起人
     */
    private String username;

    /**
     * 需求ID
     */
    private String demand_id;

    /**
     * 调研类型
     */
    private String typeName;

    /**
     * 调研需求类型
     */
    private String demandName;

    /**
     * 是否定期
     */
    private Boolean regular;

    /**
     * 调研主题
     */
    private String theme;

    /**
     * 调研目的
     */
    private String purpose;

    /**
     * 调研范围
     */
    private ScopeType scope;

    /**
     * 调研对象
     */
    private String scopeName;

    /**
     * 调研实际开始时间
     */
    private String startTime;

    /**
     * 调研实际结束时间
     */
    private String endTime;

    /**
     * 调研表制作实际完成时间
     */
    private String finishTime;

    /**
     * 调研状态
     */
    private SurveyStatus survey;

    /**
     * 调研表
     */
    private String questionnaire;

    /**
     * 备注
     */
    private String remark;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(String plan_id) {
        this.plan_id = plan_id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDemand_id() {
        return demand_id;
    }

    public void setDemand_id(String demand_id) {
        this.demand_id = demand_id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDemandName() {
        return demandName;
    }

    public void setDemandName(String demandName) {
        this.demandName = demandName;
    }

    public Boolean isRegular() {
        return regular;
    }

    public void isRegular(Boolean regular) {
        this.regular = regular;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public ScopeType getScope() {
        return scope;
    }

    public void setScope(ScopeType scope) {
        this.scope = scope;
    }

    public String getScopeName() {
        return scopeName;
    }

    public void setScopeName(String scopeName) {
        this.scopeName = scopeName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public SurveyStatus getSurvey() {
        return survey;
    }

    public void setSurvey(SurveyStatus survey) {
        this.survey = survey;
    }

    public String getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(String questionnaire) {
        this.questionnaire = questionnaire;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}