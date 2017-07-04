package com.bjike.goddess.bonusmoneyperparepay.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 奖金资金准备
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-06-30 04:44 ]
 * @Description: [ 奖金资金准备与支付 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MoneyPerpareTO extends BaseTO {

    /**
     * 年份
     */
    @NotNull(groups = {ADD.class, EDIT.class} ,message = "年份不能为空")
    private Integer years;

    /**
     * 月份
     */
    @NotNull(groups = {ADD.class, EDIT.class} ,message = "月份不能为空")
    private Integer month;

    /**
     * 科目
     */
    @NotBlank(groups = {ADD.class, EDIT.class} ,message = "科目不能为空")
    private String subjects;

    /**
     * 总准备金
     */
    @NotNull(groups = {ADD.class, EDIT.class} ,message = "总准备金不能为空")
    private Double totalReserve;

    /**
     * 项目组
     */
    @NotBlank(groups = {ADD.class, EDIT.class} ,message = "项目组不能为空")
    private String projectGroup;

    /**
     * 比例分配
     */
    @NotNull(groups = {ADD.class, EDIT.class} ,message = "比例分配不能为空")
    private Double proportional;

    /**
     * 准备金
     */
    @NotNull(groups = {ADD.class, EDIT.class} ,message = "准备金不能为空")
    private Double reserve;


    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

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

    public Double getProportional() {
        return proportional;
    }

    public void setProportional(Double proportional) {
        this.proportional = proportional;
    }

    public Double getReserve() {
        return reserve;
    }

    public void setReserve(Double reserve) {
        this.reserve = reserve;
    }
}