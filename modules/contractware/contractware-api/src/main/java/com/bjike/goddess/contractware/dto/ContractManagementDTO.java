package com.bjike.goddess.contractware.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
/**
* 科目汇总数据传输对象
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-30 06:13 ]
* @Description:	[ 科目汇总数据传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class ContractManagementDTO extends BaseDTO {
    /**
     * 新增时间
     */
    private String addTime;

    /**
     * 合同性质
     */
    private String contractCharacter;

    /**
     * 地区
     */
    private String area;

    /**
     * 专业
     */
    private String major;

    /**
     * 派工单号
     */
    private String taskNum;

    /**
     * 派工项目名称
     */
    private String taskProjectName;

    /**
     * 项目内部名称
     */
    private String innerProject;

    /**
     * 内部合同编号
     */
    private String internalContractNumber;

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getContractCharacter() {
        return contractCharacter;
    }

    public void setContractCharacter(String contractCharacter) {
        this.contractCharacter = contractCharacter;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(String taskNum) {
        this.taskNum = taskNum;
    }

    public String getTaskProjectName() {
        return taskProjectName;
    }

    public void setTaskProjectName(String taskProjectName) {
        this.taskProjectName = taskProjectName;
    }

    public String getInnerProject() {
        return innerProject;
    }

    public void setInnerProject(String innerProject) {
        this.innerProject = innerProject;
    }

    public String getInternalContractNumber() {
        return internalContractNumber;
    }

    public void setInternalContractNumber(String internalContractNumber) {
        this.internalContractNumber = internalContractNumber;
    }
}