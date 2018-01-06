package com.bjike.goddess.contractcommunicat.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 商务洽谈数据传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-28 11:24 ]
 * @Description: [ 商务洽谈数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BusinessNegotiationCollectDTO extends BaseDTO {

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组/部门
     */
    private String department;

    /**
     * 问题归属
     */
    private String problemBelong;

    public BusinessNegotiationCollectDTO(String businessType, String area, String department, String problemBelong) {
        this.businessType = businessType;
        this.area = area;
        this.department = department;
        this.problemBelong = problemBelong;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

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

    public String getProblemBelong() {
        return problemBelong;
    }

    public void setProblemBelong(String problemBelong) {
        this.problemBelong = problemBelong;
    }
}