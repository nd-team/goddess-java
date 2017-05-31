package com.bjike.goddess.managepromotion.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 技能定级
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-23 10:22 ]
 * @Description: [ 技能定级 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "managepromotion_skillgrading")
public class SkillGrading extends BaseEntity {

    /**
     * 体系
     */
    @Column(name = "system", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '体系'")
    private String system;

    /**
     * 行业
     */
    @Column(name = "industry", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '行业'")
    private String industry;

    /**
     * 业务方向-科目
     */
    @Column(name = "subject", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务方向-科目'")
    private String subject;

    /**
     * 技能定位-专业（业务范围内包含的技能）
     */
    @Column(name = "major", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '技能定位-专业（业务范围内包含的技能）'")
    private String major;
    /**
     * 技能等级
     */
    @Column( nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '技能等级'")
    private String skillLevel;

    /**
     * 转正后间隔时间
     */
    @Column(columnDefinition = "VARCHAR(255)   COMMENT '转正后间隔时间'")
    private Integer intervalAfterTransfer;
    /**
     * 档次
     */
    @Column(nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '档次'")
    private Integer grade;

    /**
     * 各档次晋升间隔时间（月）
     */
    @Column(columnDefinition = "VARCHAR(255)   COMMENT '各档次晋升间隔时间（月）'")
    private Integer gradeAfterTime;

    /**
     * 技能职衔分数
     */
    @Column(columnDefinition = "VARCHAR(255)   COMMENT '技能职衔分数'")
    private Integer technicalRank;

    /**
     * 转换额度（1分）
     */
    @Column(columnDefinition = "VARCHAR(255)   COMMENT '转换额度（1分）'")
    private Integer convertLine;

    /**
     * 标准
     */
    @Column(columnDefinition = "VARCHAR(255)   COMMENT '标准'")
    private String standard;

    /**
     * 条件
     */
    @Column( columnDefinition = "VARCHAR(255)   COMMENT '条件'")
    private String conditions;

    /**
     * 补助额度（元）
     */
    @Column( columnDefinition = "VARCHAR(255)   COMMENT '补助额度（元）'")
    private Integer subsidiesAmount;

    /**
     * 职衔补助额度
     */
    @Column( columnDefinition = "VARCHAR(255)   COMMENT '职衔补助额度'")
    private Integer quotaJobTitle;

    /**
     * 补助额度合计
     */
    @Column(columnDefinition = "VARCHAR(255)   COMMENT '补助额度合计'")
    private Integer totalAllowance;

    /**
     * 每次晋升增长幅度
     */
    @Column( columnDefinition = "VARCHAR(255)   COMMENT '每次晋升增长幅度'")
    private Integer growth;

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }

    public Integer getIntervalAfterTransfer() {
        return intervalAfterTransfer;
    }

    public void setIntervalAfterTransfer(Integer intervalAfterTransfer) {
        this.intervalAfterTransfer = intervalAfterTransfer;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
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
}