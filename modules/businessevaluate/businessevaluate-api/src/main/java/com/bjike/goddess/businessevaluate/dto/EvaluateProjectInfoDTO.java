package com.bjike.goddess.businessevaluate.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 项目基本信息数据传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-27 03:59 ]
 * @Description: [ 项目基本信息数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class EvaluateProjectInfoDTO extends BaseDTO {

    /**
     * 地区
     */
    private String area;

    /**
     * 项目
     */
    private String project;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}