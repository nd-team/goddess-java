package com.bjike.goddess.competitormanage.vo;

import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.competitormanage.enums.CollectIntervalType;
import com.bjike.goddess.competitormanage.enums.SendIntervalType;

import java.time.LocalDateTime;

/**
 * 竞争对手汇总表现层对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-23 11:27 ]
 * @Description: [ 竞争对手汇总表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CompetitorCollectVO {

    /**
     * id
     */
    private String id;
    /**
     * 地区
     */
    private String area;

    /**
     * 创建人/修改人
     */
    private String operateUser;

    /**
     * 上次发送时间
     */
    private String lastSendTime;

    /**
     * 汇总间隔
     */
    private CollectIntervalType collectInterval;

    /**
     * 发送间隔类型
     */
    private SendIntervalType sendIntervalType;

    /**
     * 发送间隔
     */
    private Integer sendInterval;

    /**
     * 发送对象
     */
    private String sendUser;

    /**
     * 状态
     */
    private Status status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 更新时间
     */
    private String modifyTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getOperateUser() {
        return operateUser;
    }

    public void setOperateUser(String operateUser) {
        this.operateUser = operateUser;
    }

    public String getLastSendTime() {
        return lastSendTime;
    }

    public void setLastSendTime(String lastSendTime) {
        this.lastSendTime = lastSendTime;
    }

    public CollectIntervalType getCollectInterval() {
        return collectInterval;
    }

    public void setCollectInterval(CollectIntervalType collectInterval) {
        this.collectInterval = collectInterval;
    }

    public SendIntervalType getSendIntervalType() {
        return sendIntervalType;
    }

    public void setSendIntervalType(SendIntervalType sendIntervalType) {
        this.sendIntervalType = sendIntervalType;
    }

    public Integer getSendInterval() {
        return sendInterval;
    }

    public void setSendInterval(Integer sendInterval) {
        this.sendInterval = sendInterval;
    }

    public String getSendUser() {
        return sendUser;
    }

    public void setSendUser(String sendUser) {
        this.sendUser = sendUser;
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

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}