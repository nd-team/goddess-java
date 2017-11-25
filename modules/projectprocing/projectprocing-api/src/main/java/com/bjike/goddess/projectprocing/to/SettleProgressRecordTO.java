package com.bjike.goddess.projectprocing.to;

import com.bjike.goddess.common.api.to.BaseTO;

import java.time.LocalDate;

/**
 * 结算进度调整记录&结算问题汇总
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 03:19 ]
 * @Description: [ 结算进度调整记录&结算问题汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SettleProgressRecordTO extends BaseTO {

    /**
     * 外包单位
     */
    private String outUnit;

    /**
     * 派工名称
     */
    private String dispatchingName;

    /**
     * 内部项目名称
     */
    private String internalName;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 修改日期时间
     */
    private String updateDate;

    /**
     * 修改内容
     */
    private String updateContent;

    /**
     * 问题描述（修改原因）
     */
    private String problemDescription;

    /**
     * 问题类型
     */
    private String problemType;

    /**
     * 协助人
     */
    private String assistPeoper;

    /**
     * 协助内容
     */
    private String assistContent;

    /**
     * 协助完成时间
     */
    private String assistDate;

    /**
     * 资金模块
     */
    private String moneyModule;

    /**
     * 资金模块意见
     */
    private String moneyModuleOpinion;

    /**
     * 总经理
     */
    private String generalManager;

    /**
     * 审批情况
     */
    private String approvalExam;

    /**
     * 是否确认
     */
    private Boolean confirm;

    public String getOutUnit() {
        return outUnit;
    }

    public void setOutUnit(String outUnit) {
        this.outUnit = outUnit;
    }

    public String getDispatchingName() {
        return dispatchingName;
    }

    public void setDispatchingName(String dispatchingName) {
        this.dispatchingName = dispatchingName;
    }

    public String getInternalName() {
        return internalName;
    }

    public void setInternalName(String internalName) {
        this.internalName = internalName;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateContent() {
        return updateContent;
    }

    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public String getAssistPeoper() {
        return assistPeoper;
    }

    public void setAssistPeoper(String assistPeoper) {
        this.assistPeoper = assistPeoper;
    }

    public String getAssistContent() {
        return assistContent;
    }

    public void setAssistContent(String assistContent) {
        this.assistContent = assistContent;
    }

    public String getAssistDate() {
        return assistDate;
    }

    public void setAssistDate(String assistDate) {
        this.assistDate = assistDate;
    }

    public String getMoneyModule() {
        return moneyModule;
    }

    public void setMoneyModule(String moneyModule) {
        this.moneyModule = moneyModule;
    }

    public String getMoneyModuleOpinion() {
        return moneyModuleOpinion;
    }

    public void setMoneyModuleOpinion(String moneyModuleOpinion) {
        this.moneyModuleOpinion = moneyModuleOpinion;
    }

    public String getGeneralManager() {
        return generalManager;
    }

    public void setGeneralManager(String generalManager) {
        this.generalManager = generalManager;
    }

    public String getApprovalExam() {
        return approvalExam;
    }

    public void setApprovalExam(String approvalExam) {
        this.approvalExam = approvalExam;
    }

    public Boolean getConfirm() {
        return confirm;
    }

    public void setConfirm(Boolean confirm) {
        this.confirm = confirm;
    }
}