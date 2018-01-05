package com.bjike.goddess.budget.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 数据业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-02 03:58 ]
 * @Description: [ 数据业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class YearListBO extends BaseBO {

    /**
     * 年份
     */
    private Integer year;
    /**
     * 月份数据
     */
    private List<MonthListBO> monthListBOList;
    /**
     * 月份数据2
     */
    private List<MonthsListBO> monthListBOList2;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<MonthListBO> getMonthListBOList() {
        return monthListBOList;
    }

    public void setMonthListBOList(List<MonthListBO> monthListBOList) {
        this.monthListBOList = monthListBOList;
    }

    public List<MonthsListBO> getMonthListBOList2() {
        return monthListBOList2;
    }

    public void setMonthListBOList2(List<MonthsListBO> monthListBOList2) {
        this.monthListBOList2 = monthListBOList2;
    }
}