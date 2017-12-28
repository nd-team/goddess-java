package com.bjike.goddess.system.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 字段对接
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-10 11:43 ]
 * @Description: [ 字段对接 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "system_fielddock")
public class FieldDock extends BaseEntity {

    /**
     * 平台1：业务市场化平台
     */
    @Column(columnDefinition = "VARCHAR(255)   COMMENT '平台1：业务市场化平台'")
    private Integer businessMarketPlatform;

    /**
     * 平台2：资金市场化平台
     */
    @Column(columnDefinition = "VARCHAR(255)   COMMENT '平台2：资金市场化平台'")
    private Integer capitalMarketPlatform;

    /**
     * 平台3：项目市场化平台
     */
    @Column(columnDefinition = "VARCHAR(255)   COMMENT '平台3：项目市场化平台'")
    private Integer projectMarketPlatform;

    /**
     * 平台4：员工机会市场化平台
     */
    @Column(columnDefinition = "VARCHAR(255)   COMMENT '平台4：员工机会市场化平台'")
    private Integer employeeMarketPlatform;

    /**
     * 平台5：生活价值平台
     */
    @Column(columnDefinition = "VARCHAR(255)   COMMENT '平台5：生活价值平台'")
    private Integer lifeValuePlatform;

    /**
     * 平台6：创意平台
     */
    @Column(columnDefinition = "VARCHAR(255)   COMMENT '平台6：创意平台'")
    private Integer creativePlatform;

    /**
     * 平台7：技能经验共享平台
     */
    @Column(columnDefinition = "VARCHAR(255)   COMMENT '平台7：技能经验共享平台'")
    private Integer skillPlatform;

    /**
     * 最新更新时间
     */
    @Column(name = "nodeUpdateTime", columnDefinition = "DATE   COMMENT '最新更新时间'")
    private LocalDate nodeUpdateTime;

    /**
     * 项目编号
     */
    @Column(name = "projectNum", columnDefinition = "VARCHAR(255)   COMMENT '项目编号'")
    private String projectNum;

    /**
     * 项目名称
     */
    @Column(name = "projectName", columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String projectName;

    /**
     * 最新更新时间
     */
    @Column(name = "newNodeUpdateTime", columnDefinition = "DATE   COMMENT '最新更新时间'")
    private LocalDate newNodeUpdateTime;

    /**
     * 新节点编号（新）
     */
    @Column(name = "newNodeNum", columnDefinition = "VARCHAR(255)   COMMENT '新节点编号（新）'")
    private String newNodeNum;

    /**
     * 节点（新）
     */
    @Column(name = "newNode", columnDefinition = "VARCHAR(255)   COMMENT '节点（新）'")
    private String newNode;

    /**
     * 最新更新时间
     */
    @Column(name = "fieldUpdateTime", columnDefinition = "DATE   COMMENT '最新更新时间'")
    private LocalDate fieldUpdateTime;

    /**
     * 字段编号（新）
     */
    @Column(name = "newFieldNum", columnDefinition = "VARCHAR(255)   COMMENT '字段编号（新）'")
    private String newFieldNum;

    /**
     * 字段（新）
     */
    @Column(name = "newField", columnDefinition = "VARCHAR(255)   COMMENT '字段（新）'")
    private String newField;

    /**
     * 重复与否
     */
    @Column(name = "fieldRepetition", columnDefinition = "VARCHAR(255)   COMMENT '重复与否'")
    private String fieldRepetition;

    /**
     * 需修改字段ID
     */
    @Column(name = "chargeField", columnDefinition = "VARCHAR(255)   COMMENT '需修改字段ID'")
    private String chargeField;

    /**
     * 更改为
     */
    @Column(name = "charge", columnDefinition = "VARCHAR(255)   COMMENT '更改为'")
    private String charge;

    /**
     * 字段（新）
     */
    @Column(name = "chargeNewField", columnDefinition = "VARCHAR(255)   COMMENT '字段（新）'")
    private String chargeNewField;

    /**
     * 重复与否
     */
    @Column(name = "repetition", columnDefinition = "VARCHAR(255)   COMMENT '重复与否'")
    private String repetition;

    /**
     * 可汇总字段
     */
    @Column(name = "summaryField", columnDefinition = "VARCHAR(255)   COMMENT '可汇总字段'")
    private String summaryField;

    /**
     * 规划模块
     */
    @Column(columnDefinition = "VARCHAR(255)   COMMENT '规划模块'")
    private Integer plan;

    /**
     * 项目名称
     */
    @Column(name = "planProjectName", columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String planProjectName;

    /**
     * 节点
     */
    @Column(name = "planNode", columnDefinition = "VARCHAR(255)   COMMENT '节点'")
    private String planNode;

    /**
     * 字段
     */
    @Column(name = "planField", columnDefinition = "VARCHAR(255)   COMMENT '字段'")
    private String planField;

    /**
     * 字段编号
     */
    @Column(name = "planFieldNum", columnDefinition = "VARCHAR(255)   COMMENT '字段编号'")
    private String planFieldNum;

    /**
     * 福利模块
     */
    @Column(columnDefinition = "VARCHAR(255)   COMMENT '福利模块'")
    private Integer welfare;

    /**
     * 项目名称
     */
    @Column(name = "welfareProjectName", columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String welfareProjectName;

    /**
     * 节点
     */
    @Column(name = "welfareNode", columnDefinition = "VARCHAR(255)   COMMENT '节点'")
    private String welfareNode;

    /**
     * 字段
     */
    @Column(name = "welfareField", columnDefinition = "VARCHAR(255)   COMMENT '字段'")
    private String welfareField;

    /**
     * 字段编号
     */
    @Column(name = "welfareFieldNum", columnDefinition = "VARCHAR(255)   COMMENT '字段编号'")
    private String welfareFieldNum;

    /**
     * 素养模块
     */
    @Column(columnDefinition = "VARCHAR(255)   COMMENT '素养模块'")
    private Integer literacy;

    /**
     * 项目名称
     */
    @Column(name = "literacyProjectName", columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String literacyProjectName;

    /**
     * 节点
     */
    @Column(name = "literacyNode", columnDefinition = "VARCHAR(255)   COMMENT '节点'")
    private String literacyNode;

    /**
     * 字段
     */
    @Column(name = "literacyField", columnDefinition = "VARCHAR(255)   COMMENT '字段'")
    private String literacyField;

    /**
     * 字段编号
     */
    @Column(name = "literacyFieldNum", columnDefinition = "VARCHAR(255)   COMMENT '字段编号'")
    private String literacyFieldNum;

    /**
     * 账务模块
     */
    @Column(columnDefinition = "VARCHAR(255)   COMMENT '账务模块'")
    private Integer account;

    /**
     * 项目名称
     */
    @Column(name = "accountProjectName", columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String accountProjectName;

    /**
     * 节点
     */
    @Column(name = "accountNode", columnDefinition = "VARCHAR(255)   COMMENT '节点'")
    private String accountNode;

    /**
     * 字段
     */
    @Column(name = "accountField", columnDefinition = "VARCHAR(255)   COMMENT '字段'")
    private String accountField;

    /**
     * 字段编号
     */
    @Column(name = "accountFieldNum", columnDefinition = "VARCHAR(255)   COMMENT '字段编号'")
    private String accountFieldNum;

    /**
     * 资金模块
     */
    @Column(columnDefinition = "VARCHAR(255)   COMMENT '资金模块'")
    private Integer money;

    /**
     * 项目名称
     */
    @Column(name = "moneyProjectName", columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String moneyProjectName;

    /**
     * 节点
     */
    @Column(name = "moneyNode", columnDefinition = "VARCHAR(255)   COMMENT '节点'")
    private String moneyNode;

    /**
     * 字段
     */
    @Column(name = "moneyField", columnDefinition = "VARCHAR(255)   COMMENT '字段'")
    private String moneyField;

    /**
     * 字段编号
     */
    @Column(name = "moneyFieldNum", columnDefinition = "VARCHAR(255)   COMMENT '字段编号'")
    private String moneyFieldNum;

    /**
     * 预算模块
     */
    @Column(columnDefinition = "VARCHAR(255)   COMMENT '预算模块'")
    private Integer budget;

    /**
     * 项目名称
     */
    @Column(name = "budgetProjectName", columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String budgetProjectName;

    /**
     * 节点
     */
    @Column(name = "budgetNode", columnDefinition = "VARCHAR(255)   COMMENT '节点'")
    private String budgetNode;

    /**
     * 字段
     */
    @Column(name = "budgetField", columnDefinition = "VARCHAR(255)   COMMENT '字段'")
    private String budgetField;

    /**
     * 字段编号
     */
    @Column(name = "budgetFieldNum", columnDefinition = "VARCHAR(255)   COMMENT '字段编号'")
    private String budgetFieldNum;

    /**
     * 客户管理模块
     */
    @Column(columnDefinition = "VARCHAR(255)   COMMENT '客户管理模块'")
    private Integer customer;

    /**
     * 项目名称
     */
    @Column(name = "customerProjectName", columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String customerProjectName;

    /**
     * 节点
     */
    @Column(name = "customerNode", columnDefinition = "VARCHAR(255)   COMMENT '节点'")
    private String customerNode;

    /**
     * 字段
     */
    @Column(name = "customerField", columnDefinition = "VARCHAR(255)   COMMENT '字段'")
    private String customerField;

    /**
     * 字段编号
     */
    @Column(name = "customerFieldNum", columnDefinition = "VARCHAR(255)   COMMENT '字段编号'")
    private String customerFieldNum;

    /**
     * 业务管理模块
     */
    @Column(columnDefinition = "VARCHAR(255)   COMMENT '业务管理模块'")
    private Integer business;

    /**
     * 项目名称
     */
    @Column(name = "businessProjectName", columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String businessProjectName;

    /**
     * 节点
     */
    @Column(name = "businessNode", columnDefinition = "VARCHAR(255)   COMMENT '节点'")
    private String businessNode;

    /**
     * 字段
     */
    @Column(name = "businessField", columnDefinition = "VARCHAR(255)   COMMENT '字段'")
    private String businessField;

    /**
     * 字段编号
     */
    @Column(name = "businessFieldNum", columnDefinition = "VARCHAR(255)   COMMENT '字段编号'")
    private String businessFieldNum;

    /**
     * 进度管理模块
     */
    @Column(columnDefinition = "VARCHAR(255)   COMMENT '进度管理模块'")
    private Integer progress;

    /**
     * 项目名称
     */
    @Column(name = "progressProjectName", columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String progressProjectName;

    /**
     * 节点
     */
    @Column(name = "progressNode", columnDefinition = "VARCHAR(255)   COMMENT '节点'")
    private String progressNode;

    /**
     * 字段
     */
    @Column(name = "progressField", columnDefinition = "VARCHAR(255)   COMMENT '字段'")
    private String progressField;

    /**
     * 字段编号
     */
    @Column(name = "progressFieldNum", columnDefinition = "VARCHAR(255)   COMMENT '字段编号'")
    private String progressFieldNum;


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

    public LocalDate getNodeUpdateTime() {
        return nodeUpdateTime;
    }

    public void setNodeUpdateTime(LocalDate nodeUpdateTime) {
        this.nodeUpdateTime = nodeUpdateTime;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public LocalDate getNewNodeUpdateTime() {
        return newNodeUpdateTime;
    }

    public void setNewNodeUpdateTime(LocalDate newNodeUpdateTime) {
        this.newNodeUpdateTime = newNodeUpdateTime;
    }

    public String getNewNodeNum() {
        return newNodeNum;
    }

    public void setNewNodeNum(String newNodeNum) {
        this.newNodeNum = newNodeNum;
    }

    public String getNewNode() {
        return newNode;
    }

    public void setNewNode(String newNode) {
        this.newNode = newNode;
    }

    public LocalDate getFieldUpdateTime() {
        return fieldUpdateTime;
    }

    public void setFieldUpdateTime(LocalDate fieldUpdateTime) {
        this.fieldUpdateTime = fieldUpdateTime;
    }

    public String getNewFieldNum() {
        return newFieldNum;
    }

    public void setNewFieldNum(String newFieldNum) {
        this.newFieldNum = newFieldNum;
    }

    public String getNewField() {
        return newField;
    }

    public void setNewField(String newField) {
        this.newField = newField;
    }

    public String getFieldRepetition() {
        return fieldRepetition;
    }

    public void setFieldRepetition(String fieldRepetition) {
        this.fieldRepetition = fieldRepetition;
    }

    public String getChargeField() {
        return chargeField;
    }

    public void setChargeField(String chargeField) {
        this.chargeField = chargeField;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getChargeNewField() {
        return chargeNewField;
    }

    public void setChargeNewField(String chargeNewField) {
        this.chargeNewField = chargeNewField;
    }

    public String getRepetition() {
        return repetition;
    }

    public void setRepetition(String repetition) {
        this.repetition = repetition;
    }

    public String getSummaryField() {
        return summaryField;
    }

    public void setSummaryField(String summaryField) {
        this.summaryField = summaryField;
    }


    public String getPlanProjectName() {
        return planProjectName;
    }

    public void setPlanProjectName(String planProjectName) {
        this.planProjectName = planProjectName;
    }

    public String getPlanNode() {
        return planNode;
    }

    public void setPlanNode(String planNode) {
        this.planNode = planNode;
    }

    public String getPlanField() {
        return planField;
    }

    public void setPlanField(String planField) {
        this.planField = planField;
    }

    public String getPlanFieldNum() {
        return planFieldNum;
    }

    public void setPlanFieldNum(String planFieldNum) {
        this.planFieldNum = planFieldNum;
    }

    public Integer getWelfare() {
        return welfare;
    }

    public void setWelfare(Integer welfare) {
        this.welfare = welfare;
    }

    public String getWelfareProjectName() {
        return welfareProjectName;
    }

    public void setWelfareProjectName(String welfareProjectName) {
        this.welfareProjectName = welfareProjectName;
    }

    public String getWelfareNode() {
        return welfareNode;
    }

    public Integer getPlan() {
        return plan;
    }

    public void setPlan(Integer plan) {
        this.plan = plan;
    }

    public void setWelfareNode(String welfareNode) {
        this.welfareNode = welfareNode;
    }

    public String getWelfareField() {
        return welfareField;
    }

    public void setWelfareField(String welfareField) {
        this.welfareField = welfareField;
    }

    public String getWelfareFieldNum() {
        return welfareFieldNum;
    }

    public void setWelfareFieldNum(String welfareFieldNum) {
        this.welfareFieldNum = welfareFieldNum;
    }

    public Integer getLiteracy() {
        return literacy;
    }

    public void setLiteracy(Integer literacy) {
        this.literacy = literacy;
    }

    public String getLiteracyProjectName() {
        return literacyProjectName;
    }

    public void setLiteracyProjectName(String literacyProjectName) {
        this.literacyProjectName = literacyProjectName;
    }

    public String getLiteracyNode() {
        return literacyNode;
    }

    public void setLiteracyNode(String literacyNode) {
        this.literacyNode = literacyNode;
    }

    public String getLiteracyField() {
        return literacyField;
    }

    public void setLiteracyField(String literacyField) {
        this.literacyField = literacyField;
    }

    public String getLiteracyFieldNum() {
        return literacyFieldNum;
    }

    public void setLiteracyFieldNum(String literacyFieldNum) {
        this.literacyFieldNum = literacyFieldNum;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public String getAccountProjectName() {
        return accountProjectName;
    }

    public void setAccountProjectName(String accountProjectName) {
        this.accountProjectName = accountProjectName;
    }

    public String getAccountNode() {
        return accountNode;
    }

    public void setAccountNode(String accountNode) {
        this.accountNode = accountNode;
    }

    public String getAccountField() {
        return accountField;
    }

    public void setAccountField(String accountField) {
        this.accountField = accountField;
    }

    public String getAccountFieldNum() {
        return accountFieldNum;
    }

    public void setAccountFieldNum(String accountFieldNum) {
        this.accountFieldNum = accountFieldNum;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getMoneyProjectName() {
        return moneyProjectName;
    }

    public void setMoneyProjectName(String moneyProjectName) {
        this.moneyProjectName = moneyProjectName;
    }

    public String getMoneyNode() {
        return moneyNode;
    }

    public void setMoneyNode(String moneyNode) {
        this.moneyNode = moneyNode;
    }

    public String getMoneyField() {
        return moneyField;
    }

    public void setMoneyField(String moneyField) {
        this.moneyField = moneyField;
    }

    public String getMoneyFieldNum() {
        return moneyFieldNum;
    }

    public void setMoneyFieldNum(String moneyFieldNum) {
        this.moneyFieldNum = moneyFieldNum;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public String getBudgetProjectName() {
        return budgetProjectName;
    }

    public void setBudgetProjectName(String budgetProjectName) {
        this.budgetProjectName = budgetProjectName;
    }

    public String getBudgetNode() {
        return budgetNode;
    }

    public void setBudgetNode(String budgetNode) {
        this.budgetNode = budgetNode;
    }

    public String getBudgetField() {
        return budgetField;
    }

    public void setBudgetField(String budgetField) {
        this.budgetField = budgetField;
    }

    public String getBudgetFieldNum() {
        return budgetFieldNum;
    }

    public void setBudgetFieldNum(String budgetFieldNum) {
        this.budgetFieldNum = budgetFieldNum;
    }

    public Integer getCustomer() {
        return customer;
    }

    public void setCustomer(Integer customer) {
        this.customer = customer;
    }

    public String getCustomerProjectName() {
        return customerProjectName;
    }

    public void setCustomerProjectName(String customerProjectName) {
        this.customerProjectName = customerProjectName;
    }

    public String getCustomerNode() {
        return customerNode;
    }

    public void setCustomerNode(String customerNode) {
        this.customerNode = customerNode;
    }

    public String getCustomerField() {
        return customerField;
    }

    public void setCustomerField(String customerField) {
        this.customerField = customerField;
    }

    public String getCustomerFieldNum() {
        return customerFieldNum;
    }

    public void setCustomerFieldNum(String customerFieldNum) {
        this.customerFieldNum = customerFieldNum;
    }

    public Integer getBusiness() {
        return business;
    }

    public void setBusiness(Integer business) {
        this.business = business;
    }

    public String getBusinessProjectName() {
        return businessProjectName;
    }

    public void setBusinessProjectName(String businessProjectName) {
        this.businessProjectName = businessProjectName;
    }

    public String getBusinessNode() {
        return businessNode;
    }

    public void setBusinessNode(String businessNode) {
        this.businessNode = businessNode;
    }

    public String getBusinessField() {
        return businessField;
    }

    public void setBusinessField(String businessField) {
        this.businessField = businessField;
    }

    public String getBusinessFieldNum() {
        return businessFieldNum;
    }

    public void setBusinessFieldNum(String businessFieldNum) {
        this.businessFieldNum = businessFieldNum;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public String getProgressProjectName() {
        return progressProjectName;
    }

    public void setProgressProjectName(String progressProjectName) {
        this.progressProjectName = progressProjectName;
    }

    public String getProgressNode() {
        return progressNode;
    }

    public void setProgressNode(String progressNode) {
        this.progressNode = progressNode;
    }

    public String getProgressField() {
        return progressField;
    }

    public void setProgressField(String progressField) {
        this.progressField = progressField;
    }

    public String getProgressFieldNum() {
        return progressFieldNum;
    }

    public void setProgressFieldNum(String progressFieldNum) {
        this.progressFieldNum = progressFieldNum;
    }
}