package com.bjike.goddess.competitormanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.competitormanage.enums.CollectIntervalType;
import com.bjike.goddess.competitormanage.enums.SendIntervalType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 竞争对手汇总
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-23 11:27 ]
 * @Description: [ 竞争对手汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CompetitorCollectTO extends BaseTO {

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空", groups = {ADD.class, EDIT.class})
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
//    @NotNull(message = "汇总间隔不能为空", groups = {ADD.class, EDIT.class})
    private CollectIntervalType collectInterval;

    /**
     * 发送间隔类型
     */
//    @NotNull(message = "发送间隔类型不能为空", groups = {ADD.class, EDIT.class})
    private SendIntervalType sendIntervalType;

    /**
     * 发送间隔
     */
    @NotNull(message = "发送间隔不能为空", groups = {ADD.class, EDIT.class})
    private Integer sendInterval;

    /**
     * 发送对象
     */
    @NotBlank(message = "发送对象不能为空", groups = {ADD.class, EDIT.class})
    private String sendUser;

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
}