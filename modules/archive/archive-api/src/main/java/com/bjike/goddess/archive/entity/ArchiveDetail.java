package com.bjike.goddess.archive.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 档案明细
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 02:05 ]
 * @Description: [ 档案明细 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "archive_detail")
public class ArchiveDetail extends BaseEntity {

    /**
     * 姓名
     */
    @Column(name = "username", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '姓名'", unique = true)
    private String username;

    /**
     * 技能等级
     */
    @Column(name = "skill", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '技能等级'")
    private String skill;

    /**
     * 管理等级
     */
    @Column(name = "manage", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '管理等级'")
    private String manage;

    /**
     * 处罚
     */
    @Column(name = "push", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '处罚'")
    private String push;

    /**
     * 奖励
     */
    @Column(name = "reward", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '奖励'")
    private String reward;

    /**
     * 身份证有效期
     */
    @Column(name = "identityCardTerm", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '身份证有效期'")
    private String identityCardTerm;

    /**
     * 劳动合同
     */
    @Column(name = "is_contractLabor", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '劳动合同'", insertable = false)
    private Boolean contractLabor;

    /**
     * 培训协议
     */
    @Column(name = "is_learnerShip", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '培训协议'", insertable = false)
    private Boolean learnerShip;

    /**
     * 保密协议
     */
    @Column(name = "is_secrecyAgreement", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '保密协议'", insertable = false)
    private Boolean secrecyAgreement;

    /**
     * 竞业协议
     */
    @Column(name = "is_protocol", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '竞业协议'", insertable = false)
    private Boolean protocol;

    /**
     * 简历
     */
    @Column(name = "is_resume", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '简历'", insertable = false)
    private Boolean resume;

    /**
     * 照片
     */
    @Column(name = "is_photo", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '照片'", insertable = false)
    private Boolean photo;

    /**
     * 身份证
     */
    @Column(name = "is_identityCard", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '身份证'", insertable = false)
    private Boolean identityCard;

    /**
     * 学历证
     */
    @Column(name = "is_education", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '学历证'", insertable = false)
    private Boolean education;

    /**
     * 银行卡
     */
    @Column(name = "is_bankCard", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '银行卡'", insertable = false)
    private Boolean bankCard;

    /**
     * 求职登记表
     */
    @Column(name = "is_rollCall", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '求职登记表'", insertable = false)
    private Boolean rollCall;

    /**
     * 入职登记表
     */
    @Column(name = "is_entryCall", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '入职登记表'", insertable = false)
    private Boolean entryCall;

    /**
     * 面谈确认单
     */
    @Column(name = "is_interviewConfirmation", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '面谈确认单'", insertable = false)
    private Boolean interviewConfirmation;

    /**
     * 转正审批表
     */
    @Column(name = "is_positiveApproval", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '转正审批表'", insertable = false)
    private Boolean positiveApproval;

    /**
     * 资格证书
     */
    @Column(name = "is_qualification", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '资格证书'", insertable = false)
    private Boolean qualification;

    /**
     * 存放路径
     */
    @Column(name = "storage", columnDefinition = "VARCHAR(255)   COMMENT '存放路径'")
    private String storage;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
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