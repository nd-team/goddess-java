package com.bjike.goddess.attendance.vo;

import com.bjike.goddess.attendance.enums.OverTimesType;
import com.bjike.goddess.attendance.enums.Status;

/**
 * 加班次数
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-09-22 03:12 ]
 * @Description: [ 加班次数 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OverWorkTimesVO {

    /**
     * 时间汇总类型
     */
    private OverTimesType overTimesType;

    /**
     * 星期一
     */
    private String first;
    /**
     * 星期二
     */
    private String second;

    /**
     * 星期三
     */
    private String third;

    /**
     * 星期四
     */
    private String four;

    /**
     * 星期五
     */
    private String five;

    /**
     * 星期六
     */
    private String six;

    /**
     * 星期日
     */
    private String seven;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 季度的第一个月
     */
    private String firstMonth;
    /**
     * 季度的第二个月
     */
    private String secndMonth;
    /**
     * 季度的第三个月
     */
    private String thirdMonth;



    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getThird() {
        return third;
    }

    public void setThird(String third) {
        this.third = third;
    }

    public String getFour() {
        return four;
    }

    public void setFour(String four) {
        this.four = four;
    }

    public String getFive() {
        return five;
    }

    public void setFive(String five) {
        this.five = five;
    }

    public String getSix() {
        return six;
    }

    public void setSix(String six) {
        this.six = six;
    }

    public String getSeven() {
        return seven;
    }

    public void setSeven(String seven) {
        this.seven = seven;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public OverTimesType getOverTimesType() {
        return overTimesType;
    }

    public void setOverTimesType(OverTimesType overTimesType) {
        this.overTimesType = overTimesType;
    }

    public String getFirstMonth() {
        return firstMonth;
    }

    public void setFirstMonth(String firstMonth) {
        this.firstMonth = firstMonth;
    }

    public String getSecndMonth() {
        return secndMonth;
    }

    public void setSecndMonth(String secndMonth) {
        this.secndMonth = secndMonth;
    }

    public String getThirdMonth() {
        return thirdMonth;
    }

    public void setThirdMonth(String thirdMonth) {
        this.thirdMonth = thirdMonth;
    }
}