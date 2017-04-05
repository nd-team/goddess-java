package com.bjike.goddess.businessevaluate.vo.interiorevaluate;

/**
 * 一线体系评价表现层对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-30 11:00 ]
 * @Description: [ 一线体系评价表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FrontlineEvaluateVO {

    /**
     * id
     */
    private String id;
    /**
     * 现场实施情况
     */
    private String spotSituation;

    /**
     * 其它
     */
    private String another;

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

    public String getSpotSituation() {
        return spotSituation;
    }

    public void setSpotSituation(String spotSituation) {
        this.spotSituation = spotSituation;
    }

    public String getAnother() {
        return another;
    }

    public void setAnother(String another) {
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