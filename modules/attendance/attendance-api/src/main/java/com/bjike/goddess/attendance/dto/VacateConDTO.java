package com.bjike.goddess.attendance.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 请假管理数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-10-07 05:15 ]
 * @Description: [ 请假管理数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class VacateConDTO extends BaseDTO {

    /**
     * 请假人
     */
    private String empName;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}