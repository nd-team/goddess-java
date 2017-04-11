package com.bjike.goddess.intromanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 教育经历
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-28 10:40 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "intromanage_educateexperience")
public class EducateExperience extends BaseEntity {

    /**
     * 员工id
     */
    @Column(name = "staffId", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '姓名'")
    private String staffId;

    /**
     * 教育地址
     */
    @Column(name = "educatAddress", columnDefinition = "VARCHAR(255) COMMENT '教育地址'")
    private String educatAddress;

    /**
     * 培训经历
     */
    @Column(name = "trainingExperience", columnDefinition = "VARCHAR(255) COMMENT '培训经历'")
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