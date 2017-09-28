package com.bjike.goddess.lendreimbursement.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.lendreimbursement.enums.LendStatus;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 申请借款
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-06 10:01 ]
 * @Description: [ 申请借款 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PhoneApplyLendTO extends BaseTO {

    public interface TESTAddAndEdit{}

    /**
     * 预计借款时间日期
     */
    @NotBlank(groups = {PhoneApplyLendTO.TESTAddAndEdit.class},message ="预计借款时间不能为空，日期格式未年-月-日" )
    private String estimateLendDate;

    /**
     * 借款人
     */
    @NotBlank(groups = {PhoneApplyLendTO.TESTAddAndEdit.class},message ="借款人不能为空" )
    private String lender;

    /**
     * 负责人
     */
    @NotBlank(groups = {PhoneApplyLendTO.TESTAddAndEdit.class},message ="负责人不能为空" )
    private String charger;

    /**
     * 地区
     */
    @NotBlank(groups = {PhoneApplyLendTO.TESTAddAndEdit.class},message ="地区不能为空" )
    private String area;

    /**
     * 项目组
     */
    @NotBlank(groups = {PhoneApplyLendTO.TESTAddAndEdit.class},message ="项目组不能为空" )
    private String projectGroup;

    /**
     * 项目名称
     */
    @NotBlank(groups = {PhoneApplyLendTO.TESTAddAndEdit.class},message ="项目名称不能为空" )
    private String projectName;

    /**
     * 参与人员
     */
    private String attender;

    /**
     * 借款方式
     */
    @NotBlank(groups = {PhoneApplyLendTO.TESTAddAndEdit.class},message ="借款方式不能为空" )
    private String lendWay;

    /**
     * 一级科目
     */
    @NotBlank(groups = {PhoneApplyLendTO.TESTAddAndEdit.class},message ="一级科目不能为空" )
    private String firstSubject;

    /**
     * 二级科目
     */
    @NotBlank(groups = {PhoneApplyLendTO.TESTAddAndEdit.class},message ="二级科目不能为空" )
    private String secondSubject;

    /**
     * 三级科目
     */
    @NotBlank(groups = {PhoneApplyLendTO.TESTAddAndEdit.class},message ="三级科目不能为空" )
    private String thirdSubject;

    /**
     * 说明
     */
    @NotBlank(groups = {PhoneApplyLendTO.TESTAddAndEdit.class},message ="说明不能为空" )
    private String explains;



    /**
     * 补充说明（借款事由）
     */
    @NotBlank(groups = {PhoneApplyLendTO.TESTAddAndEdit.class},message ="借款事由不能为空" )
    private String lendReson;

    /**
     * 金额
     */
    @NotNull(groups = {PhoneApplyLendTO.TESTAddAndEdit.class},message ="金额不能为空" )
    private Double money;

    /**
     * 商品链接
     */
    private String goodsLink;


    public String getEstimateLendDate() {
        return estimateLendDate;
    }

    public void setEstimateLendDate(String estimateLendDate) {
        this.estimateLendDate = estimateLendDate;
    }

    public String getLender() {
        return lender;
    }

    public void setLender(String lender) {
        this.lender = lender;
    }

    public String getCharger() {
        return charger;
    }

    public void setCharger(String charger) {
        this.charger = charger;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getAttender() {
        return attender;
    }

    public void setAttender(String attender) {
        this.attender = attender;
    }

    public String getLendWay() {
        return lendWay;
    }

    public void setLendWay(String lendWay) {
        this.lendWay = lendWay;
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

    public String getExplains() {
        return explains;
    }

    public void setExplains(String explains) {
        this.explains = explains;
    }

    public String getLendReson() {
        return lendReson;
    }

    public void setLendReson(String lendReson) {
        this.lendReson = lendReson;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getGoodsLink() {
        return goodsLink;
    }

    public void setGoodsLink(String goodsLink) {
        this.goodsLink = goodsLink;
    }

}