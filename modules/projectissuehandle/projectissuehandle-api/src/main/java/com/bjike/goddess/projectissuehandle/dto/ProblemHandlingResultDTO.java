package com.bjike.goddess.projectissuehandle.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 确认问题处理结果数据传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-23 04:30 ]
 * @Description: [ 确认问题处理结果数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProblemHandlingResultDTO extends BaseDTO {
    /**
     * 内部项目名称
     */
    private String internalProjectName;

    /**
     * 工程类型
     */
    private String projectType;
    /**
     * 问题对象
     */
    private String problemObject;
    /**
     * 内部项目名称
     */
    private String[] name;

    /**
     * 工程类型
     */
    private String[] type;
    /**
     * 问题对象
     */
    private String[] object;

    public String getInternalProjectName() {
        return internalProjectName;
    }

    public void setInternalProjectName(String internalProjectName) {
        this.internalProjectName = internalProjectName;
    }

    public String getProjectType() {
        return projectType;
    }


    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getProblemObject() {
        return problemObject;
    }

    public void setProblemObject(String problemObject) {
        this.problemObject = problemObject;
    }

    public String[] getName() {
        return name;
    }

    public void setName(String[] name) {
        this.name = name;
    }

    public String[] getType() {
        return type;
    }

    public void setType(String[] type) {
        this.type = type;
    }

    public String[] getObject() {
        return object;
    }

    public void setObject(String[] object) {
        this.object = object;
    }
}