package com.bjike.goddess.secure.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * @Author: [chenjunhao]
 * @Date: [2017-05-04 18:47]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DismissionEmployeeBO extends BaseBO {
    /**
     * 离职员工姓名
     */
    private String name;
    /**
     * 离职员工id
     */
    private String dimissionId;
    /**
     * 离职时间
     */
    private String endTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDimissionId() {
        return dimissionId;
    }

    public void setDimissionId(String dimissionId) {
        this.dimissionId = dimissionId;
    }
}
