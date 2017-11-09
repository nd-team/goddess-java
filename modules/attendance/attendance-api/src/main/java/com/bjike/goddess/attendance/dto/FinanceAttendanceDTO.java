package com.bjike.goddess.attendance.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 财务出勤表数据传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-16 04:09 ]
 * @Description: [ 财务出勤表数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FinanceAttendanceDTO extends BaseDTO {
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 姓名
     */
    private String[] names;

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

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }
}