package com.bjike.goddess.taskallotment.vo;

import com.bjike.goddess.taskallotment.enums.*;

/**
 * 分配及确认汇总设置表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-28 09:44 ]
 * @Description: [ 分配及确认汇总设置表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ConfirmCountEmailVO {

    /**
     * id
     */
    private String id;
    /**
     * 汇总表名称
     */
    private String name;
    /**
     * 地区
     */
    private String area;
    /**
     * 项目组
     */
    private String depart;

    /**
     * 项目
     */
    private String project;

    /**
     * 项目表
     */
    private String table;

    /**
     * 提醒间隔
     */
    private Spacing spacing;

    /**
     * 提醒频率
     */
    private Integer remind;

    /**
     * 设置时间
     */
    private String setTime;

    /**
     * 上次发送时间
     */
    private String lastTime;

    /**
     * 通报对象
     */
    private ForObject forObject;
    /**
     * 通报部门
     */
    private String forDeparts;

    /**
     * 通报个人
     */
    private String forPersons;
    /**
     * 汇总频率
     */
    private CountFrequency countFrequency;

    /**
     * 汇总对象
     */
    private CountType countType;
    /**
     * 汇总个人
     */
    private String countPersons;
    /**
     * 汇总开始时间
     */
    private String startTime;

    /**
     * 汇总结束时间
     */
    private String endTime;
    /**
     * 创建人
     */
    private String creater;

    /**
     * 状态
     */
    private Status status;

    /**
     * 备注
     */
    private String remark;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getForDeparts() {
        return forDeparts;
    }

    public void setForDeparts(String forDeparts) {
        this.forDeparts = forDeparts;
    }

    public String getForPersons() {
        return forPersons;
    }

    public void setForPersons(String forPersons) {
        this.forPersons = forPersons;
    }

    public String getCountPersons() {
        return countPersons;
    }

    public void setCountPersons(String countPersons) {
        this.countPersons = countPersons;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public Spacing getSpacing() {
        return spacing;
    }

    public void setSpacing(Spacing spacing) {
        this.spacing = spacing;
    }

    public Integer getRemind() {
        return remind;
    }

    public void setRemind(Integer remind) {
        this.remind = remind;
    }

    public String getSetTime() {
        return setTime;
    }

    public void setSetTime(String setTime) {
        this.setTime = setTime;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public ForObject getForObject() {
        return forObject;
    }

    public void setForObject(ForObject forObject) {
        this.forObject = forObject;
    }

    public CountFrequency getCountFrequency() {
        return countFrequency;
    }

    public void setCountFrequency(CountFrequency countFrequency) {
        this.countFrequency = countFrequency;
    }

    public CountType getCountType() {
        return countType;
    }

    public void setCountType(CountType countType) {
        this.countType = countType;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}