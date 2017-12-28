package com.bjike.goddess.businesscommission.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 业务提成分配比例表数据传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-06-29 04:34 ]
 * @Description: [ 业务提成分配比例表数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WeightAllotDTO extends BaseDTO {
    /**
     * 地区
     */
    private String area;

    /**
     * 内部项目名称
     */
    private String projectName;

    /**
     * 信息提供人
     */
    private Double messageProportion;

    /**
     * 介绍关系揽接
     */
    private Double businessProportion;

    /**
     * 出面接洽
     */
    private Double talkProportion;

    /**
     * 维护
     */
    private Double maintainProportion;

    /**
     * 剩余分配比例
     */
    private Double surplusProportion;

//    /**
//     * 总比例
//     */
//    private Double totalProportion;
//
//    /**
//     * 备注
//     */
//    private String remark;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Double getMessageProportion() {
        return messageProportion;
    }

    public void setMessageProportion(Double messageProportion) {
        this.messageProportion = messageProportion;
    }

    public Double getBusinessProportion() {
        return businessProportion;
    }

    public void setBusinessProportion(Double businessProportion) {
        this.businessProportion = businessProportion;
    }

    public Double getTalkProportion() {
        return talkProportion;
    }

    public void setTalkProportion(Double talkProportion) {
        this.talkProportion = talkProportion;
    }

    public Double getMaintainProportion() {
        return maintainProportion;
    }

    public void setMaintainProportion(Double maintainProportion) {
        this.maintainProportion = maintainProportion;
    }

    public Double getSurplusProportion() {
        return surplusProportion;
    }

    public void setSurplusProportion(Double surplusProportion) {
        this.surplusProportion = surplusProportion;
    }
}