package com.bjike.goddess.storage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 存储模块用户
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-03-30 09:39 ]
 * @Description: [ 存储模块用户 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "storage_user")
public class StorageUser extends BaseEntity {
    /**
     * 系统编号
     */
    @Column(nullable = false, columnDefinition = "VARCHAR(12) COMMENT '系统编号' ")
    private String systemNO;
    /**
     * 模块名
     */
    @Column( nullable = false, unique = true, columnDefinition = "VARCHAR(255)   COMMENT '模块名'")
    private String moduleName;

    /**
     * 登录账号
     */
    @Column( nullable = false, unique = true, columnDefinition = "VARCHAR(36)   COMMENT '登录账号'")
    private String account;

    /**
     * 密码
     */
    @Column( nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '密码'")
    private String password;

    /**
     * 状态
     */
    @Column(columnDefinition = "TINYINT(2) DEFAULT 0 COMMENT '状态'", nullable = false, insertable = false)
    private Status status;

    public String getSystemNO() {
        return systemNO;
    }

    public void setSystemNO(String systemNO) {
        this.systemNO = systemNO;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}