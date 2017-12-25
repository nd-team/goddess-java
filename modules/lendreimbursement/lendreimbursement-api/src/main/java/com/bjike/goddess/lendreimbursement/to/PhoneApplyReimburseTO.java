package com.bjike.goddess.lendreimbursement.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.lendreimbursement.enums.ReimStatus;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 报销记录
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-11 05:42 ]
 * @Description: [ 报销记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PhoneApplyReimburseTO extends BaseTO {
    public interface TestAddAndEdit {
    }

    /**
     * 报销发生日期(年月日)
     */
    @NotBlank(groups = {PhoneApplyReimburseTO.TestAddAndEdit.class}, message = "报销发生日期不能为空(年月日)")
    private String occureDate;

    /**
     * 地区
     */
    @NotBlank(groups = {PhoneApplyReimburseTO.TestAddAndEdit.class}, message = "地区不能为空")
    private String area;

    /**
     * 项目组
     */
    @NotBlank(groups = {PhoneApplyReimburseTO.TestAddAndEdit.class}, message = "项目组不能为空")
    private String projectGroup;

    /**
     * 当天任务
     */
    private String dayTask;

    /**
     * 补充说明(补充内容,摘要，报销人备注）
     */
    private String addContent;

    /**
     * 报销人
     */
    @NotBlank(groups = {PhoneApplyReimburseTO.TestAddAndEdit.class}, message = "报销人不能为空")
    private String reimer;

    /**
     * 项目名称
     */
    @NotBlank(groups = {PhoneApplyReimburseTO.TestAddAndEdit.class}, message = "项目名称不能为空")
    private String project;

    /**
     * 单据数量
     */
    private Double ticketQuantity;

    /**
     * 报销总金额
     */
    @NotNull(groups = {PhoneApplyReimburseTO.TestAddAndEdit.class}, message = "报销金额不能为空")
    private Double reimMoney;

    /**
     * 是否有发票(是/否)
     */
    @NotBlank(groups = {PhoneApplyReimburseTO.TestAddAndEdit.class}, message = "是否有发票不能为空(是/否)")
    private String ticketCondition;

    /**
     * 无发票情况备注
     */
    private String noTicketRemark;

    /**
     * 负责人
     */
    @NotBlank(groups = {PhoneApplyReimburseTO.TestAddAndEdit.class}, message = "负责人不能为空")
    private String charger;

    /**
     * 参与人
     */
    private String attender;

    /**
     * 一级科目
     */
    @NotBlank(groups = {PhoneApplyReimburseTO.TestAddAndEdit.class}, message = "一级科目不能为空")
    private String firstSubject;

    /**
     * 二级科目
     */
    @NotBlank(groups = {PhoneApplyReimburseTO.TestAddAndEdit.class}, message = "二级科目不能为空")
    private String secondSubject;

    /**
     * 三级科目
     */
    @NotBlank(groups = {PhoneApplyReimburseTO.TestAddAndEdit.class}, message = "三级科目不能为空")
    private String thirdSubject;

    /**
     * 说明
     */
    @NotBlank(groups = {PhoneApplyReimburseTO.TestAddAndEdit.class}, message = "说明不能为空")
    private String plainInfo;


    public String getOccureDate() {
        return occureDate;
    }

    public void setOccureDate(String occureDate) {
        this.occureDate = occureDate;
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

    public String getDayTask() {
        return dayTask;
    }

    public void setDayTask(String dayTask) {
        this.dayTask = dayTask;
    }

    public String getAddContent() {
        return addContent;
    }

    public void setAddContent(String addContent) {
        this.addContent = addContent;
    }

    public String getReimer() {
        return reimer;
    }

    public void setReimer(String reimer) {
        this.reimer = reimer;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Double getTicketQuantity() {
        return ticketQuantity;
    }

    public void setTicketQuantity(Double ticketQuantity) {
        this.ticketQuantity = ticketQuantity;
    }

    public Double getReimMoney() {
        return reimMoney;
    }

    public void setReimMoney(Double reimMoney) {
        this.reimMoney = reimMoney;
    }

    public String getTicketCondition() {
        return ticketCondition;
    }

    public void setTicketCondition(String ticketCondition) {
        this.ticketCondition = ticketCondition;
    }

    public String getNoTicketRemark() {
        return noTicketRemark;
    }

    public void setNoTicketRemark(String noTicketRemark) {
        this.noTicketRemark = noTicketRemark;
    }

    public String getCharger() {
        return charger;
    }

    public void setCharger(String charger) {
        this.charger = charger;
    }

    public String getAttender() {
        return attender;
    }

    public void setAttender(String attender) {
        this.attender = attender;
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

    public String getPlainInfo() {
        return plainInfo;
    }

    public void setPlainInfo(String plainInfo) {
        this.plainInfo = plainInfo;
    }
}