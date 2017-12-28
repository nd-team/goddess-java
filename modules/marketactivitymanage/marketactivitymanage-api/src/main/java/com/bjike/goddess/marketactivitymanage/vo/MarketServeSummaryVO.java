package com.bjike.goddess.marketactivitymanage.vo;

import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.marketactivitymanage.type.CycleType;

/**
 * 市场招待汇总表现层对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-21 02:19 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MarketServeSummaryVO {

    /**
     * id
     */
    private String id;
    /**
     * 项目组
     */
    private String projectGroups;

    /**
     * 类型
     */
    private Boolean type;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 上次发送时间
     */
    private String lastTime;

    /**
     * 备注
     */
    private String note;

    /**
     * 发送间隔
     */
    private Double sendInterval;

    /**
     * 发送时间格式
     */
    private CycleType cycle;

    /**
     * 汇总间隔开始时间
     */
    private String startTime;

    /**
     * 汇总间隔结束时间
     */
    private String endTime;


    /**
     * 发送对象
     */
    private String emails;

    /**
     * 状态
     */
    private Status status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectGroups() {
        return projectGroups;
    }

    public void setProjectGroups(String projectGroups) {
        this.projectGroups = projectGroups;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Double getSendInterval() {
        return sendInterval;
    }

    public void setSendInterval(Double sendInterval) {
        this.sendInterval = sendInterval;
    }

    public CycleType getCycle() {
        return cycle;
    }

    public void setCycle(CycleType cycle) {
        this.cycle = cycle;
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


    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}