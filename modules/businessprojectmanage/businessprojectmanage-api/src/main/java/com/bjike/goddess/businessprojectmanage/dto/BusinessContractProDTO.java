package com.bjike.goddess.businessprojectmanage.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 项目合同基本信息数据传输对象
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2017-12-11 10:03 ]
 * @Description: [ 项目合同基本信息数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BusinessContractProDTO extends BaseDTO {


    /**
     * 　地区
     */
    private String area;

    /**
     * 所属项目组
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