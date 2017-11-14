package com.bjike.goddess.managepromotion.excel;

import com.bjike.goddess.common.utils.excel.ExcelHeader;

import javax.persistence.Column;
import java.time.LocalDate;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-11-11 09:56]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class EmployeePromotedSetExcel {
    /**
     * 部门
     */
    @ExcelHeader(name = "部门",notNull = true)
    private String department;

    /**
     * 项目组
     */
    @ExcelHeader(name = "项目组",notNull = true)
    private String projectGroup;

    /**
     * 姓名
     */
    @ExcelHeader(name = "姓名",notNull = true)
    private String name;

    /**
     * 岗位
     */
    @ExcelHeader(name = "岗位",notNull = true)
    private String jobs;

    /**
     * 渠道
     */
    @ExcelHeader(name = "渠道",notNull = true)
    private String channel;

    /**
     * 时间
     */
    @ExcelHeader(name = "时间",notNull = true)
    private String times;

    /**
     * 晋升前（不包括各项补助）
     */
    @ExcelHeader(name = "晋升前",notNull = true)
    private String promotionBefore;

    /**
     * 晋升后（不包括各项补助）
     */
    @ExcelHeader(name = "晋升后",notNull = true)
    private String promotionAfter;

    /**
     * 幅度(晋升后-晋升前)
     */
    @ExcelHeader(name = "幅度",notNull = true)
    private Integer extent;

    /**
     * 总幅度
     */
    @ExcelHeader(name = "总幅度",notNull = true)
    private Integer totalRange;

    /**
     * 状态（审核中/通过/不通过）
     */
    @ExcelHeader(name = "状态",notNull = true)
    private String status;


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobs() {
        return jobs;
    }

    public void setJobs(String jobs) {
        this.jobs = jobs;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getPromotionBefore() {
        return promotionBefore;
    }

    public void setPromotionBefore(String promotionBefore) {
        this.promotionBefore = promotionBefore;
    }

    public String getPromotionAfter() {
        return promotionAfter;
    }

    public void setPromotionAfter(String promotionAfter) {
        this.promotionAfter = promotionAfter;
    }

    public Integer getExtent() {
        return extent;
    }

    public void setExtent(Integer extent) {
        this.extent = extent;
    }

    public Integer getTotalRange() {
        return totalRange;
    }

    public void setTotalRange(Integer totalRange) {
        this.totalRange = totalRange;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
