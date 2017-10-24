package com.bjike.goddess.businessproject.excel;

import com.bjike.goddess.businessproject.enums.MakeContract;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

import java.time.LocalDate;

/**
 * 外包半外包项目合同管理业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-19 11:55 ]
 * @Description: [ 外包半外包项目合同管理业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OutsourcBusinessContractExcel extends BaseTO {

    /**
     * 测算分类
     */
    @ExcelHeader(name = "测算分类")
    private String measureClassify;

    /**
     * 测算是否通过
     */
    @ExcelHeader(name = "测算是否通过")
    private String measurePass;

    /**
     * 签订时间
     */
    @ExcelHeader(name = "签订时间")
    private LocalDate signedTime;

    /**
     * 地区
     */
    @ExcelHeader(name = "地区")
    private String area;

    /**
     * 业务类型
     */
    @ExcelHeader(name = "业务类型")
    private String businessType;

    /**
     * 业务方向科目
     */
    @ExcelHeader(name = "业务方向科目")
    private String businessSubject;

    /**
     * 总包单位名称
     */
    @ExcelHeader(name = "总包单位名称")
    private String majorCompany;

    /**
     * 分包单位名称
     */
    @ExcelHeader(name = "分包单位名称")
    private String subCompany;

    /**
     * 所属项目组
     */
    @ExcelHeader(name = "所属项目组")
    private String projectGroup;

    /**
     * 是否有合同派工
     */
    @ExcelHeader(name = "是否有合同派工")
    private String taskContract;

    /**
     * 市场编号
     */
    @ExcelHeader(name = "市场编号")
    private String marketNum;

    /**
     * 类型
     */
    @ExcelHeader(name = "类型")
    private String type;

    /**
     * 专业/工期
     */
    @ExcelHeader(name = "专业/工期")
    private String major;

    /**
     * 是否有合同立项
     */
    @ExcelHeader(name = "是否有合同立项")
    private MakeContract makeContract;

    /**
     * 供应商编号
     */
    @ExcelHeader(name = "供应商编号")
    private String supplierNum;

    /**
     * 供应商地区
     */
    @ExcelHeader(name = "供应商地区")
    private String supplierArea;

    /**
     * 供应商名称
     */
    @ExcelHeader(name = "供应商名称")
    private String supplierName;

    /**
     * 供应商类型
     */
    @ExcelHeader(name = "供应商类型")
    private String supplierType;

    /**
     * 业务联络人
     */
    @ExcelHeader(name = "业务联络人")
    private String businessLiaison;

    /**
     * 是否确定合作
     */
    @ExcelHeader(name = "是否确定合作")
    private String cooperation;

    /**
     * 外包合同签订时间
     */
    @ExcelHeader(name = "外包合同签订时间")
    private LocalDate outsourcingTime;

    /**
     * 外包界面
     */
    @ExcelHeader(name = "外包界面")
    private String outsourcingInterface;

    /**
     * 外包金额
     */
    @ExcelHeader(name = "外包金额")
    private Double outsourcingMoney;

    /**
     * 税点
     */
    @ExcelHeader(name = "税点")
    private String taxPoint;

    /**
     * 增值税发票额
     */
    @ExcelHeader(name = "增值税发票额")
    private Double invoice;

    /**
     * 管理费
     */
    @ExcelHeader(name = "管理费")
    private Double managementFee;

    /**
     * 付款方式
     */
    @ExcelHeader(name = "付款方式")
    private String paymentMethod;

    /**
     * 结算批次
     */
    @ExcelHeader(name = "结算批次")
    private String settlementBatch;

    /**
     * 预估开工时间
     */
    @ExcelHeader(name = "预估开工时间")
    private LocalDate expectStartDate;

    /**
     * 实际开工日期
     */
    @ExcelHeader(name = "实际开工日期")
    private LocalDate realityStartDate;

    /**
     * 实际完工日期
     */
    @ExcelHeader(name = "实际完工日期")
    private LocalDate realityCompleteTime;

    /**
     * 预计完工时间
     */
    @ExcelHeader(name = "预计完工时间")
    private LocalDate expectCompleteTime;

    /**
     * 是否完工
     */
    @ExcelHeader(name = "是否完工")
    private String complete;

    /**
     * 是否提供完工合格依据
     */
    @ExcelHeader(name = "是否提供完工合格依据")
    private String qualifiedGist;

    /**
     * 是否验收
     */
    @ExcelHeader(name = "是否验收")
    private String accept;

    /**
     * 验收人
     */
    @ExcelHeader(name = "验收人")
    private String acceptor;

    /**
     * 验收是否通过
     */
    @ExcelHeader(name = "验收是否通过")
    private String acceptorPass;

    /**
     * 是否到账
     */
    @ExcelHeader(name = "是否到账")
    private String account;

    /**
     * 结算流程进度
     */
    @ExcelHeader(name = "结算流程进度")
    private String settlementProgress;

    /**
     * 是否付款成功
     */
    @ExcelHeader(name = "是否付款成功")
    private String pay;

    /**
     * 结算完成时间
     */
    @ExcelHeader(name = "结算完成时间")
    private LocalDate settlementTime;

    /**
     * 是否闭环
     */
    @ExcelHeader(name = "是否闭环")
    private String closedLoop;


    public String getMeasureClassify() {
        return measureClassify;
    }

    public void setMeasureClassify(String measureClassify) {
        this.measureClassify = measureClassify;
    }

    public String getMeasurePass() {
        return measurePass;
    }

    public void setMeasurePass(String measurePass) {
        this.measurePass = measurePass;
    }


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBusinessSubject() {
        return businessSubject;
    }

    public void setBusinessSubject(String businessSubject) {
        this.businessSubject = businessSubject;
    }

    public String getMajorCompany() {
        return majorCompany;
    }

    public void setMajorCompany(String majorCompany) {
        this.majorCompany = majorCompany;
    }

    public String getSubCompany() {
        return subCompany;
    }

    public void setSubCompany(String subCompany) {
        this.subCompany = subCompany;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getTaskContract() {
        return taskContract;
    }

    public void setTaskContract(String taskContract) {
        this.taskContract = taskContract;
    }

    public String getMarketNum() {
        return marketNum;
    }

    public void setMarketNum(String marketNum) {
        this.marketNum = marketNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public MakeContract getMakeContract() {
        return makeContract;
    }

    public void setMakeContract(MakeContract makeContract) {
        this.makeContract = makeContract;
    }

    public String getSupplierNum() {
        return supplierNum;
    }

    public void setSupplierNum(String supplierNum) {
        this.supplierNum = supplierNum;
    }

    public String getSupplierArea() {
        return supplierArea;
    }

    public void setSupplierArea(String supplierArea) {
        this.supplierArea = supplierArea;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }

    public String getBusinessLiaison() {
        return businessLiaison;
    }

    public void setBusinessLiaison(String businessLiaison) {
        this.businessLiaison = businessLiaison;
    }

    public String getCooperation() {
        return cooperation;
    }

    public void setCooperation(String cooperation) {
        this.cooperation = cooperation;
    }


    public String getOutsourcingInterface() {
        return outsourcingInterface;
    }

    public void setOutsourcingInterface(String outsourcingInterface) {
        this.outsourcingInterface = outsourcingInterface;
    }

    public Double getOutsourcingMoney() {
        return outsourcingMoney;
    }

    public void setOutsourcingMoney(Double outsourcingMoney) {
        this.outsourcingMoney = outsourcingMoney;
    }

    public String getTaxPoint() {
        return taxPoint;
    }

    public void setTaxPoint(String taxPoint) {
        this.taxPoint = taxPoint;
    }

    public Double getInvoice() {
        return invoice;
    }

    public void setInvoice(Double invoice) {
        this.invoice = invoice;
    }

    public Double getManagementFee() {
        return managementFee;
    }

    public void setManagementFee(Double managementFee) {
        this.managementFee = managementFee;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getSettlementBatch() {
        return settlementBatch;
    }

    public void setSettlementBatch(String settlementBatch) {
        this.settlementBatch = settlementBatch;
    }


    public String getComplete() {
        return complete;
    }

    public void setComplete(String complete) {
        this.complete = complete;
    }

    public String getQualifiedGist() {
        return qualifiedGist;
    }

    public void setQualifiedGist(String qualifiedGist) {
        this.qualifiedGist = qualifiedGist;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getAcceptor() {
        return acceptor;
    }

    public void setAcceptor(String acceptor) {
        this.acceptor = acceptor;
    }

    public String getAcceptorPass() {
        return acceptorPass;
    }

    public void setAcceptorPass(String acceptorPass) {
        this.acceptorPass = acceptorPass;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getSettlementProgress() {
        return settlementProgress;
    }

    public void setSettlementProgress(String settlementProgress) {
        this.settlementProgress = settlementProgress;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }


    public String getClosedLoop() {
        return closedLoop;
    }

    public void setClosedLoop(String closedLoop) {
        this.closedLoop = closedLoop;
    }

    public LocalDate getSignedTime() {
        return signedTime;
    }

    public void setSignedTime(LocalDate signedTime) {
        this.signedTime = signedTime;
    }

    public LocalDate getOutsourcingTime() {
        return outsourcingTime;
    }

    public void setOutsourcingTime(LocalDate outsourcingTime) {
        this.outsourcingTime = outsourcingTime;
    }

    public LocalDate getExpectStartDate() {
        return expectStartDate;
    }

    public void setExpectStartDate(LocalDate expectStartDate) {
        this.expectStartDate = expectStartDate;
    }

    public LocalDate getRealityStartDate() {
        return realityStartDate;
    }

    public void setRealityStartDate(LocalDate realityStartDate) {
        this.realityStartDate = realityStartDate;
    }

    public LocalDate getRealityCompleteTime() {
        return realityCompleteTime;
    }

    public void setRealityCompleteTime(LocalDate realityCompleteTime) {
        this.realityCompleteTime = realityCompleteTime;
    }

    public LocalDate getExpectCompleteTime() {
        return expectCompleteTime;
    }

    public void setExpectCompleteTime(LocalDate expectCompleteTime) {
        this.expectCompleteTime = expectCompleteTime;
    }

    public LocalDate getSettlementTime() {
        return settlementTime;
    }

    public void setSettlementTime(LocalDate settlementTime) {
        this.settlementTime = settlementTime;
    }
}