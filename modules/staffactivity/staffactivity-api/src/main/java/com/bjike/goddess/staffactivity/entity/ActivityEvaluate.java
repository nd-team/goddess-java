package com.bjike.goddess.staffactivity.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 活动评价
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-09 09:23 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "staffactivity_activityevaluate")
public class ActivityEvaluate extends BaseEntity {

    /**
     * 活动方案
     */
    @Column(name = "scheme", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '活动方案'")
    private String scheme;

    /**
     * 评价人姓名
     */
    @Column(name = "evaluator", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '评价人姓名'")
    private String evaluator;

    /**
     * 参与角色
     */
    @Column(name = "attendRole", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '参与角色'")
    private String attendRole;

    /**
     * 活动评分
     */
    @Column(name = "activityScore", nullable = false, columnDefinition = "INT(11) COMMENT '活动评分'")
    private Integer activityScore;

    /**
     * 活动评价
     */
    @Column(name = "activityEvaluate", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '活动评价'")
    private String activityEvaluate;

    /**
     * 活动心得与收获
     */
    @Column(name = "experienceGain", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '活动心得与收获'")
    private String experienceGain;

    /**
     * 活动建议
     */
    @Column(name = "activityProposal", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '活动建议'")
    private String activityProposal;

    /**
     * 角色评分
     */
    @Column(name = "roleScore", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '角色评分'")
    private String roleScore;

    /**
     * 个人角色收获
     */
    @Column(name = "roleGain", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '个人角色收获'")
    private String roleGain;

    /**
     * 评价日期
     */
    @Column(name = "evaluateDate", nullable = false, columnDefinition = "DATE COMMENT '评价日期'")
    private LocalDate evaluateDate;

    /**
     * 是否下次参与
     */
    @Column(name = "ifNextAttend", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0 COMMENT '是否下次参与'")
    private Boolean ifNextAttend;


    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(String evaluator) {
        this.evaluator = evaluator;
    }

    public String getAttendRole() {
        return attendRole;
    }

    public void setAttendRole(String attendRole) {
        this.attendRole = attendRole;
    }

    public Integer getActivityScore() {
        return activityScore;
    }

    public void setActivityScore(Integer activityScore) {
        this.activityScore = activityScore;
    }

    public String getActivityEvaluate() {
        return activityEvaluate;
    }

    public void setActivityEvaluate(String activityEvaluate) {
        this.activityEvaluate = activityEvaluate;
    }

    public String getExperienceGain() {
        return experienceGain;
    }

    public void setExperienceGain(String experienceGain) {
        this.experienceGain = experienceGain;
    }

    public String getActivityProposal() {
        return activityProposal;
    }

    public void setActivityProposal(String activityProposal) {
        this.activityProposal = activityProposal;
    }

    public String getRoleScore() {
        return roleScore;
    }

    public void setRoleScore(String roleScore) {
        this.roleScore = roleScore;
    }

    public String getRoleGain() {
        return roleGain;
    }

    public void setRoleGain(String roleGain) {
        this.roleGain = roleGain;
    }

    public LocalDate getEvaluateDate() {
        return evaluateDate;
    }

    public void setEvaluateDate(LocalDate evaluateDate) {
        this.evaluateDate = evaluateDate;
    }

    public Boolean getIfNextAttend() {
        return ifNextAttend;
    }

    public void setIfNextAttend(Boolean ifNextAttend) {
        this.ifNextAttend = ifNextAttend;
    }
}