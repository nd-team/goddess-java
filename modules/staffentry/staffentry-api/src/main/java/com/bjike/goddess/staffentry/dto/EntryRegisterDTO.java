package com.bjike.goddess.staffentry.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 入职登记传输对象
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-03-09 11:10]
 * @Description: [入职登记传输对象]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class EntryRegisterDTO extends BaseDTO {
    /**
     * 姓名
     */
    private String username;

    /**
     * 员工编号
     */
    private String empNumber;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(String empNumber) {
        this.empNumber = empNumber;
    }
}
