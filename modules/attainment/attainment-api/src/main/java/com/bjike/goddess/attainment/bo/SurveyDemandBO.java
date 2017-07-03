package com.bjike.goddess.attainment.bo;

import com.bjike.goddess.attainment.enums.ScopeType;
import com.bjike.goddess.attainment.enums.SurveyStatus;
import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 调研需求业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 10:28 ]
 * @Description: [ 调研需求业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SurveyDemandBO extends BaseBO {

    /**
     * 调研类型
     */
    private String typeName;

    /**
     * 调研类型id
     */
    private String typeId;

    /**
     * 调研需求类型
     */
    private String demandName;

    /**
     * 是否定期
     */
    private Boolean regular;

    /**
     * 调研需求类型id
     */
    private String demandId;

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
     * 发起人
     */
    private String username;

    /**
     * 岗位
     */
    private String gradation;

    /**
     * 发起时间
     */
    private String launch;

    /**
     * 备注
     */
    private String remark;

    /**
     * 调研状态
     */
    private SurveyStatus surveyStatus;

    /**
     * 处理人
     */
    private String handle;

    /**
     * 关闭原因
     */
    private String closeReason;

    public Boolean isRegular() {
        return regular;
    }

    public void isRegular(Boolean regular) {
        this.regular = regular;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getDemandName() {
        return demandName;
    }

    public void setDemandName(String demandName) {
        this.demandName = demandName;
    }

    public String getDemandId() {
        return demandId;
    }

    public void setDemandId(String demandId) {
        this.demandId = demandId;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGradation() {
        return gradation;
    }

    public void setGradation(String gradation) {
        this.gradation = gradation;
    }

    public String getLaunch() {
        return launch;
    }

    public void setLaunch(String launch) {
        this.launch = launch;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public SurveyStatus getSurveyStatus() {
        return surveyStatus;
    }

    public void setSurveyStatus(SurveyStatus surveyStatus) {
        this.surveyStatus = surveyStatus;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getCloseReason() {
        return closeReason;
    }

    public void setCloseReason(String closeReason) {
        this.closeReason = closeReason;
    }

    public String getScopeName() {
        return scopeName;
    }

    public void setScopeName(String scopeName) {
        this.scopeName = scopeName;
    }
}