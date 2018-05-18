package com.bjike.goddess.recruit.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 招聘情况统计
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-20 08:26 ]
 * @Description: [ 招聘情况统计 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "recruit_situation")
public class Situation extends BaseEntity {

    /**
     * 日期
     */
    @Column(name = "date",  columnDefinition = "DATE   COMMENT '日期'")
    private LocalDate date;

    /**
     * 招聘地区
     */
    @Column(name = "recruitArea",  columnDefinition = "VARCHAR(255)   COMMENT '招聘地区'")
    private String recruitArea;

    /**
     * 招聘部门项目组
     */
    @Column(name = "recruitDepart",  columnDefinition = "VARCHAR(255)   COMMENT '招聘部门项目组'")
    private String recruitDepart;

    /**
     * 招聘岗位
     */
    @Column(name = "recruitPosition",  columnDefinition = "VARCHAR(255)   COMMENT '招聘岗位'")
    private String recruitPosition;

    /**
     * 计划日简历筛选量
     */
    @Column( columnDefinition = "INT(10)   COMMENT '计划日简历筛选量'")
    private Integer planFilterCount;

    /**
     * 实际日简历筛选量
     */
    @Column( columnDefinition = "INT(10)   COMMENT '实际日简历筛选量'")
    private Integer actualFilterCount;

    /**
     * 日简历筛选量差异
     */
    @Column( columnDefinition = "INT(10)   COMMENT '日简历筛选量差异'")
    private Integer dayFilterCountDiff;

    /**
     * 使用建立点数
     */
    @Column(name = "buildCount",  columnDefinition = "VARCHAR(255)   COMMENT '使用建立点数'")
    private String buildCount;

    /**
     * 计划日邀约面试量
     */
    @Column( columnDefinition = "INT(10)   COMMENT '计划日邀约面试量'")
    private Integer planInviteCount;

    /**
     * 实际日邀约面试量
     */
    @Column( columnDefinition = "INT(10)   COMMENT '实际日邀约面试量'")
    private Integer actualInviteCount;

    /**
     * 日邀约面试量差异
     */
    @Column( columnDefinition = "INT(10)   COMMENT '日邀约面试量差异'")
    private Integer dayInviteCountDiff;

    /**
     * 计划日面试量
     */
    @Column( columnDefinition = "INT(10)   COMMENT '计划日面试量'")
    private Integer planInterviewCount;

    /**
     * 实际日面试量
     */
    @Column( columnDefinition = "INT(10)   COMMENT '实际日面试量'")
    private Integer actualInterviewCount;

    /**
     * 日面试量差异
     */
    @Column( columnDefinition = "INT(10)   COMMENT '日面试量差异'")
    private Integer dayInterviewCountDiff;

    /**
     * 计划日成功通过面试量
     */
    @Column( columnDefinition = "INT(10)   COMMENT '计划日成功通过面试量'")
    private Integer planPassInterviewCount;

    /**
     * 实际日成功通过面试量
     */
    @Column( columnDefinition = "INT(10)   COMMENT '实际日成功通过面试量'")
    private Integer actualPassInterviewCount;

    /**
     * 日成功通过面试量差异
     */
    @Column( columnDefinition = "INT(10)   COMMENT '日成功通过面试量差异'")
    private Integer dayPassInterviewCount;

    /**
     * 负责招聘员工
     */
    @Column(name = "principalStaff",  columnDefinition = "VARCHAR(255)   COMMENT '负责招聘员工'")
    private String principalStaff;


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getRecruitArea() {
        return recruitArea;
    }

    public void setRecruitArea(String recruitArea) {
        this.recruitArea = recruitArea;
    }

    public String getRecruitDepart() {
        return recruitDepart;
    }

    public void setRecruitDepart(String recruitDepart) {
        this.recruitDepart = recruitDepart;
    }

    public String getRecruitPosition() {
        return recruitPosition;
    }

    public void setRecruitPosition(String recruitPosition) {
        this.recruitPosition = recruitPosition;
    }

    public Integer getPlanFilterCount() {
        return planFilterCount;
    }

    public void setPlanFilterCount(Integer planFilterCount) {
        this.planFilterCount = planFilterCount;
    }

    public Integer getActualFilterCount() {
        return actualFilterCount;
    }

    public void setActualFilterCount(Integer actualFilterCount) {
        this.actualFilterCount = actualFilterCount;
    }

    public Integer getDayFilterCountDiff() {
        return dayFilterCountDiff;
    }

    public void setDayFilterCountDiff(Integer dayFilterCountDiff) {
        this.dayFilterCountDiff = dayFilterCountDiff;
    }

    public String getBuildCount() {
        return buildCount;
    }

    public void setBuildCount(String buildCount) {
        this.buildCount = buildCount;
    }

    public Integer getPlanInviteCount() {
        return planInviteCount;
    }

    public void setPlanInviteCount(Integer planInviteCount) {
        this.planInviteCount = planInviteCount;
    }

    public Integer getActualInviteCount() {
        return actualInviteCount;
    }

    public void setActualInviteCount(Integer actualInviteCount) {
        this.actualInviteCount = actualInviteCount;
    }

    public Integer getDayInviteCountDiff() {
        return dayInviteCountDiff;
    }

    public void setDayInviteCountDiff(Integer dayInviteCountDiff) {
        this.dayInviteCountDiff = dayInviteCountDiff;
    }

    public Integer getPlanInterviewCount() {
        return planInterviewCount;
    }

    public void setPlanInterviewCount(Integer planInterviewCount) {
        this.planInterviewCount = planInterviewCount;
    }

    public Integer getActualInterviewCount() {
        return actualInterviewCount;
    }

    public void setActualInterviewCount(Integer actualInterviewCount) {
        this.actualInterviewCount = actualInterviewCount;
    }

    public Integer getDayInterviewCountDiff() {
        return dayInterviewCountDiff;
    }

    public void setDayInterviewCountDiff(Integer dayInterviewCountDiff) {
        this.dayInterviewCountDiff = dayInterviewCountDiff;
    }

    public Integer getPlanPassInterviewCount() {
        return planPassInterviewCount;
    }

    public void setPlanPassInterviewCount(Integer planPassInterviewCount) {
        this.planPassInterviewCount = planPassInterviewCount;
    }

    public Integer getActualPassInterviewCount() {
        return actualPassInterviewCount;
    }

    public void setActualPassInterviewCount(Integer actualPassInterviewCount) {
        this.actualPassInterviewCount = actualPassInterviewCount;
    }

    public Integer getDayPassInterviewCount() {
        return dayPassInterviewCount;
    }

    public void setDayPassInterviewCount(Integer dayPassInterviewCount) {
        this.dayPassInterviewCount = dayPassInterviewCount;
    }

    public String getPrincipalStaff() {
        return principalStaff;
    }

    public void setPrincipalStaff(String principalStaff) {
        this.principalStaff = principalStaff;
    }
}