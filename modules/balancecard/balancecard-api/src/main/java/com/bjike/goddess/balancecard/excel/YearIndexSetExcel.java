package com.bjike.goddess.balancecard.excel;

import com.bjike.goddess.balancecard.enums.SeparateStatus;
import com.bjike.goddess.balancecard.to.YearIndexSetTO;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;


/**
 * 年度指标设置
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:11 ]
 * @Description: [ 年度指标设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class YearIndexSetExcel extends BaseTO {

    /**
     * 指标名称
     */
    @ExcelHeader(name = "指标名称",notNull = true)
    private String indexName;

    /**
     * 年份
     */
    @ExcelHeader(name = "年份",notNull = true)
    private String year;

    /**
     * 指标类型
     */
    @ExcelHeader(name = "指标类型")
    private String indexType;

    /**
     * 维度
     */
    @ExcelHeader(name = "维度")
    private String dimension;

    /**
     * 指标权重
     */
    @ExcelHeader(name = "指标权重",notNull = true)
    private Double describtion;

    /**
     * 年度目标值
     */
    @ExcelHeader(name = "年度目标值",notNull = true)
    private Double yearTarget;

    /**
     * 完成值
     */
    @ExcelHeader(name = "完成值")
    private Double complete;

    /**
     * 数据来源
     */
    @ExcelHeader(name = "数据来源")
    private String dataOrigin;

    /**
     * 考核周期
     */
    @ExcelHeader(name = "考核周期")
    private String examDuring;

    /**
     * 年度指标添加人
     */
    @ExcelHeader(name = "年度指标添加人")
    private String yearPersion;


    /**
     * 年度指标添加时间
     */
    @ExcelHeader(name = "年度指标添加时间")
    private String yearIndexTime;

    /**
     * 被分解状态
     */
    @ExcelHeader(name = "被分解状态")
    private SeparateStatus separateStatus;


    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public Double getDescribtion() {
        return describtion;
    }

    public void setDescribtion(Double describtion) {
        this.describtion = describtion;
    }

    public Double getYearTarget() {
        return yearTarget;
    }

    public void setYearTarget(Double yearTarget) {
        this.yearTarget = yearTarget;
    }

    public Double getComplete() {
        return complete;
    }

    public void setComplete(Double complete) {
        this.complete = complete;
    }

    public String getDataOrigin() {
        return dataOrigin;
    }

    public void setDataOrigin(String dataOrigin) {
        this.dataOrigin = dataOrigin;
    }

    public String getExamDuring() {
        return examDuring;
    }

    public void setExamDuring(String examDuring) {
        this.examDuring = examDuring;
    }

    public String getYearPersion() {
        return yearPersion;
    }

    public void setYearPersion(String yearPersion) {
        this.yearPersion = yearPersion;
    }

    public String getYearIndexTime() {
        return yearIndexTime;
    }

    public void setYearIndexTime(String yearIndexTime) {
        this.yearIndexTime = yearIndexTime;
    }

    public SeparateStatus getSeparateStatus() {
        return separateStatus;
    }

    public void setSeparateStatus(SeparateStatus separateStatus) {
        this.separateStatus = separateStatus;
    }
}
