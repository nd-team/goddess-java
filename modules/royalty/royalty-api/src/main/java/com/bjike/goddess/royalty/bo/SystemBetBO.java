package com.bjike.goddess.royalty.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.royalty.entity.SystemBet;

import java.util.List;

/**
 * 体系间对赌表业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-11 11:31 ]
 * @Description: [ 体系间对赌表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SystemBetBO extends BaseBO {

    /**
     * 对赌开始时间
     */
    private String betTime;

    /**
     * 地区
     */
    private String area;
    /**
     * 项目组/部门
     */
    private String projectGroup;

    /**
     * 内部项目名称
     */
    private String projectName;
    /**
     * 分值（利润额）
     */
    private Integer scoreProfit;
    /**
     * 计划分值（利润额）
     */
    private Integer planProfit;
    /**
     * 实际分值（利润额）
     */
    private Integer practiceProfit;
    /**
     * 体系
     */
    private String system;

    /**
     * 目标-部门分配基础权重（%）
     */
    private Double baseWeight;

    /**
     * 目标基础得分（分值（利润额）*目标-部门分配基础权重）
     */
    private Double basesScore;
    /**
     * 计划基础得分（计划分值（利润额）*目标-部门分配基础权重）
     */
    private Double basesScorePlan;
    /**
     * 实际基础得分（实际分值（利润额）*目标-部门分配基础权重）
     */
    private Double basesScorePractice;
    /**
     * 目标制约得分
     */
    private Double restrictScore;
    /**
     * 计划制约得分
     */
    private Double restrictScorePlan;
    /**
     * 实际制约得分
     */
    private Double restrictScorePractice;

    /**
     * 体系目标总得分（目标制约得分+目标基础得分）
     */
    private Double systemTotalScore;
    /**
     * 体系计划总得分（计划制约得分+计划基础得分）
     */
    private Double systemTotalScorePlan;
    /**
     * 体系实际总得分（实际制约得分+实际基础得分）
     */
    private Double systemTotalScorePractice;
    /**
     * 目标-部门分配对赌权重（%）
     */
    private Double betWeight;

    /**
     * 指标编号
     */
    private String indexNum;

    /**
     * 指标名称
     */
    private String indexName;

    /**
     * 部门对赌承诺-确认目标值
     */
    private String confirmTargetValue;

    /**
     * 实际目标值
     */
    private String actualTargetValue;

    /**
     * 项目对赌是否达标
     */
    private Boolean standard;
    /**
     * 目标对赌得分（分值（利润额）*目标-部门分配对赌权重）
     */
    private Double betScore;
    /**
     * 计划对赌得分（计划分值（利润额）*目标-部门分配对赌权重）
     */
    private Double betScorePlan;
    /**
     * 实际对赌得分（实际分值（利润额）*目标-部门分配对赌权重）
     */
    private Double betScorePractice;

    /**
     * 未达标分配体系
     */
    private String unmetAllocationSystem;

    /**
     * 未达标分配
     */
    private Double unmetAllocation;

    public String getBetTime() {
        return betTime;
    }

    public void setBetTime(String betTime) {
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

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getConfirmTargetValue() {
        return confirmTargetValue;
    }

    public void setConfirmTargetValue(String confirmTargetValue) {
        this.confirmTargetValue = confirmTargetValue;
    }

    public String getUnmetAllocationSystem() {
        return unmetAllocationSystem;
    }

    public void setUnmetAllocationSystem(String unmetAllocationSystem) {
        this.unmetAllocationSystem = unmetAllocationSystem;
    }

    public Double getBaseWeight() {
        return baseWeight;
    }

    public void setBaseWeight(Double baseWeight) {
        this.baseWeight = baseWeight;
    }

    public Double getBasesScore() {
        return basesScore;
    }

    public void setBasesScore(Double basesScore) {
        this.basesScore = basesScore;
    }

    public Double getBasesScorePlan() {
        return basesScorePlan;
    }

    public void setBasesScorePlan(Double basesScorePlan) {
        this.basesScorePlan = basesScorePlan;
    }

    public Double getBasesScorePractice() {
        return basesScorePractice;
    }

    public void setBasesScorePractice(Double basesScorePractice) {
        this.basesScorePractice = basesScorePractice;
    }

    public Double getRestrictScore() {
        return restrictScore;
    }

    public void setRestrictScore(Double restrictScore) {
        this.restrictScore = restrictScore;
    }

    public Double getRestrictScorePlan() {
        return restrictScorePlan;
    }

    public void setRestrictScorePlan(Double restrictScorePlan) {
        this.restrictScorePlan = restrictScorePlan;
    }

    public Double getRestrictScorePractice() {
        return restrictScorePractice;
    }

    public void setRestrictScorePractice(Double restrictScorePractice) {
        this.restrictScorePractice = restrictScorePractice;
    }

    public Double getSystemTotalScore() {
        return systemTotalScore;
    }

    public void setSystemTotalScore(Double systemTotalScore) {
        this.systemTotalScore = systemTotalScore;
    }

    public Double getSystemTotalScorePlan() {
        return systemTotalScorePlan;
    }

    public void setSystemTotalScorePlan(Double systemTotalScorePlan) {
        this.systemTotalScorePlan = systemTotalScorePlan;
    }

    public Double getSystemTotalScorePractice() {
        return systemTotalScorePractice;
    }

    public void setSystemTotalScorePractice(Double systemTotalScorePractice) {
        this.systemTotalScorePractice = systemTotalScorePractice;
    }

    public Double getBetWeight() {
        return betWeight;
    }

    public void setBetWeight(Double betWeight) {
        this.betWeight = betWeight;
    }

    public String getIndexNum() {
        return indexNum;
    }

    public void setIndexNum(String indexNum) {
        this.indexNum = indexNum;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getActualTargetValue() {
        return actualTargetValue;
    }

    public void setActualTargetValue(String actualTargetValue) {
        this.actualTargetValue = actualTargetValue;
    }

    public Boolean getStandard() {
        return standard;
    }

    public void setStandard(Boolean standard) {
        this.standard = standard;
    }

    public Double getBetScore() {
        return betScore;
    }

    public void setBetScore(Double betScore) {
        this.betScore = betScore;
    }

    public Double getBetScorePlan() {
        return betScorePlan;
    }

    public void setBetScorePlan(Double betScorePlan) {
        this.betScorePlan = betScorePlan;
    }

    public Double getBetScorePractice() {
        return betScorePractice;
    }

    public void setBetScorePractice(Double betScorePractice) {
        this.betScorePractice = betScorePractice;
    }

    public Double getUnmetAllocation() {
        return unmetAllocation;
    }

    public void setUnmetAllocation(Double unmetAllocation) {
        this.unmetAllocation = unmetAllocation;
    }
}