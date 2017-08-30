package com.bjike.goddess.event.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.event.enums.EventStatus;
import com.bjike.goddess.event.enums.Permissions;
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
     * 项目名称
     */
    @NotBlank(groups = ADD.class,message = "项目名称不能为空")
    private String project;

    /**
     * 事件内容
     */
    @NotBlank(groups = ADD.class,message = "事件内容不能为空")
    private String content;

    /**
     * 要求处理时间
     */
    @NotBlank(groups = ADD.class,message = "要求处理时间不能为空")
    private String requestTime;

    /**
     * 处理人
     */
    @NotBlank(groups = ADD.class,message = "处理人不能为空")
    private String name;

    /**
     * 权限
     */
    @NotNull(groups = ADD.class,message = "权限不能为空")
    private Permissions permissions;

    /**
     * 优先级
     */
    private Integer level;

    /**
     * 实际处理完成时间
     */
    @NotBlank(groups = EDIT.class,message = "实际处理完成时间不能为空")
    private String actualTime;

    /**
     * 待办事件id
     */
    @NotBlank(groups = ADD.class,message = "待办事件id不能为空")
    private String eventId;

    /**
     * 事件处理状态
     */
    @NotNull(groups = EDIT.class,message = "事件处理状态不能为空")
    private EventStatus eventStatus;

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

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
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
}