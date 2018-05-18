package com.bjike.goddess.task.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.task.enums.ExecStatus;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-19 13:53]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class TaskTO extends BaseTO {
    /**
     * 执行人
     */
    @NotNull(message = "执行人不能为空", groups = {ADD.class, EDIT.class})
    private String[] execUsers;
    /**
     * 任务量
     */
    @NotBlank(message = "任务量不能为空", groups = {ADD.class, EDIT.class})
    private Integer num;
    /**
     * 所需时长分钟
     */
    @NotNull(message = "所需时长不能为空", groups = {ADD.class, EDIT.class})
    private Integer minute;
    /**
     * 任务开始时间
     */
    @NotBlank(message = "任务开始时间不能为空", groups = {ADD.class, EDIT.class})

    private String startTime;
    /**
     * 任务结束时间
     */
    @NotBlank(message = "任务结束时间不能为空", groups = {ADD.class, EDIT.class})

    private String endTime;

    /**
     * 所属任务行
     */
    @NotBlank(message = "所属任务行不能为空", groups = {ADD.class, EDIT.class})
    private String rowId;
    /**
     * 所属表节点
     */
    @NotBlank(message = "所属表节点不能为空", groups = {ADD.class, EDIT.class})
    private String node;

    /**
     * 任务备注
     */
    private String remark;

    /**
     * 任务拆分天数
     */
    private Integer spiltDay;

    public String[] getExecUsers() {
        return execUsers;
    }

    public void setExecUsers(String[] execUsers) {
        this.execUsers = execUsers;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

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

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSpiltDay() {
        return spiltDay;
    }

    public void setSpiltDay(Integer spiltDay) {
        this.spiltDay = spiltDay;
    }
}
