package com.bjike.goddess.projectissuehandle.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 项目执行中的问题受理数据传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-23 04:16 ]
 * @Description: [ 项目执行中的问题受理数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProblemAcceptDTO extends BaseDTO {

    /**
     * 内部项目名称
     */
    private String internalProjectName;

    /**
     * 工程类型
     */
    private String projectType;

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
}