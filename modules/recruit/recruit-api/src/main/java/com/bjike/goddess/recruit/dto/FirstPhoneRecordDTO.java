package com.bjike.goddess.recruit.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 第一次电访记录
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-11 10:30]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FirstPhoneRecordDTO extends BaseDTO {
    /**
     * 开始日期
     */
    private String startDate;
    /**
     * 结束日期
     */
    private String endDate;
    /**
     * 岗位
     */
    private String position;

    /**
     * 姓名
     */
    private String name;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
