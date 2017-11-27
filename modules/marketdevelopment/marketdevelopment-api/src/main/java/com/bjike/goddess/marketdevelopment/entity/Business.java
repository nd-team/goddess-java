package com.bjike.goddess.marketdevelopment.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 业务方向分类
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-18 02:56 ]
 * @Description: [ 业务方向分类 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "marketdevelopment_business")
public class Business extends BaseEntity {

    /**
     * 业务方向编号
     */
    @Column(name = "businessNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务方向编号'")
    private String businessNum;

    /**
     * 业务方向分类
     */
    @Column(name = "businessType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务方向分类'")
    private String businessType;


    public String getBusinessNum() {
        return businessNum;
    }

    public void setBusinessNum(String businessNum) {
        this.businessNum = businessNum;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }
}