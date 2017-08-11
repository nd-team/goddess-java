package com.bjike.goddess.feedback.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 各模块关联明细业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-01 03:23 ]
 * @Description: [ 各模块关联明细业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RelevancyDetailBO extends BaseBO {

    /**
     * 主模板
     */
    private String mainTemplate;

    /**
     * 主功能
     */
    private String mainFunction;

    /**
     * 规划&福利
     */
    private String plan;

    /**
     * 素养
     */
    private String literacy;

    /**
     * 账务模块
     */
    private String account;

    /**
     * 资金模块
     */
    private String money;

    /**
     * 预算模块
     */
    private String budget;

    /**
     * 客户模块
     */
    private String customer;

    /**
     * 业务模块
     */
    private String business;

    /**
     * 进度模块
     */
    private String progress;


    public String getMainTemplate() {
        return mainTemplate;
    }

    public void setMainTemplate(String mainTemplate) {
        this.mainTemplate = mainTemplate;
    }

    public String getMainFunction() {
        return mainFunction;
    }

    public void setMainFunction(String mainFunction) {
        this.mainFunction = mainFunction;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getLiteracy() {
        return literacy;
    }

    public void setLiteracy(String literacy) {
        this.literacy = literacy;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }
}