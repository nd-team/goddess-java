package com.bjike.goddess.salarymanage.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 招聘面谈薪资确认记录业务接口
 *
 * @Author: [ jiangzaixuan ]
 * @Date: [ 2017-09-15 02:20 ]
 * @Description: [ 招聘面谈薪资确认记录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SalaryConfirmRecordDTO extends BaseDTO {

    /**
     * 姓名
     */
    private String userName;

    /**
     * 员工编号
     */
    private String  employeeID;



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }
}