package com.bjike.goddess.staffentry.vo;

/**
 * 学习经历表现层对象
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-03-10 10:36]
 * @Description: [学习经历表现层对象]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class StudyExperienceVO {

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
     * 就读学校
     */
    private String school;

    /**
     * 毕业证书（学历）
     */
    private String certificate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }
}
