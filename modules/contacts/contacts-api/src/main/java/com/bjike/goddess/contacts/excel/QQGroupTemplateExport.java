package com.bjike.goddess.contacts.excel;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

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
public class QQGroupTemplateExport extends BaseEntity {

    /**
     * Q群号
     */
    @ExcelHeader(name = "Q群号" , notNull = true)
    private String number;

    /**
     * Q群名称
     */
    @ExcelHeader(name = "Q群名称" , notNull = true)
    private String name;

    /**
     * Q群对象
     */
    @ExcelHeader(name = "Q群对象" , notNull = true)
    private String object;

    /**
     * Q群管理人
     */
    @ExcelHeader(name = "Q群管理人" , notNull = true)
    private String manager;

    /**
     * Q群状态
     */
    @ExcelHeader(name = "Q群状态" , notNull = true)
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