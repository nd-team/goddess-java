package com.bjike.goddess.intromanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;

/**
 * 工作经历业务传输对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-28 10:47 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WorkExperienceBO extends BaseBO {

    /**
     * 员工id
     */
    private String staffId;

    /**
     * 曾经参与的组织与活动
     */
    private String participatedActivity;

    /**
     * 项目经历
     */
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