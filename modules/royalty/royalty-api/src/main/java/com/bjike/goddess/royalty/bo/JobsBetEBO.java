package com.bjike.goddess.royalty.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.royalty.entity.JobsBetB;
import com.bjike.goddess.royalty.entity.JobsBetF;

import java.util.List;

/**
 * 岗位间对赌表E业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 04:33 ]
 * @Description: [ 岗位间对赌表E业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class JobsBetEBO extends BaseBO {
    /**
     * 分配对赌权重（%）
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
     * 对赌承诺-确认目标值
     */
    private String confirmTargetValue;

    /**
     * 实际目标值
     */
    private String actualTargetValue;

    /**
     * 是否达标
     */
    private Boolean standard;

    /**
     * 目标对赌得分（体系目标总得分*目标-部门分配对赌权重）
     */
    private Double betScore;
    /**
     * 计划对赌得分（体系计划总得分*目标-部门分配对赌权重）
     */
    private Double betScorePlan;
    /**
     * 实际对赌得分（体系实际总得分*目标-部门分配对赌权重）
     */
    private Double betScorePractice;
    /**
     * 岗位间对赌表F
     */
    private List<JobsBetFBO> jobsBetFBOS;

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

    public List<JobsBetFBO> getJobsBetFBOS() {
        return jobsBetFBOS;
    }

    public void setJobsBetFBOS(List<JobsBetFBO> jobsBetFBOS) {
        this.jobsBetFBOS = jobsBetFBOS;
    }
}