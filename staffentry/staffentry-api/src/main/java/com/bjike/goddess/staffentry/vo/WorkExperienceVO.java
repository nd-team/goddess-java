package com.bjike.goddess.staffentry.vo;

/**
 * 工作经历表现层对象
 * @Author: [tanghaixiang]
 * @Date: [2017-03-10 10:36]
 * @Description: [工作经历表现层对象]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class WorkExperienceVO {

    /**
     * id
     */
    private String id;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 公司名称
     */
    private String firm;

    /**
     * 工作描述
     */
    private String jobDescription;

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

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
