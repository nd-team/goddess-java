package com.bjike.goddess.marketactivitymanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.marketactivitymanage.type.CycleType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 市场招待汇总
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-21 02:19 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MarketServeSummaryTO extends BaseTO {

    /**
     * 项目组
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "项目组不能为空")
    private String[] projects;

    /**
     * 类型
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "类型不能为空")
    private Boolean type;

    /**
     * 开始活动时间点
     */
    private String startTime;

    /**
     * 结束活动时间点
     */
    private String endTime;

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

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
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