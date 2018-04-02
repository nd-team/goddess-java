package com.bjike.goddess.lendreimbursement.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 审核详情
 *
 * @Author: [ wany ]
 * @Date: [ 2018-03-02 09:39 ]
 * @Description: [ 审核详情 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "lendreimbursement_auditing")
public class Auditing extends BaseEntity {

    /**
     * 职位名称
     */
    @Column(name = "position", columnDefinition = "VARCHAR(255)   COMMENT '职位名称'")
    private String position;

    /**
     * 审核人
     */
    @Column(name = "auditor", columnDefinition = "VARCHAR(255)   COMMENT '审核人'")
    private String auditor;

    /**
     * 审核意见
     */
    @Column(name = "opinion", columnDefinition = "VARCHAR(255)   COMMENT '审核意见'")
    private String opinion;

    /**
     * 审核时间
     */
    @Column(name = "time", columnDefinition = "VARCHAR(255)   COMMENT '审核时间'")
    private String time;

    /**
     * 是否通过
     */
    @Column(name = "", columnDefinition = "VARCHAR(255)   COMMENT '是否通过'")
    private boolean pass;

    @Column(name = "reimbursement", columnDefinition = "VARCHAR(255)   COMMENT '报销金额'")
    private double reimbursement;

    @Column(name = "sendBack", columnDefinition = "VARCHAR(255)   COMMENT '回退金额'")
    private double sendBack;

    @Column(name = "borrowMoney", columnDefinition = "VARCHAR(255)   COMMENT '借款金额'")
    private double borrowMoney;

    @Column(name = "returnDate", columnDefinition = "VARCHAR(255)   COMMENT '归还日期'")
    private String returnDate;

    @Column(name = "restitution", columnDefinition = "VARCHAR(255)   COMMENT '归还方式'")
    private String restitution;

    @Column(name = "account", columnDefinition = "VARCHAR(255)   COMMENT '归还账户'")
    private String account;

    @Column(name = "auditorModule", columnDefinition = "VARCHAR(255)   COMMENT '审核人模块'")
    private String auditorModule;

    @Column(name = "verification", columnDefinition = "VARCHAR(255)   COMMENT '核对情况'")
    private String verification;

    @Column(name = "ticketDate", columnDefinition = "VARCHAR(255)   COMMENT '收票日期'")
    private String ticketDate;

    private String auditing;

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }

    public String getTicketDate() {
        return ticketDate;
    }

    public void setTicketDate(String ticketDate) {
        this.ticketDate = ticketDate;
    }

    public String getAuditorModule() {
        return auditorModule;
    }

    public void setAuditorModule(String auditorModule) {
        this.auditorModule = auditorModule;
    }

    public double getReimbursement() {
        return reimbursement;
    }

    public void setReimbursement(double reimbursement) {
        this.reimbursement = reimbursement;
    }

    public double getSendBack() {
        return sendBack;
    }

    public void setSendBack(double sendBack) {
        this.sendBack = sendBack;
    }

    public double getBorrowMoney() {
        return borrowMoney;
    }

    public void setBorrowMoney(double borrowMoney) {
        this.borrowMoney = borrowMoney;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getRestitution() {
        return restitution;
    }

    public void setRestitution(String restitution) {
        this.restitution = restitution;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAuditing() {
        return auditing;
    }

    public void setAuditing(String auditing) {
        this.auditing = auditing;
    }

    public boolean isPass() {
        return pass;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean getPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "Auditing{" +
                "position='" + position + '\'' +
                ", auditor='" + auditor + '\'' +
                ", opinion='" + opinion + '\'' +
                ", time='" + time + '\'' +
                ", pass=" + pass +
                ", reimbursement=" + reimbursement +
                ", sendBack=" + sendBack +
                ", borrowMoney=" + borrowMoney +
                ", returnDate='" + returnDate + '\'' +
                ", restitution='" + restitution + '\'' +
                ", account='" + account + '\'' +
                ", auditorModule='" + auditorModule + '\'' +
                ", auditing='" + auditing + '\'' +
                '}';
    }
}