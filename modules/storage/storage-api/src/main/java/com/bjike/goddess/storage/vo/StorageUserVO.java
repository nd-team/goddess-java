package com.bjike.goddess.storage.vo;

import com.bjike.goddess.common.api.type.Status;

/**
 * 存储模块用户表现层对象
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-03-30 09:39 ]
 * @Description: [ 存储模块用户表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class StorageUserVO {

    /**
     * id
     */
    private String id;
    /**
     * 模块名
     */
    private String moduleName;

    /**
     * 登录账号
     */
    private String account;

    /**
     * 状态
     */
    private Status status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}