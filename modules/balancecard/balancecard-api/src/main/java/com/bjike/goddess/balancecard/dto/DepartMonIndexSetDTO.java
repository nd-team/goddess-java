package com.bjike.goddess.balancecard.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 部门月度指标设置数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:33 ]
 * @Description: [ 部门月度指标设置数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DepartMonIndexSetDTO extends BaseDTO {

    /**
     * 指标名称
     */
    private String indexName;

    /**
     * 年份
     */
    private String year;

    /**
     * 月份
     */
    private String month;


    /**
     * 责任部门
     */
    private String department;

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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}