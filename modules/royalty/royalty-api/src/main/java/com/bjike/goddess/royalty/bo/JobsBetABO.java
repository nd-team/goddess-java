package com.bjike.goddess.royalty.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.royalty.entity.JobsBet;

import java.util.List;

/**
 * 岗位间对赌表A业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 04:21 ]
 * @Description: [ 岗位间对赌表A业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class JobsBetABO extends BaseBO {

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 分值
     */
    private Integer score;
    /**
     * 岗位间对赌表B
     */
    private List<JobsBetBBO> jobsBetBBOS;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public List<JobsBetBBO> getJobsBetBBOS() {
        return jobsBetBBOS;
    }

    public void setJobsBetBBOS(List<JobsBetBBO> jobsBetBBOS) {
        this.jobsBetBBOS = jobsBetBBOS;
    }
}