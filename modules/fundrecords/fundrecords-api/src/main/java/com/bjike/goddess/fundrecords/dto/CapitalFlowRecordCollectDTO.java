package com.bjike.goddess.fundrecords.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * @Author: [dengjunren]
 * @Date: [2018-04-19 17:28]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CapitalFlowRecordCollectDTO extends BaseDTO {

    /**
     * 汇总类型
     */
    private String type;

    /**
     * 开始时间
     */
    private String startTime;


    /**
     * 结束时间
     */
    private String endTime;


    /**
     * 地区
     */
    private String area;


    /**
     * 项目组
     */
    private String projectGroup;

    /**
     * 项目名称
     */
    private String project;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}
