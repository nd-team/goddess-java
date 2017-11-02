package com.bjike.goddess.recruit.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 招聘需求与计划
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-30 03:52 ]
 * @Description: [ 招聘需求与计划 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "recruit_recruitdemandplan")
public class RecruitDemandPlan extends BaseEntity {

    /**
     * 招聘地区
     */
    @Column(name = "area", columnDefinition = "VARCHAR(255)   COMMENT '招聘地区'")
    private String area;

    /**
     * 招聘部门/项目组
     */
    @Column(name = "projectGroup", columnDefinition = "VARCHAR(255)   COMMENT '招聘部门/项目组'")
    private String projectGroup;

    /**
     * 招聘岗位
     */
    @Column(name = "position", columnDefinition = "VARCHAR(255)   COMMENT '招聘岗位'")
    private String position;

    /**
     * 人口缺口隐患(待离职人员)
     */
    @Column(name = "populationGap", columnDefinition = "VARCHAR(255)   COMMENT '人口缺口隐患(待离职人员)'")
    private String populationGap;

    /**
     * 计划招聘人数
     */
    @Column(name = "planRecruitNum", columnDefinition = "VARCHAR(255)   COMMENT '计划招聘人数'")
    private String planRecruitNum;

    /**
     * 岗位描述
     */
    @Column(name = "positionDescribe", columnDefinition = "VARCHAR(255)   COMMENT '岗位描述'")
    private String positionDescribe;

    /**
     * 岗位要求
     */
    @Column(name = "positionRequire", columnDefinition = "VARCHAR(255)   COMMENT '岗位要求'")
    private String positionRequire;

    /**
     * 要求到岗时间
     */
    @Column(name = "postTime", columnDefinition = "DATE   COMMENT '要求到岗时间'")
    private LocalDate postTime;

    /**
     * 优先级
     */
    @Column(name = "priority", columnDefinition = "INT(10)   COMMENT '优先级'")
    private Integer priority;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    /**
     * 已完成招聘人数
     */
    @Column(name = "completeNum", columnDefinition = "INT(10)   COMMENT '已完成招聘人数'")
    private Integer completeNum;

    /**
     * 是否完成招聘
     */
    @Column(name = "is_completeRecruit", columnDefinition = "TINYINT(1)   COMMENT '是否完成招聘'", insertable = false)
    private Boolean completeRecruit;

    /**
     * 招聘渠道
     */
    @Column(name = "channels", columnDefinition = "VARCHAR(255)   COMMENT '招聘渠道'")
    private String channels;

    /**
     * 时间
     */
    @Column(name = "time", columnDefinition = "DATE   COMMENT '时间'")
    private LocalDate time;

    /**
     * 计划简历筛选量
     */
    @Column(name = "planScreenNum", columnDefinition = "INT(10)   COMMENT '计划简历筛选量'")
    private Integer planScreenNum;

    /**
     * 计划邀约面试量
     */
    @Column(name = "planInterviewAmount", columnDefinition = "INT(10)   COMMENT '计划邀约面试量'")
    private Integer planInterviewAmount;

    /**
     * 计划面试量
     */
    @Column(name = "planInterviewNum", columnDefinition = "INT(10)   COMMENT '计划面试量'")
    private Integer planInterviewNum;

    /**
     * 计划成功通过面试量
     */
    @Column(name = "planPassNum", columnDefinition = "INT(10)   COMMENT '计划成功通过面试量'")
    private Integer planPassNum;

    /**
     * 计划入职量
     */
    @Column(name = "planEntryNum", columnDefinition = "INT(10)   COMMENT '计划入职量'")
    private Integer planEntryNum;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPopulationGap() {
        return populationGap;
    }

    public void setPopulationGap(String populationGap) {
        this.populationGap = populationGap;
    }

    public String getPlanRecruitNum() {
        return planRecruitNum;
    }

    public void setPlanRecruitNum(String planRecruitNum) {
        this.planRecruitNum = planRecruitNum;
    }

    public String getPositionDescribe() {
        return positionDescribe;
    }

    public void setPositionDescribe(String positionDescribe) {
        this.positionDescribe = positionDescribe;
    }

    public String getPositionRequire() {
        return positionRequire;
    }

    public void setPositionRequire(String positionRequire) {
        this.positionRequire = positionRequire;
    }

    public LocalDate getPostTime() {
        return postTime;
    }

    public void setPostTime(LocalDate postTime) {
        this.postTime = postTime;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCompleteNum() {
        return completeNum;
    }

    public void setCompleteNum(Integer completeNum) {
        this.completeNum = completeNum;
    }

    public Boolean getCompleteRecruit() {
        return completeRecruit;
    }

    public void setCompleteRecruit(Boolean completeRecruit) {
        this.completeRecruit = completeRecruit;
    }

    public String getChannels() {
        return channels;
    }

    public void setChannels(String channels) {
        this.channels = channels;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public Integer getPlanScreenNum() {
        return planScreenNum;
    }

    public void setPlanScreenNum(Integer planScreenNum) {
        this.planScreenNum = planScreenNum;
    }

    public Integer getPlanInterviewAmount() {
        return planInterviewAmount;
    }

    public void setPlanInterviewAmount(Integer planInterviewAmount) {
        this.planInterviewAmount = planInterviewAmount;
    }

    public Integer getPlanInterviewNum() {
        return planInterviewNum;
    }

    public void setPlanInterviewNum(Integer planInterviewNum) {
        this.planInterviewNum = planInterviewNum;
    }

    public Integer getPlanPassNum() {
        return planPassNum;
    }

    public void setPlanPassNum(Integer planPassNum) {
        this.planPassNum = planPassNum;
    }

    public Integer getPlanEntryNum() {
        return planEntryNum;
    }

    public void setPlanEntryNum(Integer planEntryNum) {
        this.planEntryNum = planEntryNum;
    }
}