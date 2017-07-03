package com.bjike.goddess.lendreimbursement.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.lendreimbursement.enums.LendStatus;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 申请借款
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-06 10:01 ]
 * @Description: [ 申请借款 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "lendreimbursement_applylend")
public class ApplyLend extends BaseEntity {

    /**
     * 预计借款日期
     */
    @Column(name = "estimateLendDate", columnDefinition = "DATE   COMMENT '预计借款日期'")
    private LocalDate estimateLendDate;

    /**
     * 借款人
     */
    @Column(name = "lender",  columnDefinition = "VARCHAR(255)   COMMENT '借款人'")
    private String lender;

    /**
     * 负责人
     */
    @Column(name = "charger",  columnDefinition = "VARCHAR(255)   COMMENT '负责人'")
    private String charger;

    /**
     * 地区
     */
    @Column(name = "area",  columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组
     */
    @Column(name = "projectGroup",  columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String projectGroup;

    /**
     * 项目名称
     */
    @Column(name = "projectName",  columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String projectName;

    /**
     * 参与人员
     */
    @Column(name = "attender",  columnDefinition = "VARCHAR(255)   COMMENT '参与人员'")
    private String attender;

    /**
     * 借款方式
     */
    @Column(name = "lendWay",  columnDefinition = "VARCHAR(255)   COMMENT '借款方式'")
    private String lendWay;

    /**
     * 一级科目
     */
    @Column(name = "firstSubject",  columnDefinition = "VARCHAR(255)   COMMENT '一级科目'")
    private String firstSubject;

    /**
     * 二级科目
     */
    @Column(name = "secondSubject",  columnDefinition = "VARCHAR(255)   COMMENT '二级科目'")
    private String secondSubject;

    /**
     * 三级科目
     */
    @Column(name = "thirdSubject",  columnDefinition = "VARCHAR(255)   COMMENT '三级科目'")
    private String thirdSubject;

    /**
     * 说明
     */
    @Column(name = "explains",  columnDefinition = "VARCHAR(255)   COMMENT '说明'")
    private String explains;

    /**
     * 是否补写(是/否)
     */
    @Column(name = "writeUp",  columnDefinition = "VARCHAR(255)   COMMENT '是否补写（是/否）'")
    private String writeUp;

    /**
     * 补写情况
     */
    @Column(name = "writeUpCondition",  columnDefinition = "VARCHAR(255)   COMMENT '补写情况'")
    private String writeUpCondition;

    /**
     * 借款事由
     */
    @Column(name = "lendReson",  columnDefinition = "VARCHAR(255)   COMMENT '借款事由'")
    private String lendReson;

    /**
     * 金额
     */
    @Column(name = "money", columnDefinition = "DECIMAL(10,2)   COMMENT '金额'")
    private Double money;

    /**
     * 是否有发票（是/否）
     */
    @Column(name = "invoice",  columnDefinition = "VARCHAR(255)   COMMENT '是否有发票（是/否）'")
    private String invoice;

    /**
     * 无发票备注
     */
    @Column(name = "noInvoiceRemark",  columnDefinition = "VARCHAR(255)   COMMENT '无发票备注'")
    private String noInvoiceRemark;

    /**
     * 商品链接
     */
    @Column(name = "goodsLink",  columnDefinition = "VARCHAR(255)   COMMENT '商品链接'")
    private String goodsLink;

    /**
     * 备注
     */
    @Column(name = "remark",  columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    /**
     * 填单人
     */
    @Column(name = "fillSingler",  columnDefinition = "VARCHAR(255)   COMMENT '填单人'")
    private String fillSingler;

    /**
     * 借款日期
     */
    @Column(name = "lendDate",  columnDefinition = "DATE   COMMENT '借款日期'")
    private LocalDate lendDate;

    /**
     * 负责人审核意见
     */
    @Column(name = "chargerOpinion", columnDefinition = "VARCHAR(255)   COMMENT '负责人审核意见'")
    private String chargerOpinion;

    /**
     * 负责人是否通过（是/否）
     */
    @Column(name = "chargerPass",  columnDefinition = "VARCHAR(255)   COMMENT '负责人是否通过（是/否）'")
    private String chargerPass;

    /**
     * 财务运营部
     */
    @Column(name = "finacer",  columnDefinition = "VARCHAR(255)   COMMENT '财务运营部'")
    private String finacer;

    /**
     * 财务运营部审核意见
     */
    @Column(name = "fincerOpinion", columnDefinition = "VARCHAR(255)   COMMENT '财务运营部审核意见'")
    private String fincerOpinion;

    /**
     * 财务运营部是否通过（是/否）
     */
    @Column(name = "fincerPass", columnDefinition = "VARCHAR(255)   COMMENT '财务运营部是否通过（是/否）'")
    private String fincerPass;

    /**
     * 总经办
     */
    @Column(name = "manager",  columnDefinition = "VARCHAR(255)   COMMENT '总经办'")
    private String manager;

    /**
     * 总经办审核意见
     */
    @Column(name = "managerOpinion",  columnDefinition = "VARCHAR(255)   COMMENT '总经办审核意见'")
    private String managerOpinion;

    /**
     * 总经办是否通过（是/否）
     */
    @Column(name = "managerPass",  columnDefinition = "VARCHAR(255)   COMMENT '总经办是否通过（是/否）'")
    private String managerPass;

    /**
     * 代理审核备注
     */
    @Column(name = "proxyAuditRemark",  columnDefinition = "VARCHAR(255)   COMMENT '代理审核备注'")
    private String proxyAuditRemark;

    /**
     * 是否付款（是/否）
     */
    @Column(name = "payCondition",  columnDefinition = "VARCHAR(255)   COMMENT '是否付款（是/否）'")
    private String payCondition;

    /**
     * 付款人
     */
    @Column(name = "payer",  columnDefinition = "VARCHAR(255)   COMMENT '付款人'")
    private String payer;

    /**
     * 付款日期
     */
    @Column(name = "payDate",  columnDefinition = "DATE   COMMENT '付款日期'")
    private LocalDate payDate;

    /**
     * 支付来源
     */
    @Column(name = "payOrigin",  columnDefinition = "VARCHAR(255)   COMMENT '支付来源'")
    private String payOrigin;

    /**
     * 单据数量
     */
    @Column(name = "documentQuantity",  columnDefinition = "DECIMAL(10,2)   COMMENT '单据数量'")
    private Double documentQuantity;

    /**
     * 是否收到单据（是/否）
     */
    @Column(name = "documentCondition",  columnDefinition = "VARCHAR(255)   COMMENT '是否收到单据（是/否）'")
    private String documentCondition;

    /**
     * 报销金额
     */
    @Column(name = "reimMoney", columnDefinition = "DECIMAL(10,2)   COMMENT '报销金额'")
    private Double reimMoney;

    /**
     * 借款金额
     */
    @Column(name = "lendMoney",  columnDefinition = "DECIMAL(10,2)   COMMENT '借款金额'")
    private Double lendMoney;

    /**
     * 退回金额
     */
    @Column(name = "returnMoney",  columnDefinition = "DECIMAL(10,2)   COMMENT '退回金额'")
    private Double returnMoney;

    /**
     * 退回日期
     */
    @Column(name = "returnDate",  columnDefinition = "DATE   COMMENT '退回日期'")
    private LocalDate returnDate;

    /**
     * 归还方式
     */
    @Column(name = "returnWays", columnDefinition = "VARCHAR(255)   COMMENT '归还方式'")
    private String returnWays;

    /**
     * 归还账户
     */
    @Column(name = "returnAccount",  columnDefinition = "VARCHAR(255)   COMMENT '归还账户'")
    private String returnAccount;

    /**
     * 寄件人
     */
    @Column(name = "sender", columnDefinition = "VARCHAR(255)   COMMENT '寄件人'")
    private String sender;

    /**
     * 寄件日期
     */
    @Column(name = "sendDate",  columnDefinition = "DATE   COMMENT '寄件日期'")
    private LocalDate sendDate;

    /**
     * 寄件情况
     */
    @Column(name = "sendCondition",  columnDefinition = "VARCHAR(255)   COMMENT '寄件情况'")
    private String sendCondition;

    /**
     * 收件地址
     */
    @Column(name = "receiveAddr",  columnDefinition = "VARCHAR(255)   COMMENT '收件地址'")
    private String receiveAddr;

    /**
     * 收票人
     */
    @Column(name = "ticketer",  columnDefinition = "VARCHAR(255)   COMMENT '收票人'")
    private String ticketer;

    /**
     * 收票日期
     */
    @Column(name = "ticketDate",  columnDefinition = "DATE  COMMENT '收票日期'")
    private LocalDate ticketDate;

    /**
     * 收票情况
     */
    @Column(name = "ticketCondition",  columnDefinition = "VARCHAR(255)   COMMENT '收票情况'")
    private String ticketCondition;

    /**
     * 是否已收票（是/否）
     */
    @Column(name = "receiveTicket",  columnDefinition = "VARCHAR(255)   COMMENT '是否已收票（是/否）'")
    private String receiveTicket;


    /**
     * 核对人
     */
    @Column(name = "checker",  columnDefinition = "VARCHAR(255)   COMMENT '核对人'")
    private String checker;

    /**
     * 核对日期
     */
    @Column(name = "checkDate",  columnDefinition = "DATE   COMMENT '核对日期'")
    private LocalDate checkDate;

    /**
     * 核对内容
     */
    @Column(name = "checkcontent",  columnDefinition = "VARCHAR(255)   COMMENT '核对内容'")
    private String checkcontent;

    /**
     * 状态
     */
    @Column(name = "lendStatus",  columnDefinition = "INT(2)   COMMENT '状态'")
    private LendStatus lendStatus;

    /**
     * 申请单有误状态标识
     */
    @Column(name = "lendError",  columnDefinition = "INT(2)   COMMENT '申请单有误状态标识'")
    private Integer lendError;

    /**
     * 是否已收款（是/否）
     */
    @Column(name = "receivePay",  columnDefinition = "VARCHAR(255)   COMMENT '是否已收款（是/否）'")
    private String receivePay;

    /**
     * 枚举辅助
     */
    @Transient
    private Integer transientLendStatus;


    public LocalDate getEstimateLendDate() {
        return estimateLendDate;
    }

    public void setEstimateLendDate(LocalDate estimateLendDate) {
        this.estimateLendDate = estimateLendDate;
    }

    public String getLender() {
        return lender;
    }

    public void setLender(String lender) {
        this.lender = lender;
    }

    public String getCharger() {
        return charger;
    }

    public void setCharger(String charger) {
        this.charger = charger;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getAttender() {
        return attender;
    }

    public void setAttender(String attender) {
        this.attender = attender;
    }

    public String getLendWay() {
        return lendWay;
    }

    public void setLendWay(String lendWay) {
        this.lendWay = lendWay;
    }

    public String getFirstSubject() {
        return firstSubject;
    }

    public void setFirstSubject(String firstSubject) {
        this.firstSubject = firstSubject;
    }

    public String getSecondSubject() {
        return secondSubject;
    }

    public void setSecondSubject(String secondSubject) {
        this.secondSubject = secondSubject;
    }

    public String getThirdSubject() {
        return thirdSubject;
    }

    public void setThirdSubject(String thirdSubject) {
        this.thirdSubject = thirdSubject;
    }

    public String getExplains() {
        return explains;
    }

    public void setExplains(String explains) {
        this.explains = explains;
    }

    public String getWriteUp() {
        return writeUp;
    }

    public void setWriteUp(String writeUp) {
        this.writeUp = writeUp;
    }

    public String getWriteUpCondition() {
        return writeUpCondition;
    }

    public void setWriteUpCondition(String writeUpCondition) {
        this.writeUpCondition = writeUpCondition;
    }

    public String getLendReson() {
        return lendReson;
    }

    public void setLendReson(String lendReson) {
        this.lendReson = lendReson;
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

    public String getNoInvoiceRemark() {
        return noInvoiceRemark;
    }

    public void setNoInvoiceRemark(String noInvoiceRemark) {
        this.noInvoiceRemark = noInvoiceRemark;
    }

    public String getGoodsLink() {
        return goodsLink;
    }

    public void setGoodsLink(String goodsLink) {
        this.goodsLink = goodsLink;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFillSingler() {
        return fillSingler;
    }

    public void setFillSingler(String fillSingler) {
        this.fillSingler = fillSingler;
    }

    public LocalDate getLendDate() {
        return lendDate;
    }

    public void setLendDate(LocalDate lendDate) {
        this.lendDate = lendDate;
    }

    public String getChargerOpinion() {
        return chargerOpinion;
    }

    public void setChargerOpinion(String chargerOpinion) {
        this.chargerOpinion = chargerOpinion;
    }

    public String getChargerPass() {
        return chargerPass;
    }

    public void setChargerPass(String chargerPass) {
        this.chargerPass = chargerPass;
    }

    public String getFinacer() {
        return finacer;
    }

    public void setFinacer(String finacer) {
        this.finacer = finacer;
    }

    public String getFincerOpinion() {
        return fincerOpinion;
    }

    public void setFincerOpinion(String fincerOpinion) {
        this.fincerOpinion = fincerOpinion;
    }

    public String getFincerPass() {
        return fincerPass;
    }

    public void setFincerPass(String fincerPass) {
        this.fincerPass = fincerPass;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getManagerOpinion() {
        return managerOpinion;
    }

    public void setManagerOpinion(String managerOpinion) {
        this.managerOpinion = managerOpinion;
    }

    public String getManagerPass() {
        return managerPass;
    }

    public void setManagerPass(String managerPass) {
        this.managerPass = managerPass;
    }

    public String getProxyAuditRemark() {
        return proxyAuditRemark;
    }

    public void setProxyAuditRemark(String proxyAuditRemark) {
        this.proxyAuditRemark = proxyAuditRemark;
    }

    public String getPayCondition() {
        return payCondition;
    }

    public void setPayCondition(String payCondition) {
        this.payCondition = payCondition;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public LocalDate getPayDate() {
        return payDate;
    }

    public void setPayDate(LocalDate payDate) {
        this.payDate = payDate;
    }

    public String getPayOrigin() {
        return payOrigin;
    }

    public void setPayOrigin(String payOrigin) {
        this.payOrigin = payOrigin;
    }

    public Double getDocumentQuantity() {
        return documentQuantity;
    }

    public void setDocumentQuantity(Double documentQuantity) {
        this.documentQuantity = documentQuantity;
    }

    public String getDocumentCondition() {
        return documentCondition;
    }

    public void setDocumentCondition(String documentCondition) {
        this.documentCondition = documentCondition;
    }

    public Double getReimMoney() {
        return reimMoney;
    }

    public void setReimMoney(Double reimMoney) {
        this.reimMoney = reimMoney;
    }

    public Double getLendMoney() {
        return lendMoney;
    }

    public void setLendMoney(Double lendMoney) {
        this.lendMoney = lendMoney;
    }

    public Double getReturnMoney() {
        return returnMoney;
    }

    public void setReturnMoney(Double returnMoney) {
        this.returnMoney = returnMoney;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public String getReturnWays() {
        return returnWays;
    }

    public void setReturnWays(String returnWays) {
        this.returnWays = returnWays;
    }

    public String getReturnAccount() {
        return returnAccount;
    }

    public void setReturnAccount(String returnAccount) {
        this.returnAccount = returnAccount;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public LocalDate getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDate sendDate) {
        this.sendDate = sendDate;
    }

    public String getSendCondition() {
        return sendCondition;
    }

    public void setSendCondition(String sendCondition) {
        this.sendCondition = sendCondition;
    }

    public String getReceiveAddr() {
        return receiveAddr;
    }

    public void setReceiveAddr(String receiveAddr) {
        this.receiveAddr = receiveAddr;
    }

    public String getTicketer() {
        return ticketer;
    }

    public void setTicketer(String ticketer) {
        this.ticketer = ticketer;
    }

    public LocalDate getTicketDate() {
        return ticketDate;
    }

    public void setTicketDate(LocalDate ticketDate) {
        this.ticketDate = ticketDate;
    }

    public String getTicketCondition() {
        return ticketCondition;
    }

    public void setTicketCondition(String ticketCondition) {
        this.ticketCondition = ticketCondition;
    }

    public String getReceiveTicket() {
        return receiveTicket;
    }

    public void setReceiveTicket(String receiveTicket) {
        this.receiveTicket = receiveTicket;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    public LocalDate getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(LocalDate checkDate) {
        this.checkDate = checkDate;
    }

    public String getCheckcontent() {
        return checkcontent;
    }

    public void setCheckcontent(String checkcontent) {
        this.checkcontent = checkcontent;
    }

    public LendStatus getLendStatus() {
        return lendStatus;
    }

    public void setLendStatus(LendStatus lendStatus) {
        this.lendStatus = lendStatus;
    }

    public Integer getLendError() {
        return lendError;
    }

    public void setLendError(Integer lendError) {
        this.lendError = lendError;
    }

    public String getReceivePay() {
        return receivePay;
    }

    public void setReceivePay(String receivePay) {
        this.receivePay = receivePay;
    }

    public Integer getTransientLendStatus() {
        return transientLendStatus;
    }

    public void setTransientLendStatus(Integer transientLendStatus) {
        this.transientLendStatus = transientLendStatus;
    }
}