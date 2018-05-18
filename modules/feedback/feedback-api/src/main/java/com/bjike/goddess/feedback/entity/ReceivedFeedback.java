package com.bjike.goddess.feedback.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 已受理的反馈
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-02 09:24 ]
 * @Description: [ 已受理的反馈 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "feedback_receivedfeedback")
public class ReceivedFeedback extends BaseEntity {

    /**
     * 优先级（系统计算分值）
     */
    @Column( columnDefinition = "VARCHAR(255)   COMMENT '优先级（系统计算分值）'")
    private Integer systemPriority;

    /**
     * 优先级（人工编辑）
     */
    @Column(  columnDefinition = "VARCHAR(255)   COMMENT '优先级（人工编辑）'")
    private Integer artificialPriority;

    /**
     * 非责任相关人意见数(提供接口查询详情)
     */
    @Column(  columnDefinition = "VARCHAR(255)   COMMENT '非责任相关人意见数(提供接口查询详情)'")
    private Integer responsibleNum;

    /**
     * 一线项目组意见-建议描述
     */
    @Column(name = "firstProjectGroupOpinion", columnDefinition = "VARCHAR(255)   COMMENT '一线项目组意见-建议描述'")
    private String firstProjectGroupOpinion;

    /**
     * 意见提出人
     */
    @Column(name = "firstIdea",  columnDefinition = "VARCHAR(255)   COMMENT '意见提出人'")
    private String firstIdea;

    /**
     * 规划模块意见-建议描述
     */
    @Column(name = "planOpinion", columnDefinition = "VARCHAR(255)   COMMENT '规划模块意见-建议描述'")
    private String planOpinion;

    /**
     * 意见提出人
     */
    @Column(name = "planIdea",  columnDefinition = "VARCHAR(255)   COMMENT '意见提出人'")
    private String planIdea;

    /**
     * 综合素养意见-建议描述
     */
    @Column(name = "literacyOpinion", columnDefinition = "VARCHAR(255)   COMMENT '综合素养意见-建议描述'")
    private String literacyOpinion;

    /**
     * 意见提出人
     */
    @Column(name = "literacyIdea",  columnDefinition = "VARCHAR(255)   COMMENT '意见提出人'")
    private String literacyIdea;

    /**
     * 商务市场部意见-建议描述
     */
    @Column(name = "businessOpinion",  columnDefinition = "VARCHAR(255)   COMMENT '商务市场部意见-建议描述'")
    private String businessOpinion;

    /**
     * 意见提出人
     */
    @Column(name = "businessIdea",  columnDefinition = "VARCHAR(255)   COMMENT '意见提出人'")
    private String businessIdea;

    /**
     * 资金意见-建议描述
     */
    @Column(name = "moneyOpinion",  columnDefinition = "VARCHAR(255)   COMMENT '资金意见-建议描述'")
    private String moneyOpinion;

    /**
     * 意见提出人
     */
    @Column(name = "moneyIdea",  columnDefinition = "VARCHAR(255)   COMMENT '意见提出人'")
    private String moneyIdea;

    /**
     * 账务意见-建议描述
     */
    @Column(name = "accountOpinion",  columnDefinition = "VARCHAR(255)   COMMENT '账务意见-建议描述'")
    private String accountOpinion;

    /**
     * 意见提出人
     */
    @Column(name = "accountIdea",  columnDefinition = "VARCHAR(255)   COMMENT '意见提出人'")
    private String accountIdea;

    /**
     * 预算意见-建议描述
     */
    @Column(name = "budgetOpinion",  columnDefinition = "VARCHAR(255)   COMMENT '预算意见-建议描述'")
    private String budgetOpinion;

    /**
     * 意见提出人
     */
    @Column(name = "budgetIdea",  columnDefinition = "VARCHAR(255)   COMMENT '意见提出人'")
    private String budgetIdea;

    /**
     * 研发部意见-建议描述
     */
    @Column(name = "divisionOpinion",  columnDefinition = "VARCHAR(255)   COMMENT '研发部意见-建议描述'")
    private String divisionOpinion;

    /**
     * 意见提出人
     */
    @Column(name = "divisionIdea",  columnDefinition = "VARCHAR(255)   COMMENT '意见提出人'")
    private String divisionIdea;

    /**
     * 总经办（公司宏观视角）意见-建议描述
     */
    @Column(name = "generalManagerOpinion",  columnDefinition = "VARCHAR(255)   COMMENT '总经办（公司宏观视角）意见-建议描述'")
    private String generalManagerOpinion;

    /**
     * 总经办（公司宏观视角）意见提出人
     */
    @Column(name = "generalManagerIdea",  columnDefinition = "VARCHAR(255)   COMMENT '总经办（公司宏观视角）意见提出人'")
    private String generalManagerIdea;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "problemAccept_id", nullable = false,unique = true,columnDefinition = "VARCHAR(36)   COMMENT '问题反馈模块'")
    private ProblemAccept problemAccept;

    public ProblemAccept getProblemAccept() {
        return problemAccept;
    }

    public void setProblemAccept(ProblemAccept problemAccept) {
        this.problemAccept = problemAccept;
    }

    public Integer getSystemPriority() {
        return systemPriority;
    }

    public void setSystemPriority(Integer systemPriority) {
        this.systemPriority = systemPriority;
    }

    public Integer getArtificialPriority() {
        return artificialPriority;
    }

    public void setArtificialPriority(Integer artificialPriority) {
        this.artificialPriority = artificialPriority;
    }

    public Integer getResponsibleNum() {
        return responsibleNum;
    }

    public void setResponsibleNum(Integer responsibleNum) {
        this.responsibleNum = responsibleNum;
    }

    public String getFirstProjectGroupOpinion() {
        return firstProjectGroupOpinion;
    }

    public void setFirstProjectGroupOpinion(String firstProjectGroupOpinion) {
        this.firstProjectGroupOpinion = firstProjectGroupOpinion;
    }

    public String getFirstIdea() {
        return firstIdea;
    }

    public void setFirstIdea(String firstIdea) {
        this.firstIdea = firstIdea;
    }

    public String getPlanOpinion() {
        return planOpinion;
    }

    public void setPlanOpinion(String planOpinion) {
        this.planOpinion = planOpinion;
    }

    public String getPlanIdea() {
        return planIdea;
    }

    public void setPlanIdea(String planIdea) {
        this.planIdea = planIdea;
    }

    public String getLiteracyOpinion() {
        return literacyOpinion;
    }

    public void setLiteracyOpinion(String literacyOpinion) {
        this.literacyOpinion = literacyOpinion;
    }

    public String getLiteracyIdea() {
        return literacyIdea;
    }

    public void setLiteracyIdea(String literacyIdea) {
        this.literacyIdea = literacyIdea;
    }

    public String getBusinessOpinion() {
        return businessOpinion;
    }

    public void setBusinessOpinion(String businessOpinion) {
        this.businessOpinion = businessOpinion;
    }

    public String getBusinessIdea() {
        return businessIdea;
    }

    public void setBusinessIdea(String businessIdea) {
        this.businessIdea = businessIdea;
    }

    public String getMoneyOpinion() {
        return moneyOpinion;
    }

    public void setMoneyOpinion(String moneyOpinion) {
        this.moneyOpinion = moneyOpinion;
    }

    public String getMoneyIdea() {
        return moneyIdea;
    }

    public void setMoneyIdea(String moneyIdea) {
        this.moneyIdea = moneyIdea;
    }

    public String getAccountOpinion() {
        return accountOpinion;
    }

    public void setAccountOpinion(String accountOpinion) {
        this.accountOpinion = accountOpinion;
    }

    public String getAccountIdea() {
        return accountIdea;
    }

    public void setAccountIdea(String accountIdea) {
        this.accountIdea = accountIdea;
    }

    public String getBudgetOpinion() {
        return budgetOpinion;
    }

    public void setBudgetOpinion(String budgetOpinion) {
        this.budgetOpinion = budgetOpinion;
    }

    public String getBudgetIdea() {
        return budgetIdea;
    }

    public void setBudgetIdea(String budgetIdea) {
        this.budgetIdea = budgetIdea;
    }

    public String getDivisionOpinion() {
        return divisionOpinion;
    }

    public void setDivisionOpinion(String divisionOpinion) {
        this.divisionOpinion = divisionOpinion;
    }

    public String getDivisionIdea() {
        return divisionIdea;
    }

    public void setDivisionIdea(String divisionIdea) {
        this.divisionIdea = divisionIdea;
    }

    public String getGeneralManagerOpinion() {
        return generalManagerOpinion;
    }

    public void setGeneralManagerOpinion(String generalManagerOpinion) {
        this.generalManagerOpinion = generalManagerOpinion;
    }

    public String getGeneralManagerIdea() {
        return generalManagerIdea;
    }

    public void setGeneralManagerIdea(String generalManagerIdea) {
        this.generalManagerIdea = generalManagerIdea;
    }
}