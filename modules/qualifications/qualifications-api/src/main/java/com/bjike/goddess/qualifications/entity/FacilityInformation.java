package com.bjike.goddess.qualifications.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 设备信息
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 06:59 ]
 * @Description: [ 设备信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "qualifications_facility_information")
public class FacilityInformation extends BaseEntity {

    /**
     * 设备名称
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '设备名称'")
    private String name;

    /**
     * 清单
     */
    @Column(name = "fictitious", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '清单'")
    private String fictitious;

    /**
     * 真实/虚拟
     */
    @Column(name = "is_real", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '真实/虚拟'", insertable = false)
    private Boolean real;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFictitious() {
        return fictitious;
    }

    public void setFictitious(String fictitious) {
        this.fictitious = fictitious;
    }

    public Boolean getReal() {
        return real;
    }

    public void setReal(Boolean real) {
        this.real = real;
    }
}