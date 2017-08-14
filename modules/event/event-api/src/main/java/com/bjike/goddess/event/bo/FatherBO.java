package com.bjike.goddess.event.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 事件父表业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-10 11:27 ]
 * @Description: [ 事件父表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FatherBO extends BaseBO {

    /**
     * 项目名称
     */
    private String project;

    /**
     * 事件集合
     */
    private List<EventBO> eventBOs;

    public List<EventBO> getEventBOs() {
        return eventBOs;
    }

    public void setEventBOs(List<EventBO> eventBOs) {
        this.eventBOs = eventBOs;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}