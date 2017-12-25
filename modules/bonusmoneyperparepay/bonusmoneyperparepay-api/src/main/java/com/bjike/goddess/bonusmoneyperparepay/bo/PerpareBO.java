package com.bjike.goddess.bonusmoneyperparepay.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 奖金资金准备汇总传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-06-30 04:44 ]
 * @Description: [ 奖金资金准备汇总传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PerpareBO extends BaseBO {

    /**
     * 时间
     */
    private String yearsMoth;

    /**
     * 科目
     */
    private String subjects;

    /**
     * 总准备金
     */
    private Double totalReserve;

    /**
     * 项目组
     */
    private String projectGroup;

    /**
     * 准备金
     */
    private Double reserve;


    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public Double getTotalReserve() {
        return totalReserve;
    }

    public void setTotalReserve(Double totalReserve) {
        this.totalReserve = totalReserve;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getYearsMoth() {
        return yearsMoth;
    }

    public void setYearsMoth(String yearsMoth) {
        this.yearsMoth = yearsMoth;
    }

    public Double getReserve() {
        return reserve;
    }

    public void setReserve(Double reserve) {
        this.reserve = reserve;
    }
}