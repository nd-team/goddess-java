package com.bjike.goddess.secure.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 减员名单数据传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-24 09:48 ]
 * @Description: [ 减员名单数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RemoveEmployeeDTO extends BaseDTO {

    /**
     * 减员的人员姓名
     */
    private String removeName;
    /**
     * 员工编号
     */
    private String employeeId;

    public String getRemoveName() {
        return removeName;
    }

    public void setRemoveName(String removeName) {
        this.removeName = removeName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}