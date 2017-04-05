package com.bjike.goddess.projectprocing.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 项目结算跟进数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-31 03:49 ]
 * @Description: [ 项目结算跟进数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectSettlementFollowDTO extends BaseDTO {

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

    /**
     * 运营商名称
     */
    private String operatorName;



    /**
     * 外包单位
     */
    private String outsourcingUnit;


    /**
     * 内部项目名称
     */
    private String innerName;

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

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOutsourcingUnit() {
        return outsourcingUnit;
    }

    public void setOutsourcingUnit(String outsourcingUnit) {
        this.outsourcingUnit = outsourcingUnit;
    }

    public String getInnerName() {
        return innerName;
    }

    public void setInnerName(String innerName) {
        this.innerName = innerName;
    }
}