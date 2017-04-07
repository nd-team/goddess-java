package com.bjike.goddess.qualifications.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 财务资料
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 06:42 ]
 * @Description: [ 财务资料 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "qualifications_finance_info")
public class FinanceInfo extends BaseEntity {

    /**
     * 财务报表
     */
    @Column(name = "reporting", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '财务报表'")
    private String reporting;

    /**
     * 审核资料
     */
    @Column(name = "material", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '审核资料'")
    private String material;


    public String getReporting() {
        return reporting;
    }

    public void setReporting(String reporting) {
        this.reporting = reporting;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}