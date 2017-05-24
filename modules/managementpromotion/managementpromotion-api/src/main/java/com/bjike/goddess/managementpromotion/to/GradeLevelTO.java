package com.bjike.goddess.managementpromotion.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 管理等级定级
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-22 01:57 ]
 * @Description: [ 管理等级定级 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class GradeLevelTO extends BaseTO {

    /**
     * 体系
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "体系不能为空")
    private String system;

    /**
     * 分类
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "分类不能为空")
    private String classification;

    /**
     * 管理方向
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "管理方向不能为空")
    private String direction;

    /**
     * 技能等级
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "技能等级不能为空")
    private String skillLevel;

    /**
     * 档次
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "档次不能为空")
    private String grade;

    /**
     * 职衔补助分数
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "职衔补助分数不能为空")
    @Min(value = 0, groups = {ADD.class, EDIT.class}, message = "职衔补助分数不能小于0")
    private Integer allowanceRank;

    /**
     * 转换额度（1分）
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "转换额度（1分）不能为空")
    @Min(value = 0, groups = {ADD.class, EDIT.class}, message = "转换额度（1分）不能小于0")
    private Integer convertLine;

    /**
     * 条件
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "条件不能为空")
    private String condition;

    /**
     * 判断条件
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "判断条件不能为空")
    private String judgeCondition;

    /**
     * 标准
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "标准不能为空")
    private String standard;

    /**
     * 数据来源
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "数据来源不能为空")
    private String dataSource;

    /**
     * 补助额度
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "补助额度不能为空")
    @DecimalMin(value = "0.00", inclusive = false, groups = {ADD.class, EDIT.class}, message = "补助额度不能小于0")
    private Double subsidyAmount;


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