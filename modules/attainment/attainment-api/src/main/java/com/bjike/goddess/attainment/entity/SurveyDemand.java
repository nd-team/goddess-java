package com.bjike.goddess.attainment.entity;

import com.bjike.goddess.attainment.enums.ScopeType;
import com.bjike.goddess.attainment.enums.SurveyStatus;
import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * 调研需求
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 10:28 ]
 * @Description: [ 调研需求 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "attainment_survey_demand")
public class SurveyDemand extends BaseEntity {

    /**
     * 调研类型
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "type_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '调研类型'")
    private AttainmentType type;

    /**
     * 调研需求类型
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "demand_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '调研需求类型'")
    private DemandType demand;

    /**
     * 调研主题
     */
    @Column(name = "theme", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '调研主题'")
    private String theme;

    /**
     * 调研目的
     */
    @Column(name = "purpose", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '调研目的'")
    private String purpose;

    /**
     * 调研范围
     */
    @Column(name = "scope", nullable = false, columnDefinition = "TINYINT(2)  DEFAULT 0  COMMENT '调研范围'", insertable = false)
    private ScopeType scope;

    /**
     * 调研范围准确对象
     */
    @Column(name = "scope_ids", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '调研范围准确对象'")
    private String scope_ids;

    /**
     * 发起人
     */
    @Column(name = "username", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '发起人'")
    private String username;

    /**
     * 岗位
     */
    @Column(name = "gradation", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '岗位'")
    private String gradation;

    /**
     * 发起时间
     */
    @Column(name = "launch", nullable = false, columnDefinition = "DATETIME   COMMENT '发起时间'")
    private LocalDateTime launch;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    /**
     * 调研状态
     */
    @Column(name = "survey", nullable = false, columnDefinition = "TINYINT(2)  DEFAULT 0  COMMENT '调研状态'", insertable = false)
    private SurveyStatus surveyStatus;

    /**
     * 处理人
     */
    @Column(name = "handle", columnDefinition = "VARCHAR(255)   COMMENT '处理人'")
    private String handle;

    /**
     * 关闭原因
     */
    @Column(name = "closeReason", columnDefinition = "VARCHAR(255)   COMMENT '关闭原因'")
    private String closeReason;


    public AttainmentType getType() {
        return type;
    }

    public void setType(AttainmentType type) {
        this.type = type;
    }

    public DemandType getDemand() {
        return demand;
    }

    public void setDemand(DemandType demand) {
        this.demand = demand;
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

    public String getScope_ids() {
        return scope_ids;
    }

    public void setScope_ids(String scope_ids) {
        this.scope_ids = scope_ids;
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

    public LocalDateTime getLaunch() {
        return launch;
    }

    public void setLaunch(LocalDateTime launch) {
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
}