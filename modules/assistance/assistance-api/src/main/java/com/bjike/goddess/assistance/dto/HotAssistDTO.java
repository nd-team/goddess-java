package com.bjike.goddess.assistance.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 高温补助数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:52 ]
 * @Description: [ 高温补助数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class HotAssistDTO extends BaseDTO {

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组
     */
    private String projectGroup;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }
}