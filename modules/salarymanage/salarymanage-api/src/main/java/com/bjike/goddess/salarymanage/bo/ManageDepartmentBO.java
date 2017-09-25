package com.bjike.goddess.salarymanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.Set;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-19 09:47]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ManageDepartmentBO extends BaseBO{
    /**
     * 部门
     */
    private String department;

    /**
     * 基础信息
     */
    private SalaryManageCollectBO salaryManageCollectBO;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }


    public SalaryManageCollectBO getSalaryManageCollectBO() {
        return salaryManageCollectBO;
    }

    public void setSalaryManageCollectBO(SalaryManageCollectBO salaryManageCollectBO) {
        this.salaryManageCollectBO = salaryManageCollectBO;
    }
}
