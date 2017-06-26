package com.bjike.goddess.receivable.to;

/**
 * Created by ike on 17-6-20.
 */
public class CollectCompareTO {
    /**
     * 地区
     */
    private String area;

    /**
     * 项目名称
     */
    private String innerName;
    /**
     * 总包单位
     */
    private String contractor;
    /**
     * 月份
     */
    private String month;

    /**
     * 季度
     */
    private String quarter;
    /**
     * 年份
     */
    private String year;

    /**
     * 到账开始时间
     */
    private String startTime;
    /**
     * 到账结束时间
     */
    private String endTime;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getInnerName() {
        return innerName;
    }

    public void setInnerName(String innerName) {
        this.innerName = innerName;
    }

    public String getContractor() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
