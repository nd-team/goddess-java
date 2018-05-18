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
     * 模块名称中文名
     */
    private String projectChineseName;
    /**
     * 模块名称英文名
     */
    private String projectEnglishName;
    /**
     * 功能名称中文名
     */
    private String functionChineseName;

    /**
     * 功能名称英文名
     */
    private String functionEnglishName;

    /**
     * 事件集合
     */
    private List<EventVO> eventBOs;

    /**
     * 2017-12-28
     *  新增字段
     *  创建日期
     */
    private String startDate;

    public String getStartDate() {return startDate;}

    public void setStartDate(String startDate) {  this.startDate = startDate; }

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
}