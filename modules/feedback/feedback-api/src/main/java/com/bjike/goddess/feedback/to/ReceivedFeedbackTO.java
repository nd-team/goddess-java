package com.bjike.goddess.feedback.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.feedback.bo.ReceivedFeedbackBO;
import com.bjike.goddess.feedback.entity.ReceivedFeedback;
import com.bjike.goddess.feedback.enums.IdentityChoice;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.internal.engine.messageinterpolation.InterpolationTerm;

import javax.validation.constraints.NotNull;

/**
 * 已受理的反馈
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-02 09:24 ]
 * @Description: [ 已受理的反馈 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ReceivedFeedbackTO extends BaseTO {
    public interface TestAdvice{}
    public interface TestPriority{}
    public interface TestSolve{}

    /**
     * 优先级（系统计算分值）
     */
    private Integer systemPriority;

    /**
     * 优先级（人工编辑）
     */
    @NotBlank(message = "优先级（人工编辑）不能为空",groups = {ReceivedFeedbackTO.TestPriority.class})
    private Integer artificialPriority;

    /**
     * 非责任相关人意见数(提供接口查询详情)
     */
    private Integer responsibleNum;

    /**
     * 一线项目组意见-建议描述
     */
    @NotBlank(message = "一线项目组意见-建议描述不能为空",groups = {ReceivedFeedbackTO.TestAdvice.class})
    private String firstProjectGroupOpinion;

    /**
     * 意见提出人
     */
    private String firstIdea;

    /**
     * 规划模块意见-建议描述
     */
    @NotBlank(message = "规划模块意见-建议描述不能为空",groups = {ReceivedFeedbackTO.TestAdvice.class})
    private String planOpinion;

    /**
     * 意见提出人
     */
    private String planIdea;

    /**
     * 综合素养意见-建议描述
     */
    @NotBlank(message = "综合素养意见-建议描述不能为空",groups = {ReceivedFeedbackTO.TestAdvice.class})
    private String literacyOpinion;

    /**
     * 意见提出人
     */
    private String literacyIdea;

    /**
     * 商务市场部意见-建议描述
     */
    @NotBlank(message = "商务市场部意见-建议描述不能为空",groups = {ReceivedFeedbackTO.TestAdvice.class})
    private String businessOpinion;

    /**
     * 意见提出人
     */
    private String businessIdea;

    /**
     * 资金意见-建议描述
     */
    @NotBlank(message = "资金意见-建议描述不能为空",groups = {ReceivedFeedbackTO.TestAdvice.class})
    private String moneyOpinion;

    /**
     * 意见提出人
     */
    private String moneyIdea;

    /**
     * 账务意见-建议描述
     */
    @NotBlank(message = "账务意见-建议描述不能为空",groups = {ReceivedFeedbackTO.TestAdvice.class})
    private String accountOpinion;

    /**
     * 意见提出人
     */
    private String accountIdea;

    /**
     * 预算意见-建议描述
     */
    @NotBlank(message = "预算意见-建议描述不能为空",groups = {ReceivedFeedbackTO.TestAdvice.class})
    private String budgetOpinion;

    /**
     * 意见提出人
     */
    private String budgetIdea;

    /**
     * 研发部意见-建议描述
     */
    @NotBlank(message = "研发部意见-建议描述不能为空",groups = {ReceivedFeedbackTO.TestAdvice.class})
    private String divisionOpinion;

    /**
     * 意见提出人
     */
    private String divisionIdea;

    /**
     * 总经办（公司宏观视角）意见-建议描述
     */
    @NotBlank(message = "总经办（公司宏观视角）意见-建议描述不能为空",groups = {ReceivedFeedbackTO.TestAdvice.class})
    private String generalManagerOpinion;

    /**
     * 总经办（公司宏观视角）意见提出人
     */
    private String generalManagerIdea;
    /**
     * 身份选择
     */
    private IdentityChoice identityChoice;

    /**
     * 最终解决方案
     */
    @NotBlank(message = "最终解决方案不能为空",groups = {ReceivedFeedbackTO.TestSolve.class})
    private String finalSolution;

    /**
     * 问题解决时间
     */
    @NotBlank(message = "问题解决时间不能为空",groups = {ReceivedFeedbackTO.TestSolve.class})
    private String problemSolveTime;

    public String getFinalSolution() {
        return finalSolution;
    }

    public void setFinalSolution(String finalSolution) {
        this.finalSolution = finalSolution;
    }

    public String getProblemSolveTime() {
        return problemSolveTime;
    }

    public void setProblemSolveTime(String problemSolveTime) {
        this.problemSolveTime = problemSolveTime;
    }

    public void setFirstIdea(String firstIdea) {
        this.firstIdea = firstIdea;
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

    public IdentityChoice getIdentityChoice() {
        return identityChoice;
    }

    public void setIdentityChoice(IdentityChoice identityChoice) {
        this.identityChoice = identityChoice;
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