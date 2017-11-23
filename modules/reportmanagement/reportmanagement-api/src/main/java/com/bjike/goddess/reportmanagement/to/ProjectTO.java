package com.bjike.goddess.reportmanagement.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.reportmanagement.enums.ProjectType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 项目表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-20 01:58 ]
 * @Description: [ 项目表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectTO extends BaseTO {

    /**
     * 项目
     */
    @NotBlank(message = "projectName", groups = {ADD.class, EDIT.class})
    private String projectName;

    /**
     * 项目类型
     */
    @NotNull(message = "projectType", groups = {ADD.class, EDIT.class})
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