package com.bjike.goddess.staffactivity.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 活动经费汇总
 *
 * @Author: [sunfengtao]
 * @Date: [2017-04-10 10:53]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ActivityFundSummaryBO extends BaseBO {

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
