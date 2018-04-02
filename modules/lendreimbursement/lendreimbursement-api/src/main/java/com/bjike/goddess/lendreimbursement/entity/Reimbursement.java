package com.bjike.goddess.lendreimbursement.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


/**
 * 报销
 *
 * @Author: [ wany ]
 * @Date: [ 2018-03-12 03:02 ]
 * @Description: [ 报销 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "lendreimbursement_reimbursement")
public class Reimbursement extends BaseEntity {

    /**
     * 报销发生日期
     */
    @Column(name = "estimateTime", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '报销发生日期'")
    private String estimateTime;

    /**
     * 报销人
     */
    @Column(name = "borrower", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '报销人'")
    private String borrower;

    /**
     * 负责人
     */
    @Column(name = "charge", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '负责人'")
    private String charge;

    /**
     * 参与人
     */
    @Column(name = "participants", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '参与人'")
    private String participants;

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组
     */
    @Column(name = "project", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String project;

    /**
     * 项目名称
     */
    @Column(name = "projectName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String projectName;

    /**
     * 单据数量
     */
    @Column(name = "", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '单据数量'")
    private Integer quantityOfDocuments;

    /**
     * 一级科目
     */
    @Column(name = "classA", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '一级科目'")
    private String classA;

    /**
     * 二级科目
     */
    @Column(name = "classB", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '二级科目'")
    private String classB;

    /**
     * 三级科目
     */
    @Column(name = "classC", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '三级科目'")
    private String classC;

    /**
     * 是否补写
     */
    @Column(name = "", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '是否补写'")
    private boolean writeUp;

    /**
     * 报销当天任务
     */
    @Column(name = "reason", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '报销当天任务'")
    private String reason;

    /**
     * 补充内容
     */
    @Column(name = "supplementaryContent", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '补充内容'")
    private String supplementaryContent;

    /**
     * 金额
     */
    @Column(name = "money", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '金额'")
    private Double money;

    /**
     * 摘要
     */
    @Column(name = "invoice", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '摘要'")
    private String invoice;

    /**
     * 报销人备注
     */
    @Column(name = "noInvoice", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '报销人备注'")
    private String noInvoice;

    /**
     * 是否有发票
     */
    @Column(name = "", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '是否有发票'")
    private boolean readyMoney;

    /**
     * 说明
     */
    @Column(name = "link", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '说明'")
    private String link;

    /**
     * 无发票情况
     */
    @Column(name = "remarks", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '无发票情况'")
    private String remarks;

    @Column(name = "fillTime", columnDefinition = "VARCHAR(255)   COMMENT '填单日期'")
    private String fillTime;

    @Column(name = "user", columnDefinition = "VARCHAR(255)   COMMENT '填单人'")
    private String user;

    @Column(name = "needAnalysis", columnDefinition = "VARCHAR(255)   COMMENT '是否需要分析'")
    private String needAnalysis;

    @Column(name = "lastProgress", columnDefinition = "VARCHAR(255)   COMMENT '上个进度'")
    private String lastProgress;

    @Column(name = "nowProgress", columnDefinition = "VARCHAR(255)   COMMENT '当前进度'")
    private String nowProgress;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "auditing")
    private List<AuditingByReim> auditings;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "mailing")
    private Set<mailingByReim> mailings;

    @Column(name = "status", columnDefinition = "VARCHAR(255)   COMMENT '冻结状态'")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<mailingByReim> getMailings() {
        return mailings;
    }

    public void setMailings(Set<mailingByReim> mailings) {
        this.mailings = mailings;
    }

    public List<AuditingByReim> getAuditings() {
        return auditings;
    }

    public void setAuditings(List<AuditingByReim> auditings) {
        this.auditings = auditings;
    }

    public boolean isWriteUp() {
        return writeUp;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getNeedAnalysis() {
        return needAnalysis;
    }

    public void setNeedAnalysis(String needAnalysis) {
        this.needAnalysis = needAnalysis;
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

    public Integer getQuantityOfDocuments() {
        return quantityOfDocuments;
    }

    public void setQuantityOfDocuments(Integer quantityOfDocuments) {
        this.quantityOfDocuments = quantityOfDocuments;
    }

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

    public String getSupplementaryContent() {
        return supplementaryContent;
    }

    public void setSupplementaryContent(String supplementaryContent) {
        this.supplementaryContent = supplementaryContent;
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
        return "Reimbursement{" +
                "estimateTime='" + estimateTime + '\'' +
                ", borrower='" + borrower + '\'' +
                ", charge='" + charge + '\'' +
                ", participants='" + participants + '\'' +
                ", area='" + area + '\'' +
                ", project='" + project + '\'' +
                ", projectName='" + projectName + '\'' +
                ", quantityOfDocuments=" + quantityOfDocuments +
                ", classA='" + classA + '\'' +
                ", classB='" + classB + '\'' +
                ", classC='" + classC + '\'' +
                ", writeUp=" + writeUp +
                ", reason='" + reason + '\'' +
                ", supplementaryContent='" + supplementaryContent + '\'' +
                ", money=" + money +
                ", invoice='" + invoice + '\'' +
                ", noInvoice='" + noInvoice + '\'' +
                ", readyMoney=" + readyMoney +
                ", link='" + link + '\'' +
                ", remarks='" + remarks + '\'' +
                ", fillTime='" + fillTime + '\'' +
                ", user='" + user + '\'' +
                ", needAnalysis='" + needAnalysis + '\'' +
                ", lastProgress='" + lastProgress + '\'' +
                ", nowProgress='" + nowProgress + '\'' +
                ", auditings=" + auditings +
                '}';
    }
}