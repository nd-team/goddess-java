package com.bjike.goddess.incomecheck.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 收入核算资金回笼
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-29 09:54 ]
 * @Description: [ 收入核算资金回笼 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CheckIncomeTO extends BaseTO {

    public interface TestAdd{}

    /**
     * 地区
     */
    @NotBlank(groups = {CheckIncomeTO.TestAdd.class} , message = "地区不能为空")
    private String area;

    /**
     * 项目组
     */
    @NotBlank(groups = {CheckIncomeTO.TestAdd.class} , message = "项目组不能为空")
    private String projectGroup;

    /**
     * 项目名称
     */
    @NotBlank(groups = {CheckIncomeTO.TestAdd.class} , message = "项目名称不能为空")
    private String projectName;

    /**
     * 时间
     */
    @NotBlank(groups = {CheckIncomeTO.TestAdd.class} , message = "时间不能为空,格式为日期，年-月-日")
    private String time;

    /**
     * 计划收入
     */
    @NotNull(groups = {CheckIncomeTO.TestAdd.class} , message = "计划收入不能为空,且为数字")
    private Double planIncome;

    /**
     * 实际收入
     */
    @NotNull(groups = {CheckIncomeTO.TestAdd.class} , message = "实际收入不能为空,且为数字")
    private Double actualIncome;

    /**
     * 比例
     */
    private Double rate;

    /**
     * 差额
     */
    private Double balance;

    /**
     * 目标任务量
     */
    @NotNull(groups = {CheckIncomeTO.TestAdd.class} , message = "目标任务量不能为空,且为数字")
    private Double targetTask;

    /**
     * 实际任务量
     */
    @NotNull(groups = {CheckIncomeTO.TestAdd.class} , message = "实际任务量不能为空,且为数字")
    private Double actualTask;

    /**
     * 完工比例
     */
    private Double completeRate;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getPlanIncome() {
        return planIncome;
    }

    public void setPlanIncome(Double planIncome) {
        this.planIncome = planIncome;
    }

    public Double getActualIncome() {
        return actualIncome;
    }

    public void setActualIncome(Double actualIncome) {
        this.actualIncome = actualIncome;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getTargetTask() {
        return targetTask;
    }

    public void setTargetTask(Double targetTask) {
        this.targetTask = targetTask;
    }

    public Double getActualTask() {
        return actualTask;
    }

    public void setActualTask(Double actualTask) {
        this.actualTask = actualTask;
    }

    public Double getCompleteRate() {
        return completeRate;
    }

    public void setCompleteRate(Double completeRate) {
        this.completeRate = completeRate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}