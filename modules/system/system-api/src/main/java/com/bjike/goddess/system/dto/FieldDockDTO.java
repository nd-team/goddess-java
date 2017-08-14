package com.bjike.goddess.system.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 字段对接数据传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-10 11:43 ]
 * @Description: [ 字段对接数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FieldDockDTO extends BaseDTO {
    /**
     * 项目名称
     */
    private String[] projectName;

    /**
     * 平台1：业务市场化平台
     */
    private Integer businessMarketPlatform;

    /**
     * 平台2：资金市场化平台
     */
    private Integer capitalMarketPlatform;

    /**
     * 平台3：项目市场化平台
     */
    private Integer projectMarketPlatform;

    /**
     * 平台4：员工机会市场化平台
     */
    private Integer employeeMarketPlatform;

    /**
     * 平台5：生活价值平台
     */
    private Integer lifeValuePlatform;

    /**
     * 平台6：创意平台
     */
    private Integer creativePlatform;

    /**
     * 平台7：技能经验共享平台
     */
    private Integer skillPlatform;
    /**
     * 规划模块
     */
    private Integer plan;
    /**
     * 福利模块
     */
    private Integer welfare;
    /**
     * 素养模块
     */
    private Integer literacy;
    /**
     * 账务模块
     */
    private Integer account;
    /**
     * 资金模块
     */
    private Integer money;
    /**
     * 预算模块
     */
    private Integer budget;
    /**
     * 客户管理模块
     */
    private Integer customer;
    /**
     * 业务管理模块
     */
    private Integer business;
    /**
     * 进度管理模块
     */
    private Integer progress;


    public String[] getProjectName() {
        return projectName;
    }

    public void setProjectName(String[] projectName) {
        this.projectName = projectName;
    }

    public Integer getBusinessMarketPlatform() {
        return businessMarketPlatform;
    }

    public void setBusinessMarketPlatform(Integer businessMarketPlatform) {
        this.businessMarketPlatform = businessMarketPlatform;
    }

    public Integer getCapitalMarketPlatform() {
        return capitalMarketPlatform;
    }

    public void setCapitalMarketPlatform(Integer capitalMarketPlatform) {
        this.capitalMarketPlatform = capitalMarketPlatform;
    }

    public Integer getProjectMarketPlatform() {
        return projectMarketPlatform;
    }

    public void setProjectMarketPlatform(Integer projectMarketPlatform) {
        this.projectMarketPlatform = projectMarketPlatform;
    }

    public Integer getEmployeeMarketPlatform() {
        return employeeMarketPlatform;
    }

    public void setEmployeeMarketPlatform(Integer employeeMarketPlatform) {
        this.employeeMarketPlatform = employeeMarketPlatform;
    }

    public Integer getLifeValuePlatform() {
        return lifeValuePlatform;
    }

    public void setLifeValuePlatform(Integer lifeValuePlatform) {
        this.lifeValuePlatform = lifeValuePlatform;
    }

    public Integer getCreativePlatform() {
        return creativePlatform;
    }

    public void setCreativePlatform(Integer creativePlatform) {
        this.creativePlatform = creativePlatform;
    }

    public Integer getSkillPlatform() {
        return skillPlatform;
    }

    public void setSkillPlatform(Integer skillPlatform) {
        this.skillPlatform = skillPlatform;
    }

    public Integer getPlan() {
        return plan;
    }

    public void setPlan(Integer plan) {
        this.plan = plan;
    }

    public Integer getWelfare() {
        return welfare;
    }

    public void setWelfare(Integer welfare) {
        this.welfare = welfare;
    }

    public Integer getLiteracy() {
        return literacy;
    }

    public void setLiteracy(Integer literacy) {
        this.literacy = literacy;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public Integer getCustomer() {
        return customer;
    }

    public void setCustomer(Integer customer) {
        this.customer = customer;
    }

    public Integer getBusiness() {
        return business;
    }

    public void setBusiness(Integer business) {
        this.business = business;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }
}