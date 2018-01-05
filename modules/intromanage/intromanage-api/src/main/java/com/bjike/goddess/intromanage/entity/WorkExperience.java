package com.bjike.goddess.intromanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 工作经历
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-28 10:47 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "intromanage_workexperience")
public class WorkExperience extends BaseEntity {

    /**
     * 员工id
     */
    @Column(name = "staffId", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '员工id'")
    private String staffId;

    /**
     * 曾经参与的组织与活动
     */
    @Column(name = "participatedActivity", columnDefinition = "VARCHAR(255) COMMENT '曾经参与的组织与活动'")
    private String participatedActivity;

    /**
     * 项目经历
     */
    @Column(name = "projectExperience", columnDefinition = "VARCHAR(255) COMMENT '项目经历'")
    private String projectExperience;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getParticipatedActivity() {
        return participatedActivity;
    }

    public void setParticipatedActivity(String participatedActivity) {
        this.participatedActivity = participatedActivity;
    }

    public String getProjectExperience() {
        return projectExperience;
    }

    public void setProjectExperience(String projectExperience) {
        this.projectExperience = projectExperience;
    }
}