package com.bjike.goddess.staffactivity.vo;

/**
 * @Author: [sunfengtao]
 * @Date: [2017-05-24 16:43]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ActivityFundSummaryVO {

    /**
     * 活动时间
     */
    private String activityTime;

    /**
     * 活动地区
     */
    private String area;

    /**
     * 活动经费
     */
    private Double activityFund;

    public String getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(String activityTime) {
        this.activityTime = activityTime;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Double getActivityFund() {
        return activityFund;
    }

    public void setActivityFund(Double activityFund) {
        this.activityFund = activityFund;
    }

}
