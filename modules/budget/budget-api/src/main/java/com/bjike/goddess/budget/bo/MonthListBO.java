package com.bjike.goddess.budget.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 周列表业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-02 03:58 ]
 * @Description: [ 周列表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MonthListBO extends BaseBO {

    /**
     * 月份
     */
    private Integer month;
    /**
     * 周期数据列表
     */
    private List<WeekListBO> weekListBOList;

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public List<WeekListBO> getWeekListBOList() {
        return weekListBOList;
    }

    public void setWeekListBOList(List<WeekListBO> weekListBOList) {
        this.weekListBOList = weekListBOList;
    }
}