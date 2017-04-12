package com.bjike.goddess.archive.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 档案明细
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 02:05 ]
 * @Description: [ 档案明细 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ArchiveDetailTO extends BaseTO {

    /**
     * 姓名
     */
    private String username;

    /**
     * 技能等级
     */
    private String skill;

    /**
     * 管理等级
     */
    private String manage;

    /**
     * 处罚
     */
    private String push;

    /**
     * 奖励
     */
    private String reward;

    /**
     * 身份证有效期
     */
    private String identityCardTerm;

    /**
     * 劳动合同
     */
    private Boolean contractLabor;

    /**
     * 培训协议
     */
    private Boolean learnerShip;

    /**
     * 保密协议
     */
    private Boolean secrecyAgreement;

    /**
     * 竞业协议
     */
    private Boolean protocol;

    /**
     * 简历
     */
    private Boolean resume;

    /**
     * 照片
     */
    private Boolean photo;

    /**
     * 身份证
     */
    private Boolean identityCard;

    /**
     * 学历证
     */
    private Boolean education;

    /**
     * 银行卡
     */
    private Boolean bankCard;

    /**
     * 求职登记表
     */
    private Boolean rollCall;

    /**
     * 入职登记表
     */
    private Boolean entryCall;

    /**
     * 面谈确认单
     */
    private Boolean interviewConfirmation;

    /**
     * 转正审批表
     */
    private Boolean positiveApproval;

    /**
     * 资格证书
     */
    private Boolean qualification;

    /**
     * 存放路径
     */
    private String storage;

    /**
     * 备注
     */
    private String remark;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getManage() {
        return manage;
    }

    public void setManage(String manage) {
        this.manage = manage;
    }

    public String getPush() {
        return push;
    }

    public void setPush(String push) {
        this.push = push;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getIdentityCardTerm() {
        return identityCardTerm;
    }

    public void setIdentityCardTerm(String identityCardTerm) {
        this.identityCardTerm = identityCardTerm;
    }

    public Boolean getContractLabor() {
        return contractLabor;
    }

    public void setContractLabor(Boolean contractLabor) {
        this.contractLabor = contractLabor;
    }

    public Boolean getLearnerShip() {
        return learnerShip;
    }

    public void setLearnerShip(Boolean learnerShip) {
        this.learnerShip = learnerShip;
    }

    public Boolean getSecrecyAgreement() {
        return secrecyAgreement;
    }

    public void setSecrecyAgreement(Boolean secrecyAgreement) {
        this.secrecyAgreement = secrecyAgreement;
    }

    public Boolean getProtocol() {
        return protocol;
    }

    public void setProtocol(Boolean protocol) {
        this.protocol = protocol;
    }

    public Boolean getResume() {
        return resume;
    }

    public void setResume(Boolean resume) {
        this.resume = resume;
    }

    public Boolean getPhoto() {
        return photo;
    }

    public void setPhoto(Boolean photo) {
        this.photo = photo;
    }

    public Boolean getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(Boolean identityCard) {
        this.identityCard = identityCard;
    }

    public Boolean getEducation() {
        return education;
    }

    public void setEducation(Boolean education) {
        this.education = education;
    }

    public Boolean getBankCard() {
        return bankCard;
    }

    public void setBankCard(Boolean bankCard) {
        this.bankCard = bankCard;
    }

    public Boolean getRollCall() {
        return rollCall;
    }

    public void setRollCall(Boolean rollCall) {
        this.rollCall = rollCall;
    }

    public Boolean getEntryCall() {
        return entryCall;
    }

    public void setEntryCall(Boolean entryCall) {
        this.entryCall = entryCall;
    }

    public Boolean getInterviewConfirmation() {
        return interviewConfirmation;
    }

    public void setInterviewConfirmation(Boolean interviewConfirmation) {
        this.interviewConfirmation = interviewConfirmation;
    }

    public Boolean getPositiveApproval() {
        return positiveApproval;
    }

    public void setPositiveApproval(Boolean positiveApproval) {
        this.positiveApproval = positiveApproval;
    }

    public Boolean getQualification() {
        return qualification;
    }

    public void setQualification(Boolean qualification) {
        this.qualification = qualification;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}