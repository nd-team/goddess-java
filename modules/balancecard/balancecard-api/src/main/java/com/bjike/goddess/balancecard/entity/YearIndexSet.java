package com.bjike.goddess.balancecard.entity;

import com.bjike.goddess.balancecard.enums.SeparateStatus;
import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 年度指标设置
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:11 ]
 * @Description: [ 年度指标设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "balancecard_yearindexset")
public class YearIndexSet extends BaseEntity {

    /**
     * 指标名称
     */
    @Column(name = "indexName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '指标名称'")
    private String indexName;

    /**
     * 年度指标编号
     */
    @Column(name = "indexNumber", nullable = false, columnDefinition = "INT   COMMENT '年度指标编号'")
    private Integer indexNumber;


    /**
     * 年份
     */
    @Column(name = "year", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '年份'")
    private String year;

    /**
     * 指标类型
     */
    @Column(name = "indexType", columnDefinition = "VARCHAR(255)   COMMENT '指标类型'")
    private String indexType;

    /**
     * 维度
     */
    @Column(name = "dimension", columnDefinition = "VARCHAR(255)   COMMENT '维度'")
    private String dimension;

    /**
     * 指标权重
     */
    @Column(name = "describtion", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '指标权重'")
    private Double describtion;

    /**
     * 年度目标值
     */
    @Column(name = "yearTarget", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '年度目标值'")
    private Double yearTarget;

    /**
     * 完成值
     */
    @Column(name = "complete", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '完成值'")
    private Double complete;


    /**
     * 考核周期
     */
    @Column(name = "examDuring", columnDefinition = "VARCHAR(255)   COMMENT '考核周期'")
    private String examDuring;

    /**
     * 年度指标添加人
     */
    @Column(name = "yearPersion", columnDefinition = "VARCHAR(255)   COMMENT '年度指标添加人'")
    private String yearPersion;

    /**
     * 数据来源
     */
    @Column(name = "dataOrigin", columnDefinition = "VARCHAR(255)   COMMENT '数据来源'")
    private String dataOrigin;

    /**
     * 年度指标添加时间
     */
    @Column(name = "yearIndexTime", columnDefinition = "DATE   COMMENT '年度指标添加时间'")
    private LocalDate yearIndexTime;

    /**
     * 被分解状态
     */
    @Column(name = "separateStatus", nullable = false, columnDefinition = "INT(2)   COMMENT '被分解状态'")
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

    public String getDataOrigin() {
        return dataOrigin;
    }

    public void setDataOrigin(String dataOrigin) {
        this.dataOrigin = dataOrigin;
    }

    public LocalDate getYearIndexTime() {
        return yearIndexTime;
    }

    public void setYearIndexTime(LocalDate yearIndexTime) {
        this.yearIndexTime = yearIndexTime;
    }

    public SeparateStatus getSeparateStatus() {
        return separateStatus;
    }

    public void setSeparateStatus(SeparateStatus separateStatus) {
        this.separateStatus = separateStatus;
    }

    public Integer getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(Integer indexNumber) {
        this.indexNumber = indexNumber;
    }

}