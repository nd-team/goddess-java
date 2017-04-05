package com.bjike.goddess.contacts.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * QQ群管理
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:51 ]
 * @Description: [ QQ群管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "contacts_qq_group")
public class QQGroup extends BaseEntity {

    /**
     * Q群号
     */
    @Column(name = "number", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT 'Q群号'")
    private String number;

    /**
     * Q群名称
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT 'Q群名称'")
    private String name;

    /**
     * Q群对象
     */
    @Column(name = "object", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT 'Q群对象'")
    private String object;

    /**
     * Q群管理人
     */
    @Column(name = "manager", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT 'Q群管理人'")
    private String manager;

    /**
     * Q群状态
     */
    @Column(name = "is_status", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT 'Q群状态'", insertable = false)
    private Boolean status;


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public Boolean isStatus() {
        return status;
    }

    public void isStatus(Boolean status) {
        this.status = status;
    }
}