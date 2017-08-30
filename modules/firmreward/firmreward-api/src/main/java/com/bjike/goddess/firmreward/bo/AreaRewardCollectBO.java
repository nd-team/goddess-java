package com.bjike.goddess.firmreward.bo;

/**
 * 地区奖励汇总
 *
 * @Author: [sunfengtao]
 * @Date: [2017-04-13 14:19]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class AreaRewardCollectBO {

    /**
     * 所属周期
     */
    private String circle;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组
     */
    private String projectGroup;

    /**
     * 项目组当前排名
     */
    private String projectCurRanking;

    /**
     * 获奖员工合计
     */
    private Integer awardStaffSum;

    /**
     * 获奖员工
     */
    private String awardStaff;

    /**
     * 地区当前排名
     */
    private String areaCurRanking;

    /**
     * 地区当前排名
     */
    private String awardTime;

    /**
     * 奖励内容
     */
    private String awardContent;

    /**
     * 项目组得分合计
     */
    private Integer projectGroupScoreSum;

    public String getCircle() {
        return circle;
    }

    public void setCircle(String circle) {
        this.circle = circle;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getProjectCurRanking() {
        return projectCurRanking;
    }

    public void setProjectCurRanking(String projectCurRanking) {
        this.projectCurRanking = projectCurRanking;
    }

    public Integer getAwardStaffSum() {
        return awardStaffSum;
    }

    public void setAwardStaffSum(Integer awardStaffSum) {
        this.awardStaffSum = awardStaffSum;
    }

    public String getAwardStaff() {
        return awardStaff;
    }

    public void setAwardStaff(String awardStaff) {
        this.awardStaff = awardStaff;
    }

    public String getAreaCurRanking() {
        return areaCurRanking;
    }

    public void setAreaCurRanking(String areaCurRanking) {
        this.areaCurRanking = areaCurRanking;
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

    public Integer getProjectGroupScoreSum() {
        return projectGroupScoreSum;
    }

    public void setProjectGroupScoreSum(Integer projectGroupScoreSum) {
        this.projectGroupScoreSum = projectGroupScoreSum;
    }
}
