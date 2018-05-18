package com.bjike.goddess.moneyprepare.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 项目分配业务传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-07-08 02:30 ]
 * @Description: [ 项目分配业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectDetailsBO extends BaseBO {

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