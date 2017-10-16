package com.bjike.goddess.financeinit.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 核算部门
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:43 ]
 * @Description: [ 核算部门 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AccountDepartmentTO extends BaseTO {

    /**
     * 核算部门
     */
    private String accountDepartment;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 职员
     */
    private String staff;


    public String getAccountDepartment() {
        return accountDepartment;
    }

    public void setAccountDepartment(String accountDepartment) {
        this.accountDepartment = accountDepartment;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }
}