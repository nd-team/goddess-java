package com.bjike.goddess.account.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 明细账信息
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-06 11:25 ]
 * @Description: [ 明细账信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AccountInfoTO extends BaseTO {

    /**
     * 日期
     */
    @NotNull(message = "日期不能为空",groups = {ADD.class, EDIT.class})
    private String date;

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空",groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 项目名称
     */
    @NotBlank(message = "项目名称不能为空",groups = {ADD.class, EDIT.class})
    private String projectName;

    /**
     * 项目组/部门
     */
    @NotBlank(message = "项目组/部门不能为空",groups = {ADD.class, EDIT.class})
    private String projectGroup;

    /**
     * 一级科目
     */
    @NotBlank(message = "一级科目不能为空",groups = {ADD.class, EDIT.class})
    private String firstSubject;

    /**
     * 二级科目
     */
    @NotBlank(message = "二级科目不能为空",groups = {ADD.class, EDIT.class})
    private String secondSubject;

    /**
     * 三级科目
     */
    @NotBlank(message = "三级科目不能为空",groups = {ADD.class, EDIT.class})
    private String thirdSubject;

    /**
     * 摘要
     */
    private String digest;

    /**
     * 借方金额
     */
    private Double debitAmount;

    /**
     * 贷方金额
     */
    private Double creditAmount;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getFirstSubject() {
        return firstSubject;
    }

    public void setFirstSubject(String firstSubject) {
        this.firstSubject = firstSubject;
    }

    public String getSecondSubject() {
        return secondSubject;
    }

    public void setSecondSubject(String secondSubject) {
        this.secondSubject = secondSubject;
    }

    public String getThirdSubject() {
        return thirdSubject;
    }

    public void setThirdSubject(String thirdSubject) {
        this.thirdSubject = thirdSubject;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public Double getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(Double debitAmount) {
        this.debitAmount = debitAmount;
    }

    public Double getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(Double creditAmount) {
        this.creditAmount = creditAmount;
    }
}