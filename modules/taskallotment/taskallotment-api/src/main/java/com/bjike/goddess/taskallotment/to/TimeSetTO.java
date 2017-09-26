package com.bjike.goddess.taskallotment.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.taskallotment.enums.ForObject;
import com.bjike.goddess.taskallotment.enums.Spacing;
import com.bjike.goddess.taskallotment.enums.StandardType;
import com.bjike.goddess.taskallotment.enums.Status;

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
    private String area;

    /**
     * 项目组/部门
     */
    private String depart;
    /**
     * 用于对象
     */
    private ForObject forObject;
    /**
     * 标准类型
     */
    private StandardType standardType;
    /**
     * 标准工时
     */
    private Double hour;
    /**
     * 是否提醒本部门所有人
     */
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
    private String remindTime;

    /**
     * 提醒对象
     */
    private String remindObject;
    /**
     * 状态
     */
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


    public String getRemindObject() {
        return remindObject;
    }

    public void setRemindObject(String remindObject) {
        this.remindObject = remindObject;
    }

}