package com.bjike.goddess.managepromotion.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.managepromotion.enums.AuditStatus;

/**
 * 员工已晋升情况数据传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-23 09:20 ]
 * @Description: [ 员工已晋升情况数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class EmployeePromotedDTO extends BaseDTO {
    /**
     * 姓名
     */
    private String name;
    /**
     * 时间
     */
    private String times;
    /**
     * 状态
     */
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}