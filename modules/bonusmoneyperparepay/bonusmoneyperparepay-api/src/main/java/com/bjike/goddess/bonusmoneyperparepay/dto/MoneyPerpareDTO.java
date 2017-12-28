package com.bjike.goddess.bonusmoneyperparepay.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 奖金资金准备数据传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-06-30 04:44 ]
 * @Description: [ 奖金资金准备数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MoneyPerpareDTO extends BaseDTO {
    /**
     * 开始月份
     */
    private Integer startMonth;
    /**
     * 结束月份
     */
    private Integer endMonth;

    /**
     * 项目组
     */
    private String projectGroup;


    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public Integer getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(Integer startMonth) {
        this.startMonth = startMonth;
    }

    public Integer getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(Integer endMonth) {
        this.endMonth = endMonth;
    }
}