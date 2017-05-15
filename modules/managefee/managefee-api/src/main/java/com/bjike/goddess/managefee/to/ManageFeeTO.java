package com.bjike.goddess.managefee.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.managefee.entity.ManageFee;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 管理费
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-27 09:38 ]
 * @Description: [ 管理费 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ManageFeeTO extends BaseTO {

    public interface TestAdd{}
    public interface TestEdit{}
    /**
     * 地区
     */
    @NotBlank(groups = {ManageFeeTO.TestAdd.class,ManageFeeTO.TestEdit.class} , message = "地区不能为空")
    private String area;

    /**
     * 项目组
     */
    @NotBlank(groups = {ManageFeeTO.TestAdd.class,ManageFeeTO.TestEdit.class} , message = "项目组不能为空")
    private String projectGroup;

    /**
     * 项目名称
     */
    @NotBlank(groups = {ManageFeeTO.TestAdd.class,ManageFeeTO.TestEdit.class} , message = "项目名称不能为空")
    private String project;

    /**
     * 类别
     */
    @NotBlank(groups = {ManageFeeTO.TestAdd.class,ManageFeeTO.TestEdit.class} , message = "类别不能为空")
    private String type;

    /**
     * 年份
     */
    @NotBlank(groups = {ManageFeeTO.TestAdd.class,ManageFeeTO.TestEdit.class} , message = "年份不能为空")
    private String year;

    /**
     * 月份
     */
    @NotBlank(groups = {ManageFeeTO.TestAdd.class,ManageFeeTO.TestEdit.class} , message = "月份不能为空")
    private String month;

    /**
     * 目标管理费
     */
    @NotNull(groups = {ManageFeeTO.TestAdd.class,ManageFeeTO.TestEdit.class} , message = "目标管理费不能为空,且为数字")
    private Double targetFee;

    /**
     * 实际管理费
     */
    @NotNull(groups = {ManageFeeTO.TestEdit.class} , message = "实际管理费不能为空,且为数字")
    private Double actualFee;

    /**
     * 比率
     */
    private Double rate;

    /**
     * 差额
     */
    private Double balance;

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

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Double getTargetFee() {
        return targetFee;
    }

    public void setTargetFee(Double targetFee) {
        this.targetFee = targetFee;
    }

    public Double getActualFee() {
        return actualFee;
    }

    public void setActualFee(Double actualFee) {
        this.actualFee = actualFee;
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