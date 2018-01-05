package com.bjike.goddess.firmreward.vo;

/**
 * 员工奖励汇总
 *
 * @Author: [sunfengtao]
 * @Date: [2017-04-14 09:15]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class StaffRewardCollectVO {

    /**
     * id
     */
    private String id;

    /**
     * 所属周期
     */
    private String cycle;

    /**
     * 员工姓名
     */
    private String staffName;

    /**
     * 所属部门
     */
    private String depart;

    /**
     * 奖励项目名称
     */
    private String awardProjectName;

    /**
     * 得分的指标项目
     */
    private String indicatorItem;

    /**
     * 指标项目实际达成量
     */
    private String actualQuantity;

    /**
     * 指标项目实际得分
     */
    private Integer actualScore;

    /**
     * 员工得分合计
     */
    private Integer staffScoreSum;

    /**
     * 实际排名人数合计
     */
    private Integer actualPersonNoSum;

    /**
     * 员工当前排名
     */
    private String curRanking;

    /**
     * 奖励时间
     */
    private String awardTime;

    /**
     * 奖励内容
     */
    private String awardContent;

    /**
     * 奖励是否已收到
     */
    private Boolean awardIfReceived;

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

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getAwardProjectName() {
        return awardProjectName;
    }

    public void setAwardProjectName(String awardProjectName) {
        this.awardProjectName = awardProjectName;
    }

    public String getIndicatorItem() {
        return indicatorItem;
    }

    public void setIndicatorItem(String indicatorItem) {
        this.indicatorItem = indicatorItem;
    }

    public String getActualQuantity() {
        return actualQuantity;
    }

    public void setActualQuantity(String actualQuantity) {
        this.actualQuantity = actualQuantity;
    }

    public Integer getActualScore() {
        return actualScore;
    }

    public void setActualScore(Integer actualScore) {
        this.actualScore = actualScore;
    }

    public Integer getStaffScoreSum() {
        return staffScoreSum;
    }

    public void setStaffScoreSum(Integer staffScoreSum) {
        this.staffScoreSum = staffScoreSum;
    }

    public Integer getActualPersonNoSum() {
        return actualPersonNoSum;
    }

    public void setActualPersonNoSum(Integer actualPersonNoSum) {
        this.actualPersonNoSum = actualPersonNoSum;
    }

    public String getCurRanking() {
        return curRanking;
    }

    public void setCurRanking(String curRanking) {
        this.curRanking = curRanking;
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

    public Boolean getAwardIfReceived() {
        return awardIfReceived;
    }

    public void setAwardIfReceived(Boolean awardIfReceived) {
        this.awardIfReceived = awardIfReceived;
    }
}
