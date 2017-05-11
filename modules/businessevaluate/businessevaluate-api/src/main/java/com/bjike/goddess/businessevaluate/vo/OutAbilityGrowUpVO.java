package com.bjike.goddess.businessevaluate.vo;

import com.bjike.goddess.businessevaluate.enums.GroupUpType;

/**
 * 能力成长表现层对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-28 04:13 ]
 * @Description: [ 能力成长表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OutAbilityGrowUpVO {

    /**
     * id
     */
    private String id;


    /**
     * 差额增速最快月份
     */
    private String fastMonth;

    /**
     * 差额增速最慢月份
     */
    private String slowMonth;

    /**
     * 收入最多月份
     */
    private String maximumMonth;

    /**
     * 收入最少月份
     */
    private String leastMonth;

    /**
     * 支出最多月份
     */
    private String outMaximumMonth;

    /**
     * 支出最少月份
     */
    private String outLeastMonth;

    /**
     * 项目信息Id
     */
    private String projectInfoId;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目名称
     */
    private String project;

    /**
     * 工期开始时间
     */
    private String startTime;

    /**
     * 工期结束时间
     */
    private String endTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFastMonth() {
        return fastMonth;
    }

    public void setFastMonth(String fastMonth) {
        this.fastMonth = fastMonth;
    }

    public String getSlowMonth() {
        return slowMonth;
    }

    public void setSlowMonth(String slowMonth) {
        this.slowMonth = slowMonth;
    }

    public String getMaximumMonth() {
        return maximumMonth;
    }

    public void setMaximumMonth(String maximumMonth) {
        this.maximumMonth = maximumMonth;
    }

    public String getLeastMonth() {
        return leastMonth;
    }

    public void setLeastMonth(String leastMonth) {
        this.leastMonth = leastMonth;
    }

    public String getProjectInfoId() {
        return projectInfoId;
    }

    public void setProjectInfoId(String projectInfoId) {
        this.projectInfoId = projectInfoId;
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

    public String getOutMaximumMonth() {
        return outMaximumMonth;
    }

    public void setOutMaximumMonth(String outMaximumMonth) {
        this.outMaximumMonth = outMaximumMonth;
    }

    public String getOutLeastMonth() {
        return outLeastMonth;
    }

    public void setOutLeastMonth(String outLeastMonth) {
        this.outLeastMonth = outLeastMonth;
    }
}