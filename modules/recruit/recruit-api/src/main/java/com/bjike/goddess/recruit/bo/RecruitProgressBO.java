package com.bjike.goddess.recruit.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 招聘管理进度汇总
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-30 03:52 ]
 * @Description: [ 招聘管理进度汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RecruitProgressBO extends BaseBO {
    /**
     * 部门/项目组
     */
    private String projectGroup;

    /**
     * 招聘渠道开发量
     */
    private Integer channelsNum;

    /**
     * 有资金(招聘费)准备数
     */
    private Integer moneyReadyNum;

    /**
     * 人口缺口隐患(待离职人员)
     */
    private Integer populationGapNum;

    /**
     * 已完成招聘人数
     */
    private Integer completeNum;

    /**
     * 计划筛选简历数
     */
    private Integer planScreenNum;

    /**
     * 实际筛选简历数
     */
    private Integer realityScreenNum;
    /**
     * 下载简历数差异
     */
    private Integer downloadBalance;

    /**
     * 电访数量
     */
    private Integer phoneNum;

    /**
     * 预约面试量
     */
    private Integer orderNum;

    /**
     * 计划邀约面试量
     */
    private Integer planInviteNum;

    /**
     * 邀约面试量
     */
    private Integer inviteNum;
    /**
     * 计划面试量
     */
    private Integer planInterviewNum;

    /**
     * 初试面试量
     */
    private Integer firstInterviewNum;

    /**
     * 复试面试量
     */
    private Integer secondInterviewNum;

    /**
     * 薪资面谈量
     */
    private Integer accountInterviewNum;
    /**
     * 计划成功通过面试量
     */
    private Integer planPassNum;
    /**
     * 成功通过面试量
     */
    private Integer successPassNum;
    /**
     * 计划入职人数
     */
    private Integer planEntryNum;
    /**
     * 入职量
     */
    private Integer entryNum;
    /**
     * 面试率(%)
     */
    private Double interviewRate;
    /**
     * 入职率(%)
     */
    private Double inductionRate;

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public Integer getChannelsNum() {
        return channelsNum;
    }

    public void setChannelsNum(Integer channelsNum) {
        this.channelsNum = channelsNum;
    }

    public Integer getMoneyReadyNum() {
        return moneyReadyNum;
    }

    public void setMoneyReadyNum(Integer moneyReadyNum) {
        this.moneyReadyNum = moneyReadyNum;
    }

    public Integer getPopulationGapNum() {
        return populationGapNum;
    }

    public void setPopulationGapNum(Integer populationGapNum) {
        this.populationGapNum = populationGapNum;
    }

    public Integer getCompleteNum() {
        return completeNum;
    }

    public void setCompleteNum(Integer completeNum) {
        this.completeNum = completeNum;
    }

    public Integer getPlanScreenNum() {
        return planScreenNum;
    }

    public void setPlanScreenNum(Integer planScreenNum) {
        this.planScreenNum = planScreenNum;
    }

    public Integer getRealityScreenNum() {
        return realityScreenNum;
    }

    public void setRealityScreenNum(Integer realityScreenNum) {
        this.realityScreenNum = realityScreenNum;
    }

    public Integer getDownloadBalance() {
        return downloadBalance;
    }

    public void setDownloadBalance(Integer downloadBalance) {
        this.downloadBalance = downloadBalance;
    }

    public Integer getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(Integer phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getPlanInviteNum() {
        return planInviteNum;
    }

    public void setPlanInviteNum(Integer planInviteNum) {
        this.planInviteNum = planInviteNum;
    }

    public Integer getInviteNum() {
        return inviteNum;
    }

    public void setInviteNum(Integer inviteNum) {
        this.inviteNum = inviteNum;
    }

    public Integer getPlanInterviewNum() {
        return planInterviewNum;
    }

    public void setPlanInterviewNum(Integer planInterviewNum) {
        this.planInterviewNum = planInterviewNum;
    }

    public Integer getFirstInterviewNum() {
        return firstInterviewNum;
    }

    public void setFirstInterviewNum(Integer firstInterviewNum) {
        this.firstInterviewNum = firstInterviewNum;
    }

    public Integer getSecondInterviewNum() {
        return secondInterviewNum;
    }

    public void setSecondInterviewNum(Integer secondInterviewNum) {
        this.secondInterviewNum = secondInterviewNum;
    }

    public Integer getAccountInterviewNum() {
        return accountInterviewNum;
    }

    public void setAccountInterviewNum(Integer accountInterviewNum) {
        this.accountInterviewNum = accountInterviewNum;
    }

    public Integer getPlanPassNum() {
        return planPassNum;
    }

    public void setPlanPassNum(Integer planPassNum) {
        this.planPassNum = planPassNum;
    }

    public Integer getSuccessPassNum() {
        return successPassNum;
    }

    public void setSuccessPassNum(Integer successPassNum) {
        this.successPassNum = successPassNum;
    }

    public Integer getPlanEntryNum() {
        return planEntryNum;
    }

    public void setPlanEntryNum(Integer planEntryNum) {
        this.planEntryNum = planEntryNum;
    }

    public Integer getEntryNum() {
        return entryNum;
    }

    public void setEntryNum(Integer entryNum) {
        this.entryNum = entryNum;
    }

    public Double getInterviewRate() {
        return interviewRate;
    }

    public void setInterviewRate(Double interviewRate) {
        this.interviewRate = interviewRate;
    }

    public Double getInductionRate() {
        return inductionRate;
    }

    public void setInductionRate(Double inductionRate) {
        this.inductionRate = inductionRate;
    }
}