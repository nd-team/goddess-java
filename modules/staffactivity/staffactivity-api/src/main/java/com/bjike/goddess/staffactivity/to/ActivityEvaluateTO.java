package com.bjike.goddess.staffactivity.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 活动评价
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-09 09:23 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ActivityEvaluateTO extends BaseTO {

    /**
     * 活动方案
     */
    private String scheme;

    /**
     * 评价人姓名
     */
    private String evaluator;

    /**
     * 参与角色
     */
    private String attendRole;

    /**
     * 活动评分
     */
    private Integer activityScore;

    /**
     * 活动评价
     */
    private String activityEvaluate;

    /**
     * 活动心得与收获
     */
    private String experienceGain;

    /**
     * 活动建议
     */
    private String activityProposal;

    /**
     * 角色评分
     */
    private String roleScore;

    /**
     * 个人角色收获
     */
    private String roleGain;

    /**
     * 评价日期
     */
    private String evaluateDate;

    /**
     * 是否下次参与
     */
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

    public String getEvaluateDate() {
        return evaluateDate;
    }

    public void setEvaluateDate(String evaluateDate) {
        this.evaluateDate = evaluateDate;
    }

    public Boolean getIfNextAttend() {
        return ifNextAttend;
    }

    public void setIfNextAttend(Boolean ifNextAttend) {
        this.ifNextAttend = ifNextAttend;
    }
}