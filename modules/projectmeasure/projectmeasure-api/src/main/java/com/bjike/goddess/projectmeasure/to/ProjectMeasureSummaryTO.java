package com.bjike.goddess.projectmeasure.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.projectmeasure.type.CycleType;

import javax.validation.constraints.NotNull;

/**
 * 项目测算汇总
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 05:24 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectMeasureSummaryTO extends BaseTO {

    public interface  TestCollect{}

    /**
     * 项目组
     */
    private String[] projects;

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
    @NotNull(groups = {ADD.class, EDIT.class}, message = "发送间隔不能为空")
    private Double sendInterval;

    /**
     * 发送时间格式
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "发送时间格式不能为空")
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
    @NotNull(groups = {ADD.class, EDIT.class}, message = "发送对象不能为空")
    private String[] emails;

    /**
     * 状态
     */
    private Status status;

    /**
     * 地区数组
     */
    @NotNull(groups = {ProjectMeasureSummaryTO.TestCollect.class, ADD.class, EDIT.class}, message = "汇总所需地区不能为空")
    private String[] areas;

    public String[] getAreas() {
        return areas;
    }

    public void setAreas(String[] areas) {
        this.areas = areas;
    }

    public String[] getProjects() {
        return projects;
    }

    public void setProjects(String[] projects) {
        this.projects = projects;
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

    public String[] getEmails() {
        return emails;
    }

    public void setEmails(String[] emails) {
        this.emails = emails;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}