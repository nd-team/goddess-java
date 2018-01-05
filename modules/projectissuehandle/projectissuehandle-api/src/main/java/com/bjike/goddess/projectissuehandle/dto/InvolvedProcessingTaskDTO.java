package com.bjike.goddess.projectissuehandle.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 参与处理人员的任务分配数据传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-23 05:02 ]
 * @Description: [ 参与处理人员的任务分配数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class InvolvedProcessingTaskDTO extends BaseDTO {
    /**
     * 内部项目名称
     */
    private String internalProjectName;

    /**
     * 处理人员
     */
    private String handler;
    /**
     * 内部项目名称
     */
    private String[] name;

    /**
     * 处理人员
     */
    private String[] handlers;

    public String getInternalProjectName() {
        return internalProjectName;
    }

    public void setInternalProjectName(String internalProjectName) {
        this.internalProjectName = internalProjectName;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String[] getName() {
        return name;
    }

    public void setName(String[] name) {
        this.name = name;
    }

    public String[] getHandlers() {
        return handlers;
    }

    public void setHandlers(String[] handlers) {
        this.handlers = handlers;
    }
}