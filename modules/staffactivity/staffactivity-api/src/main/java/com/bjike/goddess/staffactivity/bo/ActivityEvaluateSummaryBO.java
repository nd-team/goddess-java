package com.bjike.goddess.staffactivity.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 各活动评估汇总
 *
 * @Author: [sunfengtao]
 * @Date: [2017-04-10 11:02]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ActivityEvaluateSummaryBO extends BaseBO {

    /**
     * 活动时间
     */
    private String activityTime;

    /**
     * 活动主题
     */
    private String theme;

    /**
     * 活动评分<4分
     */
    private Integer ltFour;

    /**
     * 4<=活动评分<8分
     */
    private Integer gteFourAndltEight;

    /**
     * 活动评分>=8分
     */
    private Integer gteEight;

    public String getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(String activityTime) {
        this.activityTime = activityTime;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Integer getLtFour() {
        return ltFour;
    }

    public void setLtFour(Integer ltFour) {
        this.ltFour = ltFour;
    }

    public Integer getGteFourAndltEight() {
        return gteFourAndltEight;
    }

    public void setGteFourAndltEight(Integer gteFourAndltEight) {
        this.gteFourAndltEight = gteFourAndltEight;
    }

    public Integer getGteEight() {
        return gteEight;
    }

    public void setGteEight(Integer gteEight) {
        this.gteEight = gteEight;
    }
}
