package com.bjike.goddess.managementpromotion.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 管理等级定级
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-22 01:57 ]
 * @Description: [ 管理等级定级 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "managementpromotion_gradelevel")
public class GradeLevel extends BaseEntity {

    /**
     * 体系
     */
    @Column(name = "system", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '体系'")
    private String system;

    /**
     * 分类
     */
    @Column(name = "classification", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '分类'")
    private String classification;

    /**
     * 管理方向
     */
    @Column(name = "direction", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '管理方向'")
    private String direction;

    /**
     * 技能等级
     */
    @Column(name = "skillLevel", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '技能等级'")
    private String skillLevel;

    /**
     * 档次
     */
    @Column(name = "grade", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '档次'")
    private String grade;

    /**
     * 职衔补助分数
     */
    @Column(name = "allowanceRank", nullable = false, columnDefinition = "INT(11)   COMMENT '职衔补助分数'")
    private Integer allowanceRank;

    /**
     * 转换额度（1分）
     */
    @Column(name = "convertLine", nullable = false, columnDefinition = "INT(11)   COMMENT '转换额度（1分）'")
    private Integer convertLine;

    /**
     * 条件
     */
    @Column(name = "conditions", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '条件'")
    private String condition;

    /**
     * 判断条件
     */
    @Column(name = "judgeCondition", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '判断条件'")
    private String judgeCondition;

    /**
     * 标准
     */
    @Column(name = "standard", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '标准'")
    private String standard;

    /**
     * 数据来源
     */
    @Column(name = "dataSource", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '数据来源'")
    private String dataSource;

    /**
     * 补助额度
     */
    @Column(name = "subsidyAmount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '补助额度'")
    private Double subsidyAmount;

    /**
     * 职衔补助额度
     */
    @Column(name = "quotaJob", nullable = false, columnDefinition = "INT(11)   COMMENT '职衔补助额度'")
    private Integer quotaJob;

    /**
     * 合计补助
     */
    @Column(name = "subsidySum", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '合计补助'")
    private Double subsidySum;

    /**
     * 每次晋升增长幅度
     */
    @Column(name = "growth", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '每次晋升增长幅度'")
    private Double growth;

    public Integer getQuotaJob() {
        return quotaJob;
    }

    public void setQuotaJob(Integer quotaJob) {
        this.quotaJob = quotaJob;
    }

    public Double getSubsidySum() {
        return subsidySum;
    }

    public void setSubsidySum(Double subsidySum) {
        this.subsidySum = subsidySum;
    }

    public Double getGrowth() {
        return growth;
    }

    public void setGrowth(Double growth) {
        this.growth = growth;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getAllowanceRank() {
        return allowanceRank;
    }

    public void setAllowanceRank(Integer allowanceRank) {
        this.allowanceRank = allowanceRank;
    }

    public Integer getConvertLine() {
        return convertLine;
    }

    public void setConvertLine(Integer convertLine) {
        this.convertLine = convertLine;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getJudgeCondition() {
        return judgeCondition;
    }

    public void setJudgeCondition(String judgeCondition) {
        this.judgeCondition = judgeCondition;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public Double getSubsidyAmount() {
        return subsidyAmount;
    }

    public void setSubsidyAmount(Double subsidyAmount) {
        this.subsidyAmount = subsidyAmount;
    }
}