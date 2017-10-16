package com.bjike.goddess.rentcar.bo;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-10-10 18:14]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DepartmentBO implements Serializable{
    /**
     * 部门
     */
    private String department;

    /**
     * 基础字段
     */
    private CollectDriverInfoBO collectDriverInfo;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public CollectDriverInfoBO getCollectDriverInfo() {
        return collectDriverInfo;
    }

    public void setCollectDriverInfo(CollectDriverInfoBO collectDriverInfo) {
        this.collectDriverInfo = collectDriverInfo;
    }
}
