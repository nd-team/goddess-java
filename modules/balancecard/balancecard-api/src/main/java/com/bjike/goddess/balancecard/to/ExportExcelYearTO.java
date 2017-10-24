package com.bjike.goddess.balancecard.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 年度指标设置导出条件
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:11 ]
 * @Description: [ 年度指标设置导出条件 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ExportExcelYearTO extends BaseTO {

    /**
     * 指标名称
     */
    private String indexName;

    /**
     * 指标类型
     */
    private String indexType;

    /**
     * 维度
     */
    private String dimension;

    /**
     * 年份
     */
    private Integer year;

    /**
     * 开始时间
     */
    private String startTime ;
    /**
     * 结束时间
     */
    private String endTime ;


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

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }


}
