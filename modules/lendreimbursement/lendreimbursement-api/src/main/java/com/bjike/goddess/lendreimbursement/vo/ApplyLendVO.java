package com.bjike.goddess.lendreimbursement.vo;

import com.bjike.goddess.lendreimbursement.enums.*;

import java.time.LocalDateTime;

/**
 * 申请借款表现层对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-06 10:01 ]
 * @Description: [ 申请借款表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ApplyLendVO {

    /**
     * id
     */
    private String id;
    /**
     * 预计借款日期
     */
    private String estimateLendDate;

    /**
     * 借款人
     */
    private String lender;

    /**
     * 负责人
     */
    private String charger;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组
     */
    private String projectGroup;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 参与人员
     */
    private String attender;

    /**
     * 借款方式
     */
    private String lendWay;

    /**
     * 一级科目
     */
    private String firstSubject;

    /**
     * 二级科目
     */
    private String secondSubject;

    /**
     * 三级科目
     */
    private String thirdSubject;

    /**
     * 说明
     */
    private String explains;

    /**
     * 是否补写
     */
    private String writeUp;

    /**
     * 补写情况
     */
    private String writeUpCondition;

    /**
     * 借款事由
     */
    private String lendReson;

    /**
     * 金额
     */
    private Double money;

    /**
     * 是否有发票
     */
    private String invoice;

    /**
     * 无发票备注
     */
    private String noInvoiceRemark;

    /**
     * 商品链接
     */
    private String goodsLink;

    /**
     * 备注
     */
    private String remark;

    /**
     * 填单人
     */
    private String fillSingler;

    /**
     * 借款日期(付款后才有，相当于付款日期)
     */
    private String lendDate;

    /**
     * 负责人审核意见
     */
    private String chargerOpinion;

    /**
     * 负责人是否通过
     */
    private String chargerPass;

    /**
     * 负责人审核时间
     */
    private String chargerPassTime;

    /**
     * 财务运营部
     */
    private String finacer;

    /**
     * 财务运营部审核意见
     */
    private String fincerOpinion;

    /**
     * 财务运营部是否通过
     */
    private String fincerPass;

    /**
     * 财务运营部审核时间
     */
    private String fincerPassTime;

    /**
     * 总经办
     */
    private String manager;

    /**
     * 总经办审核意见
     */
    private String managerOpinion;

    /**
     * 总经办是否通过
     */
    private String managerPass;

    /**
     * 总经办审核时间
     */
    private String managerPassTime;

    /**
     * 代理审核备注
     */
    private String proxyAuditRemark;

    /**
     * 是否付款（是/否）
     */
    private String payCondition;

    /**
     * 付款人
     */
    private String payer;

    /**
     * 付款日期
     */
    private String payDate;

    /**
     * 支付来源
     */
    private String payOrigin;

    /**
     * 单据数量
     */
    private Double documentQuantity;

    /**
     * 是否收到单据
     */
    private String documentCondition;

    /**
     * 报销金额
     */
    private Double reimMoney;

    /**
     * 借款金额
     */
    private Double lendMoney;

    /**
     * 退回金额
     */
    private Double returnMoney;

    /**
     * 退回日期
     */
    private String returnDate;

    /**
     * 归还方式
     */
    private String returnWays;

    /**
     * 归还账户
     */
    private String returnAccount;
    /**
     * 还款说明
     */
    private String returnRemark;


    /**
     * 收件人
     */
    private String sendRecevier;

    /**
     * 寄件人
     */
    private String sender;

    /**
     * 寄件日期
     */
    private String sendDate;

    /**
     * 寄件情况
     */
    private String sendCondition;

    /**
     * 收件地区(寄件的时候填的地区)
     */
    private String receiveArea;

    /**
     * 收件地址
     */
    private String receiveAddr;

    /**
     * 收票人
     */
    private String ticketer;

    /**
     * 收票日期
     */
    private String ticketDate;

    /**
     * 收票情况
     */
    private String ticketCondition;

    /**
     * 是否已收票
     */
    private String receiveTicket;

    /**
     * 核对人
     */
    private String checker;

    /**
     * 核对日期
     */
    private String checkDate;

    /**
     * 核对内容
     */
    private String checkcontent;
    /**
     * 还款核对状态
     */
    private LendRetunStatus lendRetunStatus;

    /**
     * 状态
     */
    private LendStatus lendStatus;
    /**
     * 申请单有误状态标识
     */
    private Integer lendError;
    /**
     * 是否已收款（是/否）
     */
    private String receivePay;
    /**
     * 确认已收款时间
     */
    private String receivePayTime;
    /**
     * 提交时间（填单人）
     */
    private String commitTime;
    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;

    /**
     * 查询数量
     */
    private Double counts;

    /**
     * 手机端某些功能显示控制状态(true:出现按钮，false:不出现按钮)
     */
    private Boolean phoneShowRight;

    /**
     * 手机端某些功能显示的控制状态
     */
    private LendPhoneShowStatus lendPhoneShowStatus;

    /**
     * 手机端的数据状态
     */
    private LendPhoneSelectStatus lendPhoneSelectStatus;

    /**
     * 审核身份（三种身份）
     */
    private LendIndentityStatus lendIndentityStatus;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEstimateLendDate() {
        return estimateLendDate;
    }

    public void setEstimateLendDate(String estimateLendDate) {
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

    public String getLendDate() {
        return lendDate;
    }

    public void setLendDate(String lendDate) {
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

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
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

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
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

    public String getTicketDate() {
        return ticketDate;
    }

    public void setTicketDate(String ticketDate) {
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

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
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

    public String getReceivePayTime() {
        return receivePayTime;
    }

    public void setReceivePayTime(String receivePayTime) {
        this.receivePayTime = receivePayTime;
    }

    public String getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(String commitTime) {
        this.commitTime = commitTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Double getCounts() {
        return counts;
    }

    public void setCounts(Double counts) {
        this.counts = counts;
    }

    public String getReturnRemark() {
        return returnRemark;
    }

    public void setReturnRemark(String returnRemark) {
        this.returnRemark = returnRemark;
    }

    public String getSendRecevier() {
        return sendRecevier;
    }

    public void setSendRecevier(String sendRecevier) {
        this.sendRecevier = sendRecevier;
    }

    public String getReceiveArea() {
        return receiveArea;
    }

    public void setReceiveArea(String receiveArea) {
        this.receiveArea = receiveArea;
    }

    public LendRetunStatus getLendRetunStatus() {
        return lendRetunStatus;
    }

    public void setLendRetunStatus(LendRetunStatus lendRetunStatus) {
        this.lendRetunStatus = lendRetunStatus;
    }

    public String getChargerPassTime() {
        return chargerPassTime;
    }

    public void setChargerPassTime(String chargerPassTime) {
        this.chargerPassTime = chargerPassTime;
    }

    public String getFincerPassTime() {
        return fincerPassTime;
    }

    public void setFincerPassTime(String fincerPassTime) {
        this.fincerPassTime = fincerPassTime;
    }

    public String getManagerPassTime() {
        return managerPassTime;
    }

    public void setManagerPassTime(String managerPassTime) {
        this.managerPassTime = managerPassTime;
    }

    public Boolean getPhoneShowRight() {
        return phoneShowRight;
    }

    public void setPhoneShowRight(Boolean phoneShowRight) {
        this.phoneShowRight = phoneShowRight;
    }

    public LendPhoneSelectStatus getLendPhoneSelectStatus() {
        return lendPhoneSelectStatus;
    }

    public void setLendPhoneSelectStatus(LendPhoneSelectStatus lendPhoneSelectStatus) {
        this.lendPhoneSelectStatus = lendPhoneSelectStatus;
    }

    public LendIndentityStatus getLendIndentityStatus() {
        return lendIndentityStatus;
    }

    public void setLendIndentityStatus(LendIndentityStatus lendIndentityStatus) {
        this.lendIndentityStatus = lendIndentityStatus;
    }

    public LendPhoneShowStatus getLendPhoneShowStatus() {
        return lendPhoneShowStatus;
    }

    public void setLendPhoneShowStatus(LendPhoneShowStatus lendPhoneShowStatus) {
        this.lendPhoneShowStatus = lendPhoneShowStatus;
    }
}