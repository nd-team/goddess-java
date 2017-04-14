package com.bjike.goddess.lendreimbursement.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 报销记录数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-11 05:42 ]
 * @Description: [ 报销记录数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ReimburseRecordDTO extends BaseDTO {

    /**
     * 报销单号
     */
    private String reimNumber;
    /**
     * 报销人
     */
    private String reimer;

    /**
     * 项目
     */
    private String project;

    /**
     * 地区
     */
    private String area;

    /**
     * 一级科目
     */
    private String firstSubject;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    public String getReimNumber() {
        return reimNumber;
    }

    public void setReimNumber(String reimNumber) {
        this.reimNumber = reimNumber;
    }

    public String getReimer() {
        return reimer;
    }

    public void setReimer(String reimer) {
        this.reimer = reimer;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getFirstSubject() {
        return firstSubject;
    }

    public void setFirstSubject(String firstSubject) {
        this.firstSubject = firstSubject;
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
}