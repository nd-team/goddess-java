package com.bjike.goddess.royalty.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.royalty.entity.JobsBetB;

import java.util.List;

/**
 * 岗位间对赌表C业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 04:33 ]
 * @Description: [ 岗位间对赌表C业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class JobsBetCBO extends BaseBO {

    /**
     * 岗位
     */
    private String jobs;

    /**
     * 分配基础权重（%）
     */
    private Double baseWeight;

    /**
     * 基础得分（部门总得分*目标-部门分配基础权重）
     */
    private Double basesScore;

    /**
     * 制约得分
     */
    private Double restrictScore;

    /**
     * 总得分（制约得分+基础得分）
     */
    private Double departmentTotalScore;
    /**
     * 岗位间对赌表B
     */
    private JobsBetBBO jobsBetBBO;
    /**
     * 岗位间对赌表D
     */
    private List<JobsBetDBO> jobsBetDBOS;

    public String getJobs() {
        return jobs;
    }

    public void setJobs(String jobs) {
        this.jobs = jobs;
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

    public Double getRestrictScore() {
        return restrictScore;
    }

    public void setRestrictScore(Double restrictScore) {
        this.restrictScore = restrictScore;
    }

    public Double getDepartmentTotalScore() {
        return departmentTotalScore;
    }

    public void setDepartmentTotalScore(Double departmentTotalScore) {
        this.departmentTotalScore = departmentTotalScore;
    }

    public JobsBetBBO getJobsBetBBO() {
        return jobsBetBBO;
    }

    public void setJobsBetBBO(JobsBetBBO jobsBetBBO) {
        this.jobsBetBBO = jobsBetBBO;
    }

    public List<JobsBetDBO> getJobsBetDBOS() {
        return jobsBetDBOS;
    }

    public void setJobsBetDBOS(List<JobsBetDBO> jobsBetDBOS) {
        this.jobsBetDBOS = jobsBetDBOS;
    }
}