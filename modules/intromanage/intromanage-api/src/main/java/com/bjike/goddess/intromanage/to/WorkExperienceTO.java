package com.bjike.goddess.intromanage.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 工作经历
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-28 10:47 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WorkExperienceTO extends BaseTO {

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