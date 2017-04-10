package com.bjike.goddess.bonus.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 奖罚记录
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-10 11:54 ]
 * @Description: [ 奖罚记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DisciplineRecordTO extends BaseTO {

    /**
     * 发生时间
     */
    private String occurrence;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组
     */
    private String project;

    /**
     * 姓名
     */
    private String username;

    /**
     * 工号
     */
    private String serialNumber;

    /**
     * 指标名称
     */
    private String name;

    /**
     * 原因
     */
    private String reason;

    /**
     * 分数
     */
    private Double ballot;

    /**
     * 发起方
     */
    private String launch;

    /**
     * 奖励或处罚
     */
    private Boolean status;


    public String getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(String occurrence) {
        this.occurrence = occurrence;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Double getBallot() {
        return ballot;
    }

    public void setBallot(Double ballot) {
        this.ballot = ballot;
    }

    public String getLaunch() {
        return launch;
    }

    public void setLaunch(String launch) {
        this.launch = launch;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}