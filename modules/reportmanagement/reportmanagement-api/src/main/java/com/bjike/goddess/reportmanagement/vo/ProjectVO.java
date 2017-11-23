package com.bjike.goddess.reportmanagement.vo;

import com.bjike.goddess.reportmanagement.enums.ProjectType;

/**
 * 项目表表现层对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-20 01:58 ]
 * @Description: [ 项目表表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectVO {

    /**
     * id
     */
    private String id;
    /**
     * 项目
     */
    private String projectName;

    /**
     * 项目类型
     */
    private ProjectType projectType;


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

    public ProjectType getProjectType() {
        return projectType;
    }

    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }
}