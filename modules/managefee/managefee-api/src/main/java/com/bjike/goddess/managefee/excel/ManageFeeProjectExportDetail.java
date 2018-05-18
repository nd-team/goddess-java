package com.bjike.goddess.managefee.excel;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

/**
 * 管理费业务传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-27 09:38 ]
 * @Description: [ 管理费业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ManageFeeProjectExportDetail extends BaseBO {

    /**
     * 项目名称
     */
    @ExcelHeader(name = "项目名称",notNull = true)
    private String project;

    /**
     * 项目组
     */
    @ExcelHeader(name = "项目组",notNull = true)
    private String projectGroup;

    /**
     * 地区
     */
    @ExcelHeader(name = "地区",notNull = true)
    private String area;
    /**
     * 类别
     */
    @ExcelHeader(name = "类别",notNull = true)
    private String type;

    /**
     * 年份
     */
    @ExcelHeader(name = "年份",notNull = true)
    private String year;

    /**
     * 月份
     */
    @ExcelHeader(name = "月份",notNull = true)
    private String month;

    /**
     * 目标管理费用
     */
    @ExcelHeader(name = "目标管理费用",notNull = true)
    private Double targetFee;

    /**
     * 实际管理费用
     */
    @ExcelHeader(name = "实际管理费用",notNull = true)
    private Double actualFee;

    /**
     * 比率
     */
    @ExcelHeader(name = "比率",notNull = true)
    private String ratePersent;

    /**
     * 差额
     */
    @ExcelHeader(name = "差额",notNull = true)
    private Double balance;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Double getTargetFee() {
        return targetFee;
    }

    public void setTargetFee(Double targetFee) {
        this.targetFee = targetFee;
    }

    public Double getActualFee() {
        return actualFee;
    }

    public void setActualFee(Double actualFee) {
        this.actualFee = actualFee;
    }

    public String getRatePersent() {
        return ratePersent;
    }

    public void setRatePersent(String ratePersent) {
        this.ratePersent = ratePersent;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

}