package com.bjike.goddess.projectmeasure.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.projectmeasure.type.CycleType;

/**
 * 项目测算汇总业务传输对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 05:24 ]
 * @Description: [ 项目测算汇总业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectMeasureSummaryBO extends BaseBO {

    /**
     * 项目组
     */
    private String projectGroups;

    /**
     * 创建/修改人
     */
    private String createUser;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 上次发送时间String
     */
    private String lastTime;

    /**
     * 发送间隔
     */
    private Integer sendInterval;

    /**
     * 发送时间格式
     */
    private CycleType cycle;

    /**
     * 汇总间隔
     */
    private Integer detailInterval;

    /**
     * 汇总时间格式
     */
    private CycleType detailCycle;

    /**
     * 发送对象
     */
    private String emails;

    /**
     * 状态
     */
    private Status status;


    public String getProjectGroups() {
        return projectGroups;
    }

    public void setProjectGroups(String projectGroups) {
        this.projectGroups = projectGroups;
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

    public Integer getSendInterval() {
        return sendInterval;
    }

    public void setSendInterval(Integer sendInterval) {
        this.sendInterval = sendInterval;
    }

    public CycleType getCycle() {
        return cycle;
    }

    public void setCycle(CycleType cycle) {
        this.cycle = cycle;
    }

    public Integer getDetailInterval() {
        return detailInterval;
    }

    public void setDetailInterval(Integer detailInterval) {
        this.detailInterval = detailInterval;
    }

    public CycleType getDetailCycle() {
        return detailCycle;
    }

    public void setDetailCycle(CycleType detailCycle) {
        this.detailCycle = detailCycle;
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