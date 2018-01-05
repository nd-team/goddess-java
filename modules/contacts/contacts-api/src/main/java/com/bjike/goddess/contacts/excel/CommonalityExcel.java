package com.bjike.goddess.contacts.excel;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.contacts.enums.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 公共邮箱管理
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:45 ]
 * @Description: [ 公共邮箱管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CommonalityExcel extends BaseEntity {

    /**
     * 项目组/部门ID
     */
    @ExcelHeader(name = "项目组/部门ID" ,notNull = true)
    private String departmentId;

    /**
     * 邮箱地址
     */
    @ExcelHeader(name = "邮箱地址" ,notNull = true)
    private String email;

    /**
     * 状态
     */
    @ExcelHeader(name = "状态" ,notNull = true)
    private Status status;


    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}