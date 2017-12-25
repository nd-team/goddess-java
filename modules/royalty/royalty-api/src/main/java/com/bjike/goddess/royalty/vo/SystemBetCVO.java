package com.bjike.goddess.royalty.vo;

import java.util.List;

/**
 * 体系间对赌表C表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-10 04:56 ]
 * @Description: [ 体系间对赌表C表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SystemBetCVO {

    /**
     * id
     */
    private String id;
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
     * 体系间对赌表D
     */
    private List<SystemBetDVO> systemBetDBOS;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getConfirmTargetValue() {
        return confirmTargetValue;
    }

    public void setConfirmTargetValue(String confirmTargetValue) {
        this.confirmTargetValue = confirmTargetValue;
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

    public List<SystemBetDVO> getSystemBetDBOS() {
        return systemBetDBOS;
    }

    public void setSystemBetDBOS(List<SystemBetDVO> systemBetDBOS) {
        this.systemBetDBOS = systemBetDBOS;
    }
}