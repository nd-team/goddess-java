package com.bjike.goddess.royalty.vo;

import java.util.List;

/**
 * 岗位间对赌表A表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 04:21 ]
 * @Description: [ 岗位间对赌表A表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class JobsBetAVO {

    /**
     * id
     */
    private String id;
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
    private List<JobsBetBVO> jobsBetBVOS;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public List<JobsBetBVO> getJobsBetBVOS() {
        return jobsBetBVOS;
    }

    public void setJobsBetBVOS(List<JobsBetBVO> jobsBetBVOS) {
        this.jobsBetBVOS = jobsBetBVOS;
    }
}