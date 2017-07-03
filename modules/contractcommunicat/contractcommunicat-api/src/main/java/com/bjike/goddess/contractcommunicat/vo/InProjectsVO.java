package com.bjike.goddess.contractcommunicat.vo;

import com.bjike.goddess.contractcommunicat.enums.CommunicateResult;

/**
 * 项目承包洽谈表现层对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-17T17:21:34.914 ]
 * @Description: [ 项目承包洽谈表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class InProjectsVO {

    /**
     * 内部项目名称
     */
    private String contractInProject;

    public String getContractInProject() {
        return contractInProject;
    }

    public void setContractInProject(String contractInProject) {
        this.contractInProject = contractInProject;
    }
}