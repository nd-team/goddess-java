package com.bjike.goddess.quartz.vo;

/**
 * 任务调度表现层对象
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-04-06 02:24 ]
 * @Description: [ 任务调度表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ScheduleJobVO {

    /**
     * id
     */
    private String id;
    /**
     * 制定人
     */
    private String userId;

    /**
     * 执行类
     */
    private String clazz;

    /**
     * 任务名
     */
    private String name;

    /**
     * 执行方法
     */
    private String method;

    /**
     * 表达式
     */
    private String expression;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否启用
     */
    private Boolean enable;
    /**
     * 任务所属调度组
     */
    private String scheduleJobGroupId;
    /**
     * 远程服务地址
     */
    private String address;
    /**
     * 任务所属组名
     */
    private String scheduleJobGroupName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getScheduleJobGroupId() {
        return scheduleJobGroupId;
    }

    public void setScheduleJobGroupId(String scheduleJobGroupId) {
        this.scheduleJobGroupId = scheduleJobGroupId;
    }

    public String getScheduleJobGroupName() {
        return scheduleJobGroupName;
    }

    public void setScheduleJobGroupName(String scheduleJobGroupName) {
        this.scheduleJobGroupName = scheduleJobGroupName;
    }
}