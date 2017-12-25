package com.bjike.goddess.businesscommission.vo;

/**
 * 业务提成分配比例表表现层对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-06-29 04:34 ]
 * @Description: [ 业务提成分配比例表表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WeightAllotVO {

    /**
     * id
     */
    private String id;
    
    /**
     * 业务提成分配比例协商时间
     */
    private String time;

    /**
     * 部门/项目组
     */
    private String department;

    /**
     * 影响因素
     */
    private String factors;

    /**
     * 类型影响因素权重
     */
    private Double typeFactors;

    /**
     * 参与协商人
     */
    private String consultants;

    /**
     * 提成分配比例确认单是否全部确认
     */
    private Boolean confirm;

    /**
     * 已确认人
     */
    private String confirmed;

    /**
     * 未确认人
     */
    private String notConfirmed;


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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getFactors() {
        return factors;
    }

    public void setFactors(String factors) {
        this.factors = factors;
    }

    public Double getTypeFactors() {
        return typeFactors;
    }

    public void setTypeFactors(Double typeFactors) {
        this.typeFactors = typeFactors;
    }

    public String getConsultants() {
        return consultants;
    }

    public void setConsultants(String consultants) {
        this.consultants = consultants;
    }

    public Boolean getConfirm() {
        return confirm;
    }

    public void setConfirm(Boolean confirm) {
        this.confirm = confirm;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getNotConfirmed() {
        return notConfirmed;
    }

    public void setNotConfirmed(String notConfirmed) {
        this.notConfirmed = notConfirmed;
    }

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