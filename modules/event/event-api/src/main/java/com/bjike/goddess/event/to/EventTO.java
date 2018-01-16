package com.bjike.goddess.event.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.event.enums.EventStatus;
import com.bjike.goddess.event.enums.EventTime;
import com.bjike.goddess.event.enums.Permissions;
import com.bjike.goddess.event.enums.Status;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 事件
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-09 03:58 ]
 * @Description: [ 事件 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class EventTO extends BaseTO {

    /**
     * 模块名称中文名
     */
    @NotBlank(groups = ADD.class, message = "模块名称中文名不能为空")
    private String projectChineseName;
    /**
     * 模块名称英文名
     */
    @NotBlank(groups = ADD.class, message = "模块名称英文名不能为空")
    private String projectEnglishName;
    /**
     * 功能名称中文名
     */
    @NotBlank(groups = ADD.class, message = "功能名称中文名不能为空")
    private String functionChineseName;
    /**
     * 功能名称英文名
     */
    @NotBlank(groups = ADD.class, message = "功能名称英文名不能为空")
    private String functionEnglishName;

    /**
     * 事件内容
     */
    @NotBlank(groups = ADD.class, message = "事件内容不能为空")
    private String content;

    /**
     * 要求处理时间
     */
    private String requestTime;

    /**
     * 处理人
     */
    @NotBlank(groups = ADD.class, message = "处理人不能为空")
    private String name;

    /**
     * 权限
     */
    @NotNull(groups = ADD.class, message = "权限不能为空")
    private Permissions permissions;

    /**
     * 优先级
     */
    private Integer level;

    /**
     * 实际处理完成时间
     */
    @NotBlank(groups = EDIT.class, message = "实际处理完成时间不能为空")
    private String actualTime;

    /**
     * 待办事件id
     */
    @NotBlank(groups = ADD.class, message = "待办事件id不能为空")
    private String eventId;

    /**
     * 事件处理状态
     */
    @NotNull(groups = EDIT.class, message = "事件处理状态不能为空")
    private EventStatus eventStatus;

    /**
     * 待办事件对应状态
     */
    @NotNull(groups = ADD.class, message = "待办事件对应状态不能为空")
    private String status;

    /**
     * 2017-12-28新增字段
     *
     * 任务开始时间
     */
    private String startTime;
    /**
     * 2017-12-28新增字段
     * 任务结束时间
     */
    private String endTime;

    /**
     * 2017-12-28 新增字段
     *  创建日期
     */
    private String startDate;

    /**
     * 状态 是否重复
     *2017-12-29 新增字段
     */
    private Status eStatus;

    /**
     * 提前多少分钟提醒
     *2017-12-29 新增字段
     * MINUTES_15 提前15分钟
     * MINUTES_30 提前30分钟
     * MINUTES_60 提前60分钟
     *
     */
    private EventTime eventTime;

    public Status geteStatus() {
        return eStatus;
    }

    public void seteStatus(Status eStatus) {
        this.eStatus = eStatus;
    }

    public EventTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(EventTime eventTime) {
        this.eventTime = eventTime;
    }

    public String getStartTime() { return startTime;  }

    public void setStartTime(String startTime) {  this.startTime = startTime;  }

    public String getEndTime() {   return endTime;  }

    public void setEndTime(String endTime) { this.endTime = endTime; }

    public String getStartDate() { return startDate; }

    public void setStartDate(String startDate) { this.startDate = startDate; }

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }

    public Permissions getPermissions() {
        return permissions;
    }

    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getProjectChineseName() {
        return projectChineseName;
    }

    public void setProjectChineseName(String projectChineseName) {
        this.projectChineseName = projectChineseName;
    }

    public String getProjectEnglishName() {
        return projectEnglishName;
    }

    public void setProjectEnglishName(String projectEnglishName) {
        this.projectEnglishName = projectEnglishName;
    }

    public String getFunctionChineseName() {
        return functionChineseName;
    }

    public void setFunctionChineseName(String functionChineseName) {
        this.functionChineseName = functionChineseName;
    }

    public String getFunctionEnglishName() {
        return functionEnglishName;
    }

    public void setFunctionEnglishName(String functionEnglishName) {
        this.functionEnglishName = functionEnglishName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getActualTime() {
        return actualTime;
    }

    public void setActualTime(String actualTime) {
        this.actualTime = actualTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}