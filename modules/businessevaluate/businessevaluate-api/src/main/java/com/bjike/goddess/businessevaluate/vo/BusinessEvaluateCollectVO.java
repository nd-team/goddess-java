package com.bjike.goddess.businessevaluate.vo;

import com.bjike.goddess.businessevaluate.enums.CollectIntervalType;
import com.bjike.goddess.businessevaluate.enums.SendIntervalType;
import com.bjike.goddess.common.api.type.Status;

/**
 * 商务评估汇总表现层对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-30 03:13 ]
 * @Description: [ 商务评估汇总表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BusinessEvaluateCollectVO {

    /**
     * id
     */
    private String id;
    /**
     * 地区
     */
    private String area;

    /**
     * 项目
     */
    private String project;

    /**
     * 创建人/修改人
     */
    private String operateUser;

    /**
     * 上次发送时间
     */
    private String lastSendTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 发送间隔类型
     */
    private SendIntervalType sendIntervalType;

    /**
     * 发送间隔
     */
    private String sendInterval;

    /**
     * 汇总间隔
     */
    private CollectIntervalType collectInterval;

    /**
     * 发送对象
     */
    private String sendUser;

    /**
     * 数据状态
     */
    private Status status;


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

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public SendIntervalType getSendIntervalType() {
        return sendIntervalType;
    }

    public void setSendIntervalType(SendIntervalType sendIntervalType) {
        this.sendIntervalType = sendIntervalType;
    }

    public String getSendInterval() {
        return sendInterval;
    }

    public void setSendInterval(String sendInterval) {
        this.sendInterval = sendInterval;
    }

    public CollectIntervalType getCollectInterval() {
        return collectInterval;
    }

    public void setCollectInterval(CollectIntervalType collectInterval) {
        this.collectInterval = collectInterval;
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
}