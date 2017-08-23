package com.bjike.goddess.intromanage.vo;

/**
 * 教育经历表现层对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-28 10:40 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class EducateExperienceVO {

    /**
     * id
     */
    private String id;

    /**
     * 员工id
     */
    private String staffId;

    /**
     * 教育地址
     */
    private String educatAddress;

    /**
     * 培训经历
     */
    private String trainingExperience;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getEducatAddress() {
        return educatAddress;
    }

    public void setEducatAddress(String educatAddress) {
        this.educatAddress = educatAddress;
    }

    public String getTrainingExperience() {
        return trainingExperience;
    }

    public void setTrainingExperience(String trainingExperience) {
        this.trainingExperience = trainingExperience;
    }
}