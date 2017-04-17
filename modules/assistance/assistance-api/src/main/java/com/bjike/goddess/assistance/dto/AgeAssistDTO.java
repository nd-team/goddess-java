package com.bjike.goddess.assistance.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 工龄补助数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:59 ]
 * @Description: [ 工龄补助数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AgeAssistDTO extends BaseDTO {

    /**
     * 地区
     */
    private String area;

    /**
     * 姓名
     */
    private String empName;


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

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }
}