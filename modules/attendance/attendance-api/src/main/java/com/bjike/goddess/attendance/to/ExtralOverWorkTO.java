package com.bjike.goddess.attendance.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 补班设置
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-10-12 04:42 ]
 * @Description: [ 补班设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ExtralOverWorkTO extends BaseTO {

    public interface TESTAddAndEdit {
    }

    /**
     * 补班类型(调休/节假日补班/其他....)
     */
    @NotBlank(groups = {ExtralOverWorkTO.TESTAddAndEdit.class}, message = "补班类型不能为空")
    private String overType;

    /**
     * 补班开始时间
     */
    @NotBlank(groups = {ExtralOverWorkTO.TESTAddAndEdit.class}, message = "补班开始时间不能为空,格式为2017-09-11 12:13:14")
    private String overStartTime;

    /**
     * 补班结束时间
     */
    @NotBlank(groups = {ExtralOverWorkTO.TESTAddAndEdit.class}, message = "补班结束时间不能为空,格式为2017-09-11 12:13:14")
    private String overEndTime;

    /**
     * 补班天数
     */
    @NotNull(groups = {ExtralOverWorkTO.TESTAddAndEdit.class}, message = "补班天数不能为空")
    private Double overDay;

    /**
     * 是否午休
     */
    @NotNull(groups = {ExtralOverWorkTO.TESTAddAndEdit.class}, message = "是否午休不能为空")
    private Boolean lunchBreak;

    public Boolean getLunchBreak() {
        return lunchBreak;
    }

    public void setLunchBreak(Boolean lunchBreak) {
        this.lunchBreak = lunchBreak;
    }

    public String getOverType() {
        return overType;
    }

    public void setOverType(String overType) {
        this.overType = overType;
    }

    public String getOverStartTime() {
        return overStartTime;
    }

    public void setOverStartTime(String overStartTime) {
        this.overStartTime = overStartTime;
    }

    public String getOverEndTime() {
        return overEndTime;
    }

    public void setOverEndTime(String overEndTime) {
        this.overEndTime = overEndTime;
    }

    public Double getOverDay() {
        return overDay;
    }

    public void setOverDay(Double overDay) {
        this.overDay = overDay;
    }

}