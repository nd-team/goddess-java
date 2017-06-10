package com.bjike.goddess.attainment.bo;

import com.bjike.goddess.attainment.enums.ScopeType;
import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 调研分析业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:50 ]
 * @Description: [ 调研分析业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SurveyAnalyseBO extends BaseBO {

    /**
     * 调研计划id
     */
    private String planId;

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
    private String demandId;

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
     * 分析人
     */
    private String assayer;

    /**
     * 分析结果1
     */
    private String resultOne;

    /**
     * 分析结果2
     */
    private String resultTwo;

    /**
     * 备注
     */
    private String remark;


    public String getAssayer() {
        return assayer;
    }

    public void setAssayer(String assayer) {
        this.assayer = assayer;
    }

    public String getResultOne() {
        return resultOne;
    }

    public void setResultOne(String resultOne) {
        this.resultOne = resultOne;
    }

    public String getResultTwo() {
        return resultTwo;
    }

    public void setResultTwo(String resultTwo) {
        this.resultTwo = resultTwo;
    }

    public String getRemark() {
        return remark;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
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

    public String getDemandId() {
        return demandId;
    }

    public void setDemandId(String demandId) {
        this.demandId = demandId;
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

    public void setRemark(String remark) {
        this.remark = remark;
    }
}