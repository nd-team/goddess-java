package com.bjike.goddess.businesscommission.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 业务提成分配比例表数据传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-23 11:29 ]
 * @Description: [ 业务提成分配比例表数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProportionDTO extends BaseDTO {
    /**
     * 地区
     */
    private String area;

    /**
     * 部门/项目组
     */
    private String department;

    /**
     * 内部项目名称
     */
    private String projectName;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}