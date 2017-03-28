package com.bjike.goddess.contractcommunicat.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.contractcommunicat.enums.CommunicateResult;

/**
 * 项目外包洽谈数据传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-18T09:24:12.794 ]
 * @Description: [ 项目外包洽谈数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectOutsourcingDTO extends BaseDTO {

    /**
     * 洽谈人
     */
    private String communicateUser;
    /**
     * 洽谈对象
     */
    private String communicateObj;
    /**
     * 项目结果
     */
    private CommunicateResult communicateResult;
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

    public String getCommunicateUser() {
        return communicateUser;
    }

    public void setCommunicateUser(String communicateUser) {
        this.communicateUser = communicateUser;
    }

    public String getCommunicateObj() {
        return communicateObj;
    }

    public void setCommunicateObj(String communicateObj) {
        this.communicateObj = communicateObj;
    }

    public CommunicateResult getCommunicateResult() {
        return communicateResult;
    }

    public void setCommunicateResult(CommunicateResult communicateResult) {
        this.communicateResult = communicateResult;
    }

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