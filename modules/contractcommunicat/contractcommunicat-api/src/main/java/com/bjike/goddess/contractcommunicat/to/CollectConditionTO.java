package com.bjike.goddess.contractcommunicat.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 项目承包洽谈
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-17T17:21:34.913 ]
 * @Description: [ 项目承包洽谈 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CollectConditionTO extends BaseTO {

    /**
     * 内部项目名称
     */
    private String contractInProject;
    /**
     * 创建开始范围时间
     */
    private String startTime;
    /**
     * 创建结束范围时间
     */
    private String endTime;

    public String getContractInProject() {
        return contractInProject;
    }

    public void setContractInProject(String contractInProject) {
        this.contractInProject = contractInProject;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}