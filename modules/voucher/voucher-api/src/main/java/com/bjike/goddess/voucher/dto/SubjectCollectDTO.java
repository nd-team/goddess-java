package com.bjike.goddess.voucher.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 记账凭证合计金额数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-17 05:35 ]
 * @Description: [ 记账凭证合计金额数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SubjectCollectDTO extends BaseDTO {

    /**
     * 地区
     */
    private String[] area;
    /**
     * 一级科目
     */

    private String firstSubject;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 项目组/部门
     */
    private String projectGroup;

    /**
     * 查询开始时间
     */
    private String startTime;

    /**
     * 查询结束时间
     */
    private String endTime;

    public SubjectCollectDTO() {
    }

    public SubjectCollectDTO(String firstSubject) {
        this.firstSubject = firstSubject;
    }

    public SubjectCollectDTO(String[] area, String firstSubject, String projectName, String projectGroup, String startTime, String endTime) {
        this.area = area;
        this.firstSubject = firstSubject;
        this.projectName = projectName;
        this.projectGroup = projectGroup;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String[] getArea() {
        return area;
    }

    public void setArea(String[] area) {
        this.area = area;
    }

    public String getFirstSubject() {
        return firstSubject;
    }

    public void setFirstSubject(String firstSubject) {
        this.firstSubject = firstSubject;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
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