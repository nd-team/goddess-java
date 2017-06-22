package com.bjike.goddess.businessevaluate.to;

import com.bjike.goddess.businessevaluate.enums.CollectIntervalType;
import com.bjike.goddess.businessevaluate.enums.SendIntervalType;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.api.type.Status;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 商务评估汇总
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-30 03:13 ]
 * @Description: [ 商务评估汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BusinessEvaluateCollectTO extends BaseTO {

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空",groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 项目
     */
    @NotBlank(message = "项目不能为空",groups = {ADD.class, EDIT.class})
    private String projectId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 发送间隔类型
     */
    @NotNull(message = "发送间隔类型不能为空",groups = {ADD.class, EDIT.class})
    private SendIntervalType sendIntervalType;

    /**
     * 发送间隔
     */
    @NotNull(message = "发送间隔不能为空",groups = {ADD.class, EDIT.class})
    private Integer sendInterval;

    /**
     * 汇总间隔
     */
    @NotNull(message = "汇总间隔不能为空",groups = {ADD.class, EDIT.class})
    private CollectIntervalType collectInterval;

    /**
     * 发送对象
     */
    @NotNull(message = "发送对象不能为空",groups = {ADD.class, EDIT.class})
    private String[] sendUsers;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
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

    public Integer getSendInterval() {
        return sendInterval;
    }

    public void setSendInterval(Integer sendInterval) {
        this.sendInterval = sendInterval;
    }

    public CollectIntervalType getCollectInterval() {
        return collectInterval;
    }

    public void setCollectInterval(CollectIntervalType collectInterval) {
        this.collectInterval = collectInterval;
    }

    public String[] getSendUsers() {
        return sendUsers;
    }

    public void setSendUsers(String[] sendUsers) {
        this.sendUsers = sendUsers;
    }
}