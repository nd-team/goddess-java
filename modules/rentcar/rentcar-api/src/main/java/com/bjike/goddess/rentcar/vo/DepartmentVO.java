package com.bjike.goddess.rentcar.vo;

import java.util.List;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-10-10 18:16]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DepartmentVO {
    /**
     * 部门
     */
    private String department;

    /**
     * 基础信息
     */
    private CollectDriverInfoVO collectDriverInfo;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public CollectDriverInfoVO getCollectDriverInfo() {
        return collectDriverInfo;
    }

    public void setCollectDriverInfo(CollectDriverInfoVO collectDriverInfo) {
        this.collectDriverInfo = collectDriverInfo;
    }
}
