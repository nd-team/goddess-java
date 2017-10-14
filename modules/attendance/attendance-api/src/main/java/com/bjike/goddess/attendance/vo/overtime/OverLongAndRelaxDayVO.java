package com.bjike.goddess.attendance.vo.overtime;


/**
 * 加班表现层对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-10-10 10:32 ]
 * @Description: [ 加班表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OverLongAndRelaxDayVO {


    /**
     * 可休天数
     */
    private Double overLong;

    /**
     * 加班时长
     */
    private Double relaxDay;


    public Double getOverLong() {
        return overLong;
    }

    public void setOverLong(Double overLong) {
        this.overLong = overLong;
    }

    public Double getRelaxDay() {
        return relaxDay;
    }

    public void setRelaxDay(Double relaxDay) {
        this.relaxDay = relaxDay;
    }
}