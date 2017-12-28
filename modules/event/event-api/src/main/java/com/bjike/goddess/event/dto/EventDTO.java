package com.bjike.goddess.event.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.event.enums.Permissions;

import javax.validation.constraints.NotNull;

/**
 * 事件数据传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-09 03:58 ]
 * @Description: [ 事件数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class EventDTO extends BaseDTO {
    public interface MONTH{}
    public interface AREA{}
    public interface CLASSIFY{}
    /**
     * 年份
     */
    @NotNull(groups = EventDTO.MONTH.class,message = "年份不能为空")
    private Integer year;
    /**
     * 月份
     */
    @NotNull(groups = EventDTO.MONTH.class,message = "月份不能为空")
    private Integer month;

    /**
     * 周数
     */
    private Integer week;

    /**
     * 地区数组
     */
    @NotNull(groups = EventDTO.AREA.class,message = "地区数组不能为空")
    private String[] areas;

    /**
     * 分类数组
     */
    @NotNull(groups = EventDTO.CLASSIFY.class,message = "分类数组不能为空")
    private Permissions[] classifys;

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public String[] getAreas() {
        return areas;
    }

    public void setAreas(String[] areas) {
        this.areas = areas;
    }

    public Permissions[] getClassifys() {
        return classifys;
    }

    public void setClassifys(Permissions[] classifys) {
        this.classifys = classifys;
    }

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
}