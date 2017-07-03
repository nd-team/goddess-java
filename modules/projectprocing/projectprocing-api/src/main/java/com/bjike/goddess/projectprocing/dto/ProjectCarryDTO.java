package com.bjike.goddess.projectprocing.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 项目实施数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-31 02:33 ]
 * @Description: [ 项目实施数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectCarryDTO extends BaseDTO {

    /**
     * 合同签订情况
     */
    private String signCondition;

    /**
     * 立项情况
     */
    private String signProject;


    /**
     * 地区
     */
    private String area;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 业务方向科目
     */
    private String businessSubject;


    public String getSignCondition() {
        return signCondition;
    }

    public void setSignCondition(String signCondition) {
        this.signCondition = signCondition;
    }

    public String getSignProject() {
        return signProject;
    }

    public void setSignProject(String signProject) {
        this.signProject = signProject;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBusinessSubject() {
        return businessSubject;
    }

    public void setBusinessSubject(String businessSubject) {
        this.businessSubject = businessSubject;
    }
}