package com.bjike.goddess.qualifications.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 审核资料
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 06:44 ]
 * @Description: [ 审核资料 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "qualifications_audit_material")
public class AuditMaterial extends BaseEntity {

    /**
     * 备案书
     */
    @Column(name = "record", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '备案书'")
    private String record;

    /**
     * 其他(附件)
     */
    @Column(name = "other", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '其他(附件)'")
    private String other;


    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}