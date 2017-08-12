package com.bjike.goddess.system.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 字段对接数据传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-10 11:43 ]
 * @Description: [ 字段对接数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FieldDockDTO extends BaseDTO {
    /**
     * 项目名称
     */
    private String[] projectName;

    public String[] getProjectName() {
        return projectName;
    }

    public void setProjectName(String[] projectName) {
        this.projectName = projectName;
    }
}