package com.bjike.goddess.managepromotion.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 技能晋升明细汇总
 *
 * @Author: [xiazhili]
 * @Date: [2017-09-12 14:40]
 * @Description: [ 技能晋升明细汇总]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SkillPromotionDetailCollectTO extends BaseTO {
    /**
     * 年份
     */
    private Integer year;
    /**
     * 月份
     */
    private Integer month;
    /**
     * 第几周
     */
    private Integer week;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }
}
