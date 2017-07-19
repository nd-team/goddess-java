package com.bjike.goddess.royalty.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.royalty.entity.JobsBetB;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 岗位间对赌表C
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 04:33 ]
 * @Description: [ 岗位间对赌表C ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class JobsBetCTO extends BaseTO {

    /**
     * 岗位
     */
    @NotBlank(message = "岗位不能为空",groups = {ADD.class, EDIT.class})
    private String jobs;

    /**
     * 分配基础权重（%）
     */
    @NotNull(message = "分配基础权重（%）不能为空",groups = {ADD.class, EDIT.class})
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
     * 岗位间对赌表C
     */
    private List<JobsBetETO> jobsBetETOS;

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

    public List<JobsBetETO> getJobsBetETOS() {
        return jobsBetETOS;
    }

    public void setJobsBetETOS(List<JobsBetETO> jobsBetETOS) {
        this.jobsBetETOS = jobsBetETOS;
    }
}