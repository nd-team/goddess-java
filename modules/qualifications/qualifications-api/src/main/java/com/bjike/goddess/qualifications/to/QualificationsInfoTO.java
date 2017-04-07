package com.bjike.goddess.qualifications.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.qualifications.enums.AptitudeStatus;

import javax.validation.constraints.NotNull;

/**
 * 资质信息管理
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 04:15 ]
 * @Description: [ 资质信息管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class QualificationsInfoTO extends BaseTO {

    /**
     * 资质类别
     */
    @NotNull(message = "资质类别不能为空", groups = {ADD.class, EDIT.class})
    private String type;

    /**
     * 办理时间
     */
    @NotNull(message = "办理时间不能为空", groups = {ADD.class, EDIT.class})
    private String handleTime;

    /**
     * 审批机构
     */
    @NotNull(message = "审批机构不能为空", groups = {ADD.class, EDIT.class})
    private String examineAgency;

    /**
     * 资质办理人
     */
    private String transactor;

    /**
     * 有效年限
     */
    @NotNull(message = "有效年限不能为空", groups = {ADD.class, EDIT.class})
    private Integer year;

    /**
     * 年审时间
     */
    @NotNull(message = "年审时间不能为空", groups = {ADD.class, EDIT.class})
    private String extendTime;

    /**
     * 年审间隔时间(月)
     */
    @NotNull(message = "年审间隔时间不能为空", groups = {ADD.class, EDIT.class})
    private Integer intervalTime;

    /**
     * 办理费用(元)
     */
    @NotNull(message = "办理费用不能为空", groups = {ADD.class, EDIT.class})
    private Double handleCost;

    /**
     * 年审费用(元)
     */
    @NotNull(message = "年审费用不能为空", groups = {ADD.class, EDIT.class})
    private Double extendCost;

    /**
     * 资质状态
     */
    @NotNull(message = "资质状态不能为空", groups = {ADD.class, EDIT.class})
    private AptitudeStatus status;

    /**
     * 原文件存储位置
     */
    private String storageSite;

    /**
     * 备注
     */
    private String remark;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(String handleTime) {
        this.handleTime = handleTime;
    }

    public String getExamineAgency() {
        return examineAgency;
    }

    public void setExamineAgency(String examineAgency) {
        this.examineAgency = examineAgency;
    }

    public String getTransactor() {
        return transactor;
    }

    public void setTransactor(String transactor) {
        this.transactor = transactor;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getExtendTime() {
        return extendTime;
    }

    public void setExtendTime(String extendTime) {
        this.extendTime = extendTime;
    }

    public Integer getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(Integer intervalTime) {
        this.intervalTime = intervalTime;
    }

    public Double getHandleCost() {
        return handleCost;
    }

    public void setHandleCost(Double handleCost) {
        this.handleCost = handleCost;
    }

    public Double getExtendCost() {
        return extendCost;
    }

    public void setExtendCost(Double extendCost) {
        this.extendCost = extendCost;
    }

    public String getStorageSite() {
        return storageSite;
    }

    public void setStorageSite(String storageSite) {
        this.storageSite = storageSite;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public AptitudeStatus getStatus() {
        return status;
    }

    public void setStatus(AptitudeStatus status) {
        this.status = status;
    }
}