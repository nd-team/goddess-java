package com.bjike.goddess.attendance.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 考勤情况汇总第一层子表
 *
 * @Author: [chenjunhao]
 * @Date: [2017-10-14 16:45]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CaseABO extends BaseBO {
    /**
     * 部门
     */
    private String depart;
    /**
     * 正常出勤人员
     */
    private Long normalAttendance;
    /**
     * 迟到出勤人员
     */
    private Long lateAttendance;
    /**
     * 有请假出勤人员
     */
    private Long vacateAttendance;
    /**
     * 旷工情况
     */
    private Long absenteeism;
    /**
     * 正常休息情况
     */
    private Long normalRest;
    /**
     * 加班情况
     */
    private Long outWork;
    /**
     * 法定节假日休息情况
     */
    private Long festival;

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public Long getNormalAttendance() {
        return normalAttendance;
    }

    public void setNormalAttendance(Long normalAttendance) {
        this.normalAttendance = normalAttendance;
    }

    public Long getLateAttendance() {
        return lateAttendance;
    }

    public void setLateAttendance(Long lateAttendance) {
        this.lateAttendance = lateAttendance;
    }

    public Long getVacateAttendance() {
        return vacateAttendance;
    }

    public void setVacateAttendance(Long vacateAttendance) {
        this.vacateAttendance = vacateAttendance;
    }

    public Long getAbsenteeism() {
        return absenteeism;
    }

    public void setAbsenteeism(Long absenteeism) {
        this.absenteeism = absenteeism;
    }

    public Long getNormalRest() {
        return normalRest;
    }

    public void setNormalRest(Long normalRest) {
        this.normalRest = normalRest;
    }

    public Long getOutWork() {
        return outWork;
    }

    public void setOutWork(Long outWork) {
        this.outWork = outWork;
    }

    public Long getFestival() {
        return festival;
    }

    public void setFestival(Long festival) {
        this.festival = festival;
    }
}
