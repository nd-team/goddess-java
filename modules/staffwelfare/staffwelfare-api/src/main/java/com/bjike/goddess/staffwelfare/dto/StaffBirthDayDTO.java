package com.bjike.goddess.staffwelfare.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * Created by haikuang on 17-8-17.
 */
public class StaffBirthDayDTO extends BaseDTO{
    /**
     * 生日月份
     */
    private Integer month;


    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
}
