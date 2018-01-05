package com.bjike.goddess.intromanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;

/**
 * 教育经历业务传输对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-28 10:40 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class EducateExperienceBO extends BaseBO {

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