package com.bjike.goddess.dispatchcar.to;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-27 16:51]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ExportCollectPayedTO {
    /**
     * 根据日导出的日期
     */
    private String exportDay;

    /**
     * 根据周导出的日期
     */
    private String weekDay;

    /**
     * 年
     */
    private String year;

    /**
     * 月
     */
    private String month;

    /**
     * 季度
     */
    private Integer quarter;
    public String getExportDay() {
        return exportDay;
    }

    public void setExportDay(String exportDay) {
        this.exportDay = exportDay;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getQuarter() {
        return quarter;
    }

    public void setQuarter(Integer quarter) {
        this.quarter = quarter;
    }
}
