package com.bjike.goddess.moneyprepare.vo;

/**
 * 项目分配表现层对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-07-08 02:30 ]
 * @Description: [ 项目分配表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectDetailsVO {

    /**
     * id
     */
    private String id;
    /**
     * 项目组
     */
    private String project;

    /**
     * 比例分配
     */
    private Double distribution;

    /**
     * 准备金
     */
    private Double fund;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Double getDistribution() {
        return distribution;
    }

    public void setDistribution(Double distribution) {
        this.distribution = distribution;
    }

    public Double getFund() {
        return fund;
    }

    public void setFund(Double fund) {
        this.fund = fund;
    }
}