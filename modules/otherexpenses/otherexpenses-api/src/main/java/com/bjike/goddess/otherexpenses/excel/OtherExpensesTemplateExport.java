package com.bjike.goddess.otherexpenses.excel;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

/**
 * 其他费用excel模板导出
 * @Author: [zhuangkaiqin]
 * @Date: [2017-06-23 10:52]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class OtherExpensesTemplateExport extends BaseTO {

    /**
     * 地区
     */
    @ExcelHeader(name = "地区", notNull = true)
    private String area;

    /**
     * 项目组
     */
    @ExcelHeader(name = "项目组", notNull = true)
    private String project;

    /**
     * 类别
     */
    @ExcelHeader(name = "类别", notNull = true)
    private String type;

    /**
     * 项目名称
     */
    @ExcelHeader(name = "项目名称", notNull = true)
    private String name;

    /**
     * 年份
     */
    @ExcelHeader(name = "年份", notNull = true)
    private Integer year;

    /**
     * 月份
     */
    @ExcelHeader(name = "月份", notNull = true)
    private Integer month;

    /**
     * 目标其他费用
     */
    @ExcelHeader(name = "目标其他费用", notNull = true)
    private Double target;

    /**
     * 实际其他费用
     */
    @ExcelHeader(name = "实际其他费用", notNull = true)
    private Double actual;

    /**
     * 比例
     */
    @ExcelHeader(name = "比例")
    private Double ratio;

    /**
     * 差额
     */
    @ExcelHeader(name = "差额")
    private Double balance;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Double getTarget() {
        return target;
    }

    public void setTarget(Double target) {
        this.target = target;
    }

    public Double getActual() {
        return actual;
    }

    public void setActual(Double actual) {
        this.actual = actual;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
