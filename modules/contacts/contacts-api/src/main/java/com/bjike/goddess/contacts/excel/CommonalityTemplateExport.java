package com.bjike.goddess.contacts.excel;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.contacts.enums.Status;


/**
 * 公共邮箱管理
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:45 ]
 * @Description: [ 公共邮箱管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CommonalityTemplateExport extends BaseEntity {

    /**
     * 项目组/部门
     */
    @ExcelHeader(name = "项目组/部门" ,notNull = true)
    private String department;

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


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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