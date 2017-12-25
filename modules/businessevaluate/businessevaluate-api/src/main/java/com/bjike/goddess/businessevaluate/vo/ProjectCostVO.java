package com.bjike.goddess.businessevaluate.vo;

/**
 * 项目费用表现层对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-28 02:17 ]
 * @Description: [ 项目费用表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectCostVO {

    /**
     * id
     */
    private String id;
    /**
     * 服务费用
     */
    private Double serviceCost;

    /**
     * 招待费用
     */
    private Double entertainCost;

    /**
     * 提成
     */
    private Double commission;

    /**
     * 其它
     */
    private Double another;

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

    public Double getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(Double serviceCost) {
        this.serviceCost = serviceCost;
    }

    public Double getEntertainCost() {
        return entertainCost;
    }

    public void setEntertainCost(Double entertainCost) {
        this.entertainCost = entertainCost;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public Double getAnother() {
        return another;
    }

    public void setAnother(Double another) {
        this.another = another;
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
}