package com.bjike.goddess.businessproject.to;

import com.bjike.goddess.taskallotment.enums.TimeStatus;
import com.bjike.goddess.common.api.to.BaseTO;

/**
 * @Author: [xiazhili]
 * @Date: [2017-11-11 11:17]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class PersonTO extends BaseTO {
    /**
     * 所属项目组
     */
    private String projectGroup;
    /**
     * 用户名
     */
    private String userName;

    /**
     * 时间类型
     */
    private TimeStatus timeStatus;

    /**
     * 年
     */
    private int year;

    /**
     * 月
     */
    private int month;

    /**
     * 周
     */
    private int week;

    /**
     * 季度
     */
    private int quart;

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public TimeStatus getTimeStatus() {
        return timeStatus;
    }

    public void setTimeStatus(TimeStatus timeStatus) {
        this.timeStatus = timeStatus;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getQuart() {
        return quart;
    }

    public void setQuart(int quart) {
        this.quart = quart;
    }
}
