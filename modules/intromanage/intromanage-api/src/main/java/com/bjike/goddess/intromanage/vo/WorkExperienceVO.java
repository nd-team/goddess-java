package com.bjike.goddess.intromanage.vo;

/**
 * 工作经历表现层对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-28 10:47 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WorkExperienceVO {

    /**
     * id
     */
    private String id;

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