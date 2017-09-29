package com.bjike.goddess.dispatchcar.bo;

import java.util.List;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-27 17:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DepartmentCollectBO {
    /**
     * 部门
     */
    private String deparment;

    /**
     * 基础子集
     */
    private DispatchcarRecordCollectBO dispatchcarRecordCollect;

    public String getDeparment() {
        return deparment;
    }

    public void setDeparment(String deparment) {
        this.deparment = deparment;
    }

    public DispatchcarRecordCollectBO getDispatchcarRecordCollect() {
        return dispatchcarRecordCollect;
    }

    public void setDispatchcarRecordCollect(DispatchcarRecordCollectBO dispatchcarRecordCollect) {
        this.dispatchcarRecordCollect = dispatchcarRecordCollect;
    }
}
