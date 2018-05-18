package com.bjike.goddess.managepromotion.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 技能定级C
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-23 10:22 ]
 * @Description: [ 技能定级C ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "managepromotion_skillgradingc")
public class SkillGradingC extends BaseEntity {


    /**
     * 档次
     */
    @Column(nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '档次'")
    private String grade;

    /**
     * 各档次晋升间隔时间（月）
     */
    @Column(nullable = false,columnDefinition = "INT(5)   COMMENT '各档次晋升间隔时间（月）'")
    private Integer gradeAfterTime;

    /**
     * 技能职衔分数
     */
    @Column(nullable = false,columnDefinition = "INT(5)   COMMENT '技能职衔分数'")
    private Integer technicalRank;

    /**
     * 转换额度（1分）
     */
    @Column(nullable = false,columnDefinition = "INT(5)   COMMENT '转换额度（1分）'")
    private Integer convertLine;

    /**
     * 标准
     */
    @Column(nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '标准'")
    private String standard;

    /**
     * 条件
     */
    @Column(nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '条件'")
    private String conditions;

    /**
     * 补助额度（元）
     */
    @Column(nullable = false,columnDefinition = "INT(5)   COMMENT '补助额度（元）'")
    private Integer subsidiesAmount;

    /**
     * 职衔补助额度
     */
    @Column(nullable = false,columnDefinition = "INT(5)   COMMENT '职衔补助额度'")
    private Integer quotaJobTitle;

    /**
     * 补助额度合计
     */
    @Column(nullable = false,columnDefinition = "INT(5)   COMMENT '补助额度合计'")
    private Integer totalAllowance;

    /**
     * 每次晋升增长幅度
     */
    @Column(nullable = false,columnDefinition = "INT(5)   COMMENT '每次晋升增长幅度'")
    private Integer growth;
    /**
     * 技能定级B
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "skillGradingB_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '技能定级B'")
    private SkillGradingB skillGradingB;


    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getGradeAfterTime() {
        return gradeAfterTime;
    }

    public void setGradeAfterTime(Integer gradeAfterTime) {
        this.gradeAfterTime = gradeAfterTime;
    }

    public Integer getTechnicalRank() {
        return technicalRank;
    }

    public void setTechnicalRank(Integer technicalRank) {
        this.technicalRank = technicalRank;
    }

    public Integer getConvertLine() {
        return convertLine;
    }

    public void setConvertLine(Integer convertLine) {
        this.convertLine = convertLine;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public Integer getSubsidiesAmount() {
        return subsidiesAmount;
    }

    public void setSubsidiesAmount(Integer subsidiesAmount) {
        this.subsidiesAmount = subsidiesAmount;
    }

    public Integer getQuotaJobTitle() {
        return quotaJobTitle;
    }

    public void setQuotaJobTitle(Integer quotaJobTitle) {
        this.quotaJobTitle = quotaJobTitle;
    }

    public Integer getTotalAllowance() {
        return totalAllowance;
    }

    public void setTotalAllowance(Integer totalAllowance) {
        this.totalAllowance = totalAllowance;
    }

    public Integer getGrowth() {
        return growth;
    }

    public void setGrowth(Integer growth) {
        this.growth = growth;
    }

    public SkillGradingB getSkillGradingB() {
        return skillGradingB;
    }

    public void setSkillGradingB(SkillGradingB skillGradingB) {
        this.skillGradingB = skillGradingB;
    }
}