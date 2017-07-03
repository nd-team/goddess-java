package com.bjike.goddess.firmreward.vo;

/**
 * 项目组奖励汇总
 *
 * @Author: [sunfengtao]
 * @Date: [2017-04-14 09:12]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ProjectGroupRewardCollectVO {

    /**
     * id
     */
    private String id;

    /**
     * 所属周期
     */
    private String cycle;

    /**
     * 项目组
     */
    private String projectGroup;

    /**
     * 项目组当前排名
     */
    private String curRanking;

    /**
     * 获奖员工合计
     */
    private Integer awardStaffSum;

    /**
     * 获奖员工
     */
    private String staffName;

    /**
     * 奖励项目名称
     */
    private String awardProjectName;

    /**
     * 奖励时间
     */
    private String awardTime;

    /**
     * 奖励内容
     */
    private String awardContent;

    /**
     * 员工得分合计
     */
    private Integer staffScoreSum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getCurRanking() {
        return curRanking;
    }

    public void setCurRanking(String curRanking) {
        this.curRanking = curRanking;
    }

    public Integer getAwardStaffSum() {
        return awardStaffSum;
    }

    public void setAwardStaffSum(Integer awardStaffSum) {
        this.awardStaffSum = awardStaffSum;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getAwardProjectName() {
        return awardProjectName;
    }

    public void setAwardProjectName(String awardProjectName) {
        this.awardProjectName = awardProjectName;
    }

    public String getAwardTime() {
        return awardTime;
    }

    public void setAwardTime(String awardTime) {
        this.awardTime = awardTime;
    }

    public String getAwardContent() {
        return awardContent;
    }

    public void setAwardContent(String awardContent) {
        this.awardContent = awardContent;
    }

    public Integer getStaffScoreSum() {
        return staffScoreSum;
    }

    public void setStaffScoreSum(Integer staffScoreSum) {
        this.staffScoreSum = staffScoreSum;
    }
}
