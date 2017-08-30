package com.bjike.goddess.intromanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 客户及合作伙伴
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 05:56 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "intromanage_customerandpartner")
public class CustomerAndPartner extends BaseEntity {

    /**
     * 公司记录id
     */
    @Column(name = "firmId", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '公司名称'")
    private String firmId;

    /**
     * 运营商
     */
    @Column(name = "operators", columnDefinition = "VARCHAR(255) COMMENT '运营商'")
    private String operators;

    /**
     * 厂家
     */
    @Column(name = "manufacturer", columnDefinition = "VARCHAR(255) COMMENT '厂家'")
    private String manufacturer;

    /**
     * 各政府单位
     */
    @Column(name = "governmentUnit", columnDefinition = "VARCHAR(255) COMMENT '各政府单位'")
    private String governmentUnit;

    /**
     * 合作伙伴
     */
    @Column(name = "partner", columnDefinition = "VARCHAR(255) COMMENT '合作伙伴'")
    private String partner;

    public String getFirmId() {
        return firmId;
    }

    public void setFirmId(String firmId) {
        this.firmId = firmId;
    }

    public String getOperators() {
        return operators;
    }

    public void setOperators(String operators) {
        this.operators = operators;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getGovernmentUnit() {
        return governmentUnit;
    }

    public void setGovernmentUnit(String governmentUnit) {
        this.governmentUnit = governmentUnit;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }
}