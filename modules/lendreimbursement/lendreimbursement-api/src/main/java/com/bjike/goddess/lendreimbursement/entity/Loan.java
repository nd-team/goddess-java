package com.bjike.goddess.lendreimbursement.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


/**
 * 借款
 *
 * @Author: [ wany ]
 * @Date: [ 2018-03-01 05:48 ]
 * @Description: [ 借款 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "lendreimbursement_loan")
public class Loan extends BaseEntity {

    /**
     * 预计借款时间
     */
    @Column(name = "estimateTime", columnDefinition = "VARCHAR(255)   COMMENT '预计借款时间'")
    private String estimateTime;

    /**
     * 借款人
     */
    @Column(name = "borrower", columnDefinition = "VARCHAR(255)   COMMENT '借款人'")
    private String borrower;

    /**
     * 负责人
     */
    @Column(name = "charge", columnDefinition = "VARCHAR(255)   COMMENT '负责人'")
    private String charge;

    /**
     * 参与人
     */
    @Column(name = "participants", columnDefinition = "VARCHAR(255)   COMMENT '参与人'")
    private String participants;

    /**
     * 地区
     */
    @Column(name = "area", columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组
     */
    @Column(name = "project", columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String project;

    /**
     * 项目名称
     */
    @Column(name = "projectName", columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String projectName;

    /**
     * 借款方式
     */
    @Column(name = "wayOfBorrowing", columnDefinition = "VARCHAR(255)   COMMENT '借款方式'")
    private String wayOfBorrowing;

//    /**
//     * 对赌总比例
//     */
//    @Column(name = "proportionOG", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '对赌总比例'")
//    private String proportionOG;

    /**
     * 一级科目
     */
    @Column(name = "classA", columnDefinition = "VARCHAR(255)   COMMENT '一级科目'")
    private String classA;

    /**
     * 二级科目
     */
    @Column(name = "classB", columnDefinition = "VARCHAR(255)   COMMENT '二级科目'")
    private String classB;

    /**
     * 三级科目
     */
    @Column(name = "classC", columnDefinition = "VARCHAR(255)   COMMENT '三级科目'")
    private String classC;

    /**
     * 是否补写
     */
    @Column(name = "", columnDefinition = "VARCHAR(255)   COMMENT '是否补写'")
    private boolean writeUp;

    /**
     * 借款事由
     */
    @Column(name = "reason", columnDefinition = "VARCHAR(255)   COMMENT '借款事由'")
    private String reason;

    /**
     * 金额
     */
    @Column(name = "money", columnDefinition = "DECIMAL(10,2)   COMMENT '金额'")
    private Double money;

    /**
     * 是否有发票
     */
    @Column(name = "invoice", columnDefinition = "VARCHAR(255)   COMMENT '是否有发票'")
    private String invoice;

    /**
     * 无发票备注
     */
    @Column(name = "noInvoice", columnDefinition = "VARCHAR(255)   COMMENT '无发票备注'")
    private String noInvoice;

    /**
     * 是否有准备金
     */
    @Column(name = "", columnDefinition = "VARCHAR(255)   COMMENT '是否有准备金'")
    private boolean readyMoney;

    /**
     * 商品连接
     */
    @Column(name = "link", columnDefinition = "VARCHAR(2048)   COMMENT '商品连接'")
    private String link;

    /**
     * 备注
     */
    @Column(name = "remarks", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remarks;

    @Column(name = "lastProgress", columnDefinition = "VARCHAR(255)   COMMENT '上个进度'")
    private String lastProgress;

    @Column(name = "nowProgress", columnDefinition = "VARCHAR(255)   COMMENT '当前进度'")
    private String nowProgress;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "auditing")
    private List<Auditing> auditings;

    @Column(name = "fillTime", columnDefinition = "VARCHAR(255)   COMMENT '填单日期'")
    private String fillTime;

    @Column(name = "user", columnDefinition = "VARCHAR(255)   COMMENT '填单人'")
    private String user;

    @Column(name = "needAnalysis", columnDefinition = "VARCHAR(255)   COMMENT '是否需要分析'")
    private String needAnalysis;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "mailing")
    private Set<Mailing> mailings;

    @Column(name = "status", columnDefinition = "VARCHAR(255)   COMMENT '冻结状态'")
    private String status;

    @Column(name = "payOrigin", columnDefinition = "VARCHAR(255)   COMMENT '支付来源'")
    private String payOrigin;

    @Column(name = "payDate", columnDefinition = "VARCHAR(255)   COMMENT '支付时间'")
    private String payDate;

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getPayOrigin() {
        return payOrigin;
    }

    public void setPayOrigin(String payOrigin) {
        this.payOrigin = payOrigin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Mailing> getMailings() {
        return mailings;
    }

    public void setMailings(Set<Mailing> mailings) {
        this.mailings = mailings;
    }

    public String getNeedAnalysis() {
        return needAnalysis;
    }

    public void setNeedAnalysis(String needAnalysis) {
        this.needAnalysis = needAnalysis;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public boolean isReadyMoney() {
        return readyMoney;
    }

    public String getFillTime() {
        return fillTime;
    }

    public void setFillTime(String fillTime) {
        this.fillTime = fillTime;
    }

    public boolean isWriteUp() {
        return writeUp;
    }

    public String getLastProgress() {
        return lastProgress;
    }

    public void setLastProgress(String lastProgress) {
        this.lastProgress = lastProgress;
    }

    public String getNowProgress() {
        return nowProgress;
    }

    public void setNowProgress(String nowProgress) {
        this.nowProgress = nowProgress;
    }

    public List<Auditing> getAuditings() {
        return auditings;
    }

    public void setAuditings(List<Auditing> auditings) {
        this.auditings = auditings;
    }

    public String getEstimateTime() {
        return estimateTime;
    }

    public void setEstimateTime(String estimateTime) {
        this.estimateTime = estimateTime;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getWayOfBorrowing() {
        return wayOfBorrowing;
    }

    public void setWayOfBorrowing(String wayOfBorrowing) {
        this.wayOfBorrowing = wayOfBorrowing;
    }

//    public String getProportionOG() {
//        return proportionOG;
//    }
//
//    public void setProportionOG(String proportionOG) {
//        this.proportionOG = proportionOG;
//    }

    public String getClassA() {
        return classA;
    }

    public void setClassA(String classA) {
        this.classA = classA;
    }

    public String getClassB() {
        return classB;
    }

    public void setClassB(String classB) {
        this.classB = classB;
    }

    public String getClassC() {
        return classC;
    }

    public void setClassC(String classC) {
        this.classC = classC;
    }

    public boolean getWriteUp() {
        return writeUp;
    }

    public void setWriteUp(boolean writeUp) {
        this.writeUp = writeUp;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getNoInvoice() {
        return noInvoice;
    }

    public void setNoInvoice(String noInvoice) {
        this.noInvoice = noInvoice;
    }

    public boolean getReadyMoney() {
        return readyMoney;
    }

    public void setReadyMoney(boolean readyMoney) {
        this.readyMoney = readyMoney;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "estimateTime='" + estimateTime + '\'' +
                ", borrower='" + borrower + '\'' +
                ", charge='" + charge + '\'' +
                ", participants='" + participants + '\'' +
                ", area='" + area + '\'' +
                ", project='" + project + '\'' +
                ", projectName='" + projectName + '\'' +
                ", wayOfBorrowing='" + wayOfBorrowing + '\'' +
//                ", proportionOG='" + proportionOG + '\'' +
                ", classA='" + classA + '\'' +
                ", classB='" + classB + '\'' +
                ", classC='" + classC + '\'' +
                ", writeUp=" + writeUp +
                ", reason='" + reason + '\'' +
                ", money=" + money +
                ", invoice='" + invoice + '\'' +
                ", noInvoice='" + noInvoice + '\'' +
                ", readyMoney=" + readyMoney +
                ", link='" + link + '\'' +
                ", remarks='" + remarks + '\'' +
                ", lastProgress='" + lastProgress + '\'' +
                ", nowProgress='" + nowProgress + '\'' +
                ", auditings=" + auditings +
                '}';
    }
}