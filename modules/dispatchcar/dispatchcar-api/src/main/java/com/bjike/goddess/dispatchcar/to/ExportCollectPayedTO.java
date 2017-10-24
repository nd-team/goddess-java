package com.bjike.goddess.dispatchcar.to;

import com.bjike.goddess.message.entity.Message;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-27 16:51]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ExportCollectPayedTO implements Serializable{
    /**
     * 根据日导出的日期
     */
    @NotBlank (message = "根据日导出的日期不能为空")
    private String exportDay;

    /**
     * 根据周导出的日期
     */
    @NotBlank (message = "根据周导出的日期不能为空")
    private String weekDay;

    /**
     * 年
     */
    @NotNull(message = "年不能为空")
    private Integer year;

    /**
     * 月
     */
    @NotBlank (message = "月不能为空")
    private String month;

    /**
     * 季度
     */
    @NotNull(message = "季度不能为空")
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
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
