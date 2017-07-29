package com.bjike.goddess.projectmeasure.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 单个项目多个界面数据传输对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:51 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SingleProjectMultipleUIDTO extends BaseDTO {
    /**
     * 项目名称
     */
    private String projectName;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}