package com.bjike.goddess.salarymanage.vo;

import com.bjike.goddess.salarymanage.bo.SalaryManageCollectBO;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-19 09:47]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ManageDepartmentVO {
    /**
     * 部门
     */
    private String department;

    /**
     * 基础信息
     */
    private SalaryManageCollectVO salaryManageCollectVO;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }


    public SalaryManageCollectVO getSalaryManageCollectVO() {
        return salaryManageCollectVO;
    }

    public void setSalaryManageCollectVO(SalaryManageCollectVO salaryManageCollectVO) {
        this.salaryManageCollectVO = salaryManageCollectVO;
    }
}
