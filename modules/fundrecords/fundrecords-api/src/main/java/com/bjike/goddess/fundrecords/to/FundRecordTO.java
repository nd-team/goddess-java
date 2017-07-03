package com.bjike.goddess.fundrecords.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 资金流水
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-20 09:33 ]
 * @Description: [ 资金流水 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FundRecordTO extends BaseTO {

    /**
     * 流水日期
     */
    @NotBlank(message = "日期不能为空", groups = {ADD.class,EDIT.class})
    private String recordDate;

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空", groups = {ADD.class,EDIT.class})
    private String area;

    /**
     * 项目组
     */
    @NotBlank(message = "项目组不能为空", groups = {ADD.class,EDIT.class})
    private String projectGroup;

    /**
     * 项目名称
     */
    @NotBlank(message = "项目名称不能为空", groups = {ADD.class, EDIT.class})
    private String project;

    /**
     * 摘要
     */
    @NotBlank(message = "摘要不能为空", groups = {ADD.class, EDIT.class})
    private String digest;

    /**
     * 收入
     */
    @NotNull(message = "收入不能为空", groups = {ADD.class, EDIT.class})
    private Double income;

    /**
     * 支出
     */
    @NotNull(message = "支出不能为空", groups = {ADD.class, EDIT.class})
    private Double expenditure;

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

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

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(Double expenditure) {
        this.expenditure = expenditure;
    }


}