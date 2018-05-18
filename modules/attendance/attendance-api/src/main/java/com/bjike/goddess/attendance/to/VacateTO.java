package com.bjike.goddess.attendance.to;

import com.bjike.goddess.attendance.enums.AduitStatus;
import com.bjike.goddess.attendance.enums.EndTime;
import com.bjike.goddess.attendance.enums.StartTime;
import com.bjike.goddess.attendance.enums.VacateType;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 请假管理
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-07 05:15 ]
 * @Description: [ 请假管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class VacateTO extends BaseTO {
    public interface GETTIME{}
    public interface PHONEADD{}
//    /**
//     * 员工编号
//     */
//    @NotBlank(groups = {ADD.class,VacateTO.PHONEADD.class},message = "员工编号不能为空")
//    private String employeeNumber;

    /**
     * 请假人
     */
    @NotBlank(groups = {ADD.class,VacateTO.PHONEADD.class},message = "请假人不能为空")
    private String name;
    /**
     * 地区
     */
    @NotBlank(groups = {ADD.class},message = "地区不能为空")
    private String area;
    /**
     * 项目组/部门
     */
    @NotBlank(groups = {ADD.class},message = "项目组/部门不能为空")
    private String depart;

    /**
     * 职位
     */
    @NotBlank(groups = {ADD.class},message = "职位不能为空")
    private String position;
    /**
     * 请假类型
     */
    @NotNull(groups = {ADD.class,VacateTO.PHONEADD.class},message = "请假类型不能为空")
    private VacateType vacateType;
    /**
     * 开始日期
     */
    @NotBlank(groups = {ADD.class,VacateTO.GETTIME.class,VacateTO.PHONEADD.class},message = "开始日期不能为空")
    private String startDate;
    /**
     * 结束日期
     */
    @NotBlank(groups = {ADD.class,VacateTO.GETTIME.class,VacateTO.PHONEADD.class},message = "结束日期不能为空")
    private String endDate;
    /**
     * 开始时间
     */
    @NotNull(groups = {ADD.class,VacateTO.GETTIME.class,VacateTO.PHONEADD.class},message = "开始时间不能为空")
    private StartTime startTime;

    /**
     * 结束时间
     */
    @NotNull(groups = {ADD.class,VacateTO.GETTIME.class,VacateTO.PHONEADD.class},message = "结束时间不能为空")
    private EndTime endTime;

    /**
     * 请假原因
     */
    @NotBlank(groups = {ADD.class,VacateTO.PHONEADD.class},message = "请假原因不能为空")
    private String reason;

    /**
     * 主送人
     */
    @NotNull(groups = {ADD.class,VacateTO.PHONEADD.class},message = "主送人不能为空")
    private String[] mains;

    /**
     * 抄送人
     */
    private String[] carbons;

    /**
     * 工作交接内容
     */
    private String handoff;
    /**
     * 审核意见
     */
    @NotBlank(groups = {EDIT.class},message = "审核意见不能为空")
    private String advice;

    /**
     * uuid
     */
    @NotBlank(groups = {ADD.class,VacateTO.PHONEADD.class},message = "uuid不能为空")
    private String uuid;

    /**
     * 审核状态
     */
    @NotNull(groups = {EDIT.class},message = "审核状态不能为空")
    private AduitStatus aduitStatus;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public AduitStatus getAduitStatus() {
        return aduitStatus;
    }

    public void setAduitStatus(AduitStatus aduitStatus) {
        this.aduitStatus = aduitStatus;
    }

    public String getHandoff() {
        return handoff;
    }

    public void setHandoff(String handoff) {
        this.handoff = handoff;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VacateType getVacateType() {
        return vacateType;
    }

    public void setVacateType(VacateType vacateType) {
        this.vacateType = vacateType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }


    public StartTime getStartTime() {
        return startTime;
    }

    public void setStartTime(StartTime startTime) {
        this.startTime = startTime;
    }

    public EndTime getEndTime() {
        return endTime;
    }

    public void setEndTime(EndTime endTime) {
        this.endTime = endTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String[] getMains() {
        return mains;
    }

    public void setMains(String[] mains) {
        this.mains = mains;
    }

    public String[] getCarbons() {
        return carbons;
    }

    public void setCarbons(String[] carbons) {
        this.carbons = carbons;
    }
}