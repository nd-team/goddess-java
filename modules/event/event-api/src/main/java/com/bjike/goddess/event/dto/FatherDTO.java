package com.bjike.goddess.event.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.event.enums.EventStatus;
import com.bjike.goddess.event.enums.Permissions;

/**
 * 事件父表数据传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-10 11:27 ]
 * @Description: [ 事件父表数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FatherDTO extends BaseDTO {
    /**
     * 日期
     */
    private String time;
    /**
     * 分类数组
     */
    private Permissions[] permissionses;
    /**
     * 处理情况数组
     */
    private EventStatus[] eventStatuses;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Permissions[] getPermissionses() {
        return permissionses;
    }

    public void setPermissionses(Permissions[] permissionses) {
        this.permissionses = permissionses;
    }

    public EventStatus[] getEventStatuses() {
        return eventStatuses;
    }

    public void setEventStatuses(EventStatus[] eventStatuses) {
        this.eventStatuses = eventStatuses;
    }
}