package com.bjike.goddess.reportmanagement.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.reportmanagement.enums.ProjectType;

/**
 * 项目表业务传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-20 01:58 ]
 * @Description: [ 项目表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectBO extends BaseBO {

    /**
     * 项目
     */
    private String projectName;

    /**
     * 项目类型
     */
    private ProjectType projectType;


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public ProjectType getProjectType() {
        return projectType;
    }

    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }
}