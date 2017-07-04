package com.bjike.goddess.bonusmoneyperparepay.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 等待付款数据传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-06-30 05:32 ]
 * @Description: [ 等待付款数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WaitingPayDTO extends BaseDTO {

    /**
     * 付款开始时间
     */
    private String startDifference;

    /**
     * 付款结束时间
     */
    private String endDifference;

    private String projectGroup;


    public String getStartDifference() {
        return startDifference;
    }

    public void setStartDifference(String startDifference) {
        this.startDifference = startDifference;
    }

    public String getEndDifference() {
        return endDifference;
    }

    public void setEndDifference(String endDifference) {
        this.endDifference = endDifference;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }
}