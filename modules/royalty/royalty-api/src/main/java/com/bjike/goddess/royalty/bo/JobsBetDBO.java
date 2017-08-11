package com.bjike.goddess.royalty.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.royalty.entity.JobsBetB;

import java.util.List;

/**
 * 岗位间对赌表D业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 04:33 ]
 * @Description: [ 岗位间对赌表D业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class JobsBetDBO extends BaseBO {

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
     * 对赌得分（部门总得分*目标-部门分配对赌权重）
     */
    private Double betScore;
//    /**
//     * 岗位间对赌表C
//     */
//    private JobsBetCBO jobsBetCBO;
    /**
     * 岗位间对赌表E
     */
    private List<JobsBetEBO> jobsBetEBOS;


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

//    public JobsBetCBO getJobsBetCBO() {
//        return jobsBetCBO;
//    }
//
//    public void setJobsBetCBO(JobsBetCBO jobsBetCBO) {
//        this.jobsBetCBO = jobsBetCBO;
//    }

    public List<JobsBetEBO> getJobsBetEBOS() {
        return jobsBetEBOS;
    }

    public void setJobsBetEBOS(List<JobsBetEBO> jobsBetEBOS) {
        this.jobsBetEBOS = jobsBetEBOS;
    }
}