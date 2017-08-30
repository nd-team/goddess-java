package com.bjike.goddess.archive.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 对外人员基本信息设置
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 02:28 ]
 * @Description: [ 对外人员基本信息设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "archive_foreign_staffing_set")
public class ForeignStaffingSet extends BaseEntity {

    /**
     * 类型
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '类型'")
    private String name;

    /**
     * 描述
     */
    @Column(name = "description",  columnDefinition = "VARCHAR(255)   COMMENT '描述'")
    private String description;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2)  DEFAULT 0  COMMENT '状态'", insertable = false)
    private Status status;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}