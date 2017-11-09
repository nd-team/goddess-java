package com.bjike.goddess.attendance.to;

import com.bjike.goddess.attendance.enums.CountFrequency;
import com.bjike.goddess.attendance.enums.RemindFrequency;
import com.bjike.goddess.attendance.enums.TotalType;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 日报汇总设置
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-11-03 04:20 ]
 * @Description: [ 日报汇总设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DayCountSetTO extends BaseTO {

    /**
     * 汇总表名称
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "汇总表名称不能为空")
    private String name;

    /**
     * 项目组
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "项目组不能为空")
    private String depart;

    /**
     * 设置发送时间
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "设置发送时间不能为空")
    private String sendTime;

    /**
     * 统计类型
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "统计类型不能为空")
    private TotalType totalType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 通报对象
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "通报对象不能为空")
    private String[] sendObjects;

    /**
     * 汇总对象
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "汇总对象不能为空")
    private String[] collectObjects;

    /**
     * 是否发送至本部门全部人
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否发送至本部门全部人不能为空")
    private Boolean all;

    /**
     * 提醒频率
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "提醒频率不能为空")
    private RemindFrequency remindFrequency;

    /**
     * 提醒间隔值
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "提醒间隔值不能为空")
    private Integer remindVal;

    /**
     * 汇总频率
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "汇总频率不能为空")
    private CountFrequency countFrequency;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String[] getSendObjects() {
        return sendObjects;
    }

    public void setSendObjects(String[] sendObjects) {
        this.sendObjects = sendObjects;
    }

    public String[] getCollectObjects() {
        return collectObjects;
    }

    public void setCollectObjects(String[] collectObjects) {
        this.collectObjects = collectObjects;
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