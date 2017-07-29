package com.bjike.goddess.recruit.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 招聘计划
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-11 10:36]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class RecruitPlanDTO extends BaseDTO {
    public interface Situation {
    }

    public interface DAY {
    }

    public interface WEEK {
    }

    public interface MONTH {
    }

    /**
     * 日期
     */
    @NotBlank(groups = {RecruitPlanDTO.Situation.class, RecruitPlanDTO.DAY.class}, message = "日期不能为空")
    private String time;
    /**
     * 招聘地区
     */
    @NotBlank(groups = {RecruitPlanDTO.Situation.class, RecruitPlanDTO.DAY.class, RecruitPlanDTO.WEEK.class, RecruitPlanDTO.MONTH.class}, message = "招聘地区不能为空")
    private String area;
    /**
     * 招聘部门/项目组
     */
    private String depart;
    /**
     * 招聘岗位
     */
    private String position;
    /**
     * 年份
     */
    @NotNull(groups = {RecruitPlanDTO.WEEK.class, RecruitPlanDTO.MONTH.class}, message = "年份不能为空")
    private Integer year;
    /**
     * 月份
     */
    @NotNull(groups = {RecruitPlanDTO.WEEK.class, RecruitPlanDTO.MONTH.class}, message = "月份不能为空")
    private Integer month;
    /**
     * 周数
     */
    @NotNull(groups = {RecruitPlanDTO.WEEK.class}, message = "周数不能为空")
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
