package com.bjike.goddess.marketdevelopment.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 业务方向科目数据
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-30 03:03 ]
 * @Description: [ 业务方向科目数据 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "marketdevelopment_subjectdata")
public class SubjectData extends BaseEntity {

    /**
     * 业务方向类型id
     */
    @Column(name = "businessDataId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务方向类型id'")
    private String businessDataId;

    /**
     * 业务方向科目
     */
    @Column(name = "subject", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务方向科目'")
    private String subject;


    public String getBusinessDataId() {
        return businessDataId;
    }

    public void setBusinessDataId(String businessDataId) {
        this.businessDataId = businessDataId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}