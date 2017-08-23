package com.bjike.goddess.contacts.vo;

/**
 * QQ群管理表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:51 ]
 * @Description: [ QQ群管理表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class QQGroupVO {

    /**
     * id
     */
    private String id;
    /**
     * Q群号
     */
    private String number;

    /**
     * Q群名称
     */
    private String name;

    /**
     * Q群对象
     */
    private String object;

    /**
     * Q群管理人
     */
    private String manager;

    /**
     * Q群状态
     */
    private Boolean status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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