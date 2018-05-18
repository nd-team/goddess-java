package com.bjike.goddess.attendance.to;

import com.bjike.goddess.attendance.enums.AduitStatus;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 财务出勤表
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-16 04:09 ]
 * @Description: [ 财务出勤表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FinanceAttendanceTO extends BaseTO {
    public interface ADUIT {
    }

    /**
     * 申请核对原因
     */
    @NotBlank(groups = EDIT.class, message = "申请核对原因不能为空")
    private String reason;

    /**
     * 修改后的请假天数
     */
    private Double vacateDayNew;

    /**
     * 修改后的旷工天数
     */
    private Double absenteeismDayNew;

    /**
     * 修改后的任务完成天数
     */
    private Double finishDayNew;

    /**
     * 修改后的当天考勤天数
     */
    private Double attendanceDayNew;

    /**
     * 修改后的实际出勤天数
     */
    private Double actualDayNew;

    /**
     * 负责人
     */
    @NotBlank(groups = EDIT.class, message = "负责人不能为空")
    private String principal;

    /**
     * 审核意见
     */
    @NotNull(groups = FinanceAttendanceTO.ADUIT.class, message = "负责人审核意见不能为空")
    private AduitStatus aduitStatus;

    /**
     * 审核理由
     */
    @NotBlank(groups = FinanceAttendanceTO.ADUIT.class, message = "负责人审核理由不能为空")
    private String aduitReason;

    /**
     * 项目经理
     */
    @NotBlank(groups = EDIT.class, message = "项目经理不能为空")
    private String manager;

    public String getAduitReason() {
        return aduitReason;
    }

    public void setAduitReason(String aduitReason) {
        this.aduitReason = aduitReason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Double getVacateDayNew() {
        return vacateDayNew;
    }

    public void setVacateDayNew(Double vacateDayNew) {
        this.vacateDayNew = vacateDayNew;
    }

    public Double getAbsenteeismDayNew() {
        return absenteeismDayNew;
    }

    public void setAbsenteeismDayNew(Double absenteeismDayNew) {
        this.absenteeismDayNew = absenteeismDayNew;
    }

    public Double getFinishDayNew() {
        return finishDayNew;
    }

    public void setFinishDayNew(Double finishDayNew) {
        this.finishDayNew = finishDayNew;
    }

    public Double getAttendanceDayNew() {
        return attendanceDayNew;
    }

    public void setAttendanceDayNew(Double attendanceDayNew) {
        this.attendanceDayNew = attendanceDayNew;
    }

    public Double getActualDayNew() {
        return actualDayNew;
    }

    public void setActualDayNew(Double actualDayNew) {
        this.actualDayNew = actualDayNew;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public AduitStatus getAduitStatus() {
        return aduitStatus;
    }

    public void setAduitStatus(AduitStatus aduitStatus) {
        this.aduitStatus = aduitStatus;
    }
}