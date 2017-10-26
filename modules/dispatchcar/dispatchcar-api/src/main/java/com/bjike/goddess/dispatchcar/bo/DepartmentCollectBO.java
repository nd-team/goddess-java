package com.bjike.goddess.dispatchcar.bo;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-27 17:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DepartmentCollectBO implements Serializable{
    /**
     * 部门
     */
    private String department;

    /**
     * 基础子集
     */
    private DispatchcarRecordCollectBO dispatchcarRecordCollect;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public DispatchcarRecordCollectBO getDispatchcarRecordCollect() {
        return dispatchcarRecordCollect;
    }

    public void setDispatchcarRecordCollect(DispatchcarRecordCollectBO dispatchcarRecordCollect) {
        this.dispatchcarRecordCollect = dispatchcarRecordCollect;
    }
}
