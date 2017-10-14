package com.bjike.goddess.attendance.bo.overtime;

import com.bjike.goddess.attendance.enums.AuditStatus;
import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 剩余加班业务传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-10-10 10:32 ]
 * @Description: [ 剩余加班业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OverWorkRestDayBO extends BaseBO {

    /**
     * 员工编号
     */
    private String empNum;
    /**
     * 员工名
     */
    private String empName;

    /**
     * 剩余加班天数
     */
    private String restDay;

    /**
     * 核算截至时间
     */
    private String checkEndTime;


    public String getEmpNum() {
        return empNum;
    }

    public void setEmpNum(String empNum) {
        this.empNum = empNum;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getRestDay() {
        return restDay;
    }

    public void setRestDay(String restDay) {
        this.restDay = restDay;
    }

    public String getCheckEndTime() {
        return checkEndTime;
    }

    public void setCheckEndTime(String checkEndTime) {
        this.checkEndTime = checkEndTime;
    }
}