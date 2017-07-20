package com.bjike.goddess.costdetail.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 成本明细数据传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 05:45 ]
 * @Description: [ 成本明细数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CostDetailsDTO extends BaseDTO {
    /**
     * 日期
     */
    private String costTime;
    /**
     * 部门
     */
    private String department;

    public String getCostTime() {
        return costTime;
    }

    public void setCostTime(String costTime) {
        this.costTime = costTime;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}