package com.bjike.goddess.royalty.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;


/**
 * 体系间对赌表A
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-10 04:22 ]
 * @Description: [ 体系间对赌表A ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "royalty_systembeta")
public class SystemBetA extends BaseEntity {
    /**
     * 对赌开始时间
     */
    @Column(name = "betTime",  columnDefinition = "DATE   COMMENT '对赌开始时间'")
    private LocalDate betTime;

    /**
     * 地区
     */
    @Column(name="area", columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;
    /**
     * 项目组/部门
     */
    @Column(name = "projectGroup",  columnDefinition = "VARCHAR(255)   COMMENT '项目组/部门'")
    private String projectGroup;


    /**
     * 内部项目名称
     */
    @Column(name = "projectName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String projectName;
    /**
     * 分值（利润额）
     */
    @Column( name = "scoreProfit", columnDefinition = "INT(11)   COMMENT '分值（利润额）'")
    private Integer scoreProfit;
    /**
     * 计划分值（利润额）
     */
    @Column( name = "planProfit",columnDefinition = "INT(11)   COMMENT '计划分值（利润额）'")
    private Integer planProfit;
    /**
     * 实际分值（利润额）
     */
    @Column( name = "practiceProfit", columnDefinition = "INT(11)   COMMENT '实际分值（利润额）'")
    private Integer practiceProfit;


    public LocalDate getBetTime() {
        return betTime;
    }

    public void setBetTime(LocalDate betTime) {
        this.betTime = betTime;
    }

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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getScoreProfit() {
        return scoreProfit;
    }

    public void setScoreProfit(Integer scoreProfit) {
        this.scoreProfit = scoreProfit;
    }

    public Integer getPlanProfit() {
        return planProfit;
    }

    public void setPlanProfit(Integer planProfit) {
        this.planProfit = planProfit;
    }

    public Integer getPracticeProfit() {
        return practiceProfit;
    }

    public void setPracticeProfit(Integer practiceProfit) {
        this.practiceProfit = practiceProfit;
    }
}