package com.bjike.goddess.taskallotment.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.taskallotment.enums.ForObject;
import com.bjike.goddess.taskallotment.enums.Spacing;
import com.bjike.goddess.taskallotment.enums.StandardType;
import com.bjike.goddess.taskallotment.enums.Status;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 标准工时设置
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-15 11:23 ]
 * @Description: [ 标准工时设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TimeSetTO extends BaseTO {

    /**
     * 地区
     */
    @NotBlank(groups = {ADD.class, EDIT.class},message = "地区不能为空")
    private String area;

    /**
     * 项目组/部门
     */
    @NotBlank(groups = {ADD.class, EDIT.class},message = "项目组/部门不能为空")
    private String depart;
    /**
     * 用于对象
     */
    @NotNull(groups = {ADD.class, EDIT.class},message = "用于对象不能为空")
    private ForObject forObject;
    /**
     * 标准类型
     */
    @NotNull(groups = {ADD.class, EDIT.class},message = "标准类型不能为空")
    private StandardType standardType;
    /**
     * 标准工时
     */
    @DecimalMin(value = "0.00",groups = {ADD.class, EDIT.class},message = "标准工时不能为空")
    @NotNull(groups = {ADD.class, EDIT.class},message = "标准工时不能为空")
    private Double hour;
    /**
     * 是否提醒本部门所有人
     */
    @NotNull(groups = {ADD.class, EDIT.class},message = "是否提醒本部门所有人不能为空")
    private Boolean sendAll;

    /**
     * 提醒频率
     */
    private Integer remind;
    /**
     * 提醒间隔
     */
    private Spacing spacing;

    /**
     * 开始提醒时间
     */
    @NotBlank(groups = {ADD.class, EDIT.class},message = "开始提醒时间不能为空")
    private String remindTime;

    /**
     * 提醒对象
     */
    @NotNull(groups = {ADD.class, EDIT.class},message = "提醒对象不能为空")
    private String[] remindObjects;
    /**
     * 状态
     */
    @NotNull(groups = {ADD.class, EDIT.class},message = "状态不能为空")
    private Status status;

    public ForObject getForObject() {
        return forObject;
    }

    public void setForObject(ForObject forObject) {
        this.forObject = forObject;
    }

    public StandardType getStandardType() {
        return standardType;
    }

    public void setStandardType(StandardType standardType) {
        this.standardType = standardType;
    }

    public Spacing getSpacing() {
        return spacing;
    }

    public void setSpacing(Spacing spacing) {
        this.spacing = spacing;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Boolean getSendAll() {
        return sendAll;
    }

    public void setSendAll(Boolean sendAll) {
        this.sendAll = sendAll;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public Double getHour() {
        return hour;
    }

    public void setHour(Double hour) {
        this.hour = hour;
    }

    public Integer getRemind() {
        return remind;
    }

    public void setRemind(Integer remind) {
        this.remind = remind;
    }

    public String getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(String remindTime) {
        this.remindTime = remindTime;
    }

    public String[] getRemindObjects() {
        return remindObjects;
    }

    public void setRemindObjects(String[] remindObjects) {
        this.remindObjects = remindObjects;
    }
}