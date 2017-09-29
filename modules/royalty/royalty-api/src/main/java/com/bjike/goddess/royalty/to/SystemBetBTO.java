package com.bjike.goddess.royalty.to;

import com.bjike.goddess.common.api.to.BaseTO;

import java.util.List;

/**
 * 体系间对赌表B
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-10 04:52 ]
 * @Description: [ 体系间对赌表B ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SystemBetBTO extends BaseTO {

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
     * 体系间对赌表C
     */
    private List<SystemBetCTO> systemBetCTOS;


    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
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

    public Double getRestrictScore() {
        return restrictScore;
    }

    public void setRestrictScore(Double restrictScore) {
        this.restrictScore = restrictScore;
    }

    public List<SystemBetCTO> getSystemBetCTOS() {
        return systemBetCTOS;
    }

    public void setSystemBetCTOS(List<SystemBetCTO> systemBetCTOS) {
        this.systemBetCTOS = systemBetCTOS;
    }
}