package com.bjike.goddess.lendreimbursement.excel.lendreimimport;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.common.utils.excel.ExcelValue;
import com.bjike.goddess.lendreimbursement.enums.LendRetunStatus;
import com.bjike.goddess.lendreimbursement.enums.LendStatus;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 借款报销从旧系统导入数据
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:11 ]
 * @Description: [ 借款报销从旧系统导入数据 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class LendReimImportExcelTO extends BaseTO {


    /**
     * 预计借款日期
     */
    @ExcelHeader(name = "预计借款日期",notNull = true)
    private LocalDate estimateLendDate;

    /**
     * 借款人
     */
    @ExcelHeader(name = "借款人",notNull = true)
    private String lender;

    /**
     * 负责人
     */
    @ExcelHeader(name = "负责人",notNull = true)
    private String charger;

    /**
     * 地区
     */
    @ExcelHeader(name = "地区",notNull = true)
    private String area;

    /**
     * 项目组
     */
    @ExcelHeader(name = "项目组")
    private String projectGroup;

    /**
     * 项目名称
     */
    @ExcelHeader(name = "项目名称",notNull = true)
    private String projectName;

    /**
     * 参与人员
     */
    @ExcelHeader(name = "参与人员",notNull = true)
    private String attender;

    /**
     * 借款方式
     */
    @ExcelHeader(name = "借款方式",notNull = true)
    private String lendWay;


    /**
     * 一级科目
     */
    @ExcelHeader(name = "一级科目",notNull = true)
    private String firstSubject;

    /**
     * 二级科目
     */
    @ExcelHeader(name = "二级科目",notNull = true)
    private String secondSubject;

    /**
     * 三级科目
     */
    @ExcelHeader(name = "三级科目",notNull = true)
    private String thirdSubject;

    /**
     * 说明
     */
    @ExcelHeader(name = "说明" )
    private String explains;


    /**
     * 借款事由
     */
    @ExcelHeader(name = "借款事由",notNull = true)
    private String lendReson;

    /**
     * 金额
     */
    @ExcelHeader(name = "金额",notNull = true)
    private Double money;



    /**
     * 备注
     */
    @ExcelHeader(name = "备注")
    private String remark;


    /**
     * 借款日期(付款后才有，相当于付款日期)
     */
    @ExcelHeader(name = "借款日期")
    private LocalDate lendDate;


    /**
     * 负责人是否通过（是/否）
     */
    @ExcelHeader(name = "负责人是否通过")
    private String chargerPass;

    /**
     * 负责人审核时间
     */
    @ExcelHeader(name = "负责人审核时间")
    private LocalDateTime chargerPassTime;

    /**
     * 财务运营部
     */
    @ExcelHeader(name = "财务运营部")
    private String finacer;

    /**
     * 财务运营部审核意见
     */
    @ExcelHeader(name = "财务运营部审核意见")
    private String fincerOpinion;

    /**
     * 财务运营部是否通过（是/否）
     */
    @ExcelHeader(name = "财务运营部是否通过")
    private String fincerPass;
    /**
     * 财务运营部审核时间
     */
    @ExcelHeader(name = "财务运营部审核时间")
    private LocalDateTime fincerPassTime;

    /**
     * 总经办
     */
    @ExcelHeader(name = "总经办" )
    private String manager;

    /**
     * 总经办审核意见
     */
    @ExcelHeader(name = "总经办审核意见")
    private String managerOpinion;

    /**
     * 总经办是否通过（是/否）
     */
    @ExcelHeader(name = "总经办是否通过" )
    private String managerPass;

    /**
     * 总经办审核时间
     */
    @ExcelHeader(name = "总经办审核时间" )
    private LocalDateTime managerPassTime;


    /**
     * 付款人
     */
    @ExcelHeader(name = "付款人")
    private String payer;

    /**
     * 付款日期
     */
    @ExcelHeader(name = "付款日期")
    private LocalDate payDate;

    /**
     * 支付来源
     */
    @ExcelHeader(name = "支付来源")
    private String payOrigin;

    /**
     * 单据数量
     */
    @ExcelHeader(name = "单据数量")
    private Double documentQuantity;


    /**
     * 报销金额
     */
    @ExcelHeader(name = "报销金额")
    private Double reimMoney;

    /**
     * 借款金额
     */
    @ExcelHeader(name = "借款金额")
    private Double lendMoney;

    /**
     * 退回金额
     */
    @ExcelHeader(name = "退回金额")
    private Double returnMoney;

    /**
     * 退回日期
     */
    @ExcelHeader(name = "退回日期" )
    private LocalDate returnDate;

    /**
     * 归还方式
     */
    @ExcelHeader(name = "归还方式")
    private String returnWays;


    /**
     * 寄件人
     */
    @ExcelHeader(name = "寄件人")
    private String sender;

    /**
     * 寄件日期
     */
    @ExcelHeader(name = "寄件日期")
    private LocalDate sendDate;

    /**
     * 寄件情况
     */
    @ExcelHeader(name = "寄件情况")
    private String sendCondition;



    /**
     * 收票人
     */
    @ExcelHeader(name = "收票人" )
    private String ticketer;

    /**
     * 收票日期
     */
    @ExcelHeader(name = "收票日期" )
    private LocalDate ticketDate;

    /**
     * 收票情况
     */
    @ExcelHeader(name = "收票情况" )
    private String ticketCondition;


    /**
     * 核对人
     */
    @ExcelHeader(name = "核对人" )
    private String checker;

    /**
     * 核对日期
     */
    @ExcelHeader(name = "核对日期" )
    private LocalDate checkDate;

    /**
     * 核对内容
     */
    @ExcelHeader(name = "核对内容" )
    private String checkcontent;


    /**
     * 是否已收款（是/否）
     */
    @ExcelHeader(name = "是否已收款" )
    private String receivePay;

    /**
     * 是否已还款（未处理/通过/不通过）
     */
    @ExcelHeader(name = "是否已还款" )
    private String returnStatus;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public LocalDate getLendDate() {
        return lendDate;
    }

    public void setLendDate(LocalDate lendDate) {
        this.lendDate = lendDate;
    }

    public String getChargerPass() {
        return chargerPass;
    }

    public void setChargerPass(String chargerPass) {
        this.chargerPass = chargerPass;
    }

    public LocalDateTime getChargerPassTime() {
        return chargerPassTime;
    }

    public void setChargerPassTime(LocalDateTime chargerPassTime) {
        this.chargerPassTime = chargerPassTime;
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

    public LocalDateTime getFincerPassTime() {
        return fincerPassTime;
    }

    public void setFincerPassTime(LocalDateTime fincerPassTime) {
        this.fincerPassTime = fincerPassTime;
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

    public LocalDateTime getManagerPassTime() {
        return managerPassTime;
    }

    public void setManagerPassTime(LocalDateTime managerPassTime) {
        this.managerPassTime = managerPassTime;
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

    public String getReceivePay() {
        return receivePay;
    }

    public void setReceivePay(String receivePay) {
        this.receivePay = receivePay;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }
}
