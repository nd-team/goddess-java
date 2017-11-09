package com.bjike.goddess.attendance.bo;

import com.bjike.goddess.attendance.enums.CountFrequency;
import com.bjike.goddess.attendance.enums.RemindFrequency;
import com.bjike.goddess.attendance.enums.TotalType;
import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 考勤情况汇总设置业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-19 06:18 ]
 * @Description: [ 考勤情况汇总设置业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CaseCountSetBO extends BaseBO {

    /**
     * 汇总表名称
     */
    private String name;

    /**
     * 创建人
     */
    private String creater;

    /**
     * 项目组
     */
    private String depart;

    /**
     * 设置发送时间
     */
    private String sendTime;

    /**
     * 上次发送时间
     */
    private String lastTime;

    /**
     * 统计类型
     */
    private TotalType totalType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 通报对象
     */
    private String sendObject;

    /**
     * 汇总对象
     */
    private String collectObject;

    /**
     * 是否发送至本部门全部人
     */
    private Boolean all;

    /**
     * 提醒频率
     */
    private RemindFrequency remindFrequency;

    /**
     * 提醒间隔值
     */
    private Integer remindVal;

    /**
     * 汇总频率
     */
    private CountFrequency countFrequency;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public TotalType getTotalType() {
        return totalType;
    }

    public void setTotalType(TotalType totalType) {
        this.totalType = totalType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSendObject() {
        return sendObject;
    }

    public void setSendObject(String sendObject) {
        this.sendObject = sendObject;
    }

    public String getCollectObject() {
        return collectObject;
    }

    public void setCollectObject(String collectObject) {
        this.collectObject = collectObject;
    }

    public Boolean getAll() {
        return all;
    }

    public void setAll(Boolean all) {
        this.all = all;
    }

    public RemindFrequency getRemindFrequency() {
        return remindFrequency;
    }

    public void setRemindFrequency(RemindFrequency remindFrequency) {
        this.remindFrequency = remindFrequency;
    }

    public Integer getRemindVal() {
        return remindVal;
    }

    public void setRemindVal(Integer remindVal) {
        this.remindVal = remindVal;
    }

    public CountFrequency getCountFrequency() {
        return countFrequency;
    }

    public void setCountFrequency(CountFrequency countFrequency) {
        this.countFrequency = countFrequency;
    }
}