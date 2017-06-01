package com.bjike.goddess.projectmeasure.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.projectmeasure.type.CycleType;
import org.hibernate.validator.constraints.NotBlank;

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

    /**
     * 项目组
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "项目组不能为空")
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
    private Integer sendInterval;

    /**
     * 发送时间格式
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "发送时间格式不能为空")
    private CycleType cycle;

    /**
     * 汇总间隔
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "汇总间隔不能为空")
    private Integer detailInterval;

    /**
     * 汇总时间格式
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "汇总时间格式不能为空")
    private CycleType detailCycle;

    /**
     * 发送对象
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "发送对象不能为空")
    private String emails;

    /**
     * 状态
     */
    private Status status;

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