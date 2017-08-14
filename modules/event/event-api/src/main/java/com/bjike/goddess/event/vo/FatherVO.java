package com.bjike.goddess.event.vo;

import java.util.List;

/**
 * 事件父表表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-10 11:27 ]
 * @Description: [ 事件父表表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FatherVO {

    /**
     * id
     */
    private String id;
    /**
     * 项目名称
     */
    private String project;

    /**
     * 事件集合
     */
    private List<EventVO> eventBOs;

    public List<EventVO> getEventBOs() {
        return eventBOs;
    }

    public void setEventBOs(List<EventVO> eventBOs) {
        this.eventBOs = eventBOs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}