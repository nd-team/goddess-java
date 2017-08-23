package com.bjike.goddess.projectprocing.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 项目验收情况数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-31 03:54 ]
 * @Description: [ 项目验收情况数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectAcceptanceDTO extends BaseDTO {

    /**
     * 地区
     */
    private String area;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}