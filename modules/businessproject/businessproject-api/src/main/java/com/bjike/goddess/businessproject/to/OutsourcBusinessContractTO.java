package com.bjike.goddess.businessproject.to;

import com.bjike.goddess.businessproject.enums.MakeContract;
import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 外包半外包项目合同管理
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-19 11:55 ]
 * @Description: [ 外包半外包项目合同管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OutsourcBusinessContractTO extends BaseTO {

    /**
     * 测算分类
     */
    private String measureClassify;

    /**
     * 测算是否通过
     */
    private Boolean measurePass;

    /**
     * 签订时间
     */
    private String signedTime;

    /**
     * 地区
     */
    private String area;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 业务方向科目
     */
    private String businessSubject;

    /**
     * 总包单位名称
     */
    private String majorCompany;

    /**
     * 分包单位名称
     */
    private String subCompany;

    /**
     * 所属项目组
     */
    private String projectGroup;

    /**
     * 是否有合同派工
     */
    private Boolean taskContract;

    /**
     * 市场编号
     */
    private String marketNum;

    /**
     * 类型
     */
    private String type;

    /**
     * 专业/工期
     */
    private String major;

    /**
     * 是否有合同立项
     */
    private MakeContract makeContract;

    /**
     * 供应商编号
     */
    private String supplierNum;

    /**
     * 供应商地区
     */
    private String supplierArea;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 供应商类型
     */
    private String supplierType;

    /**
     * 业务联络人
     */
    private String businessLiaison;

    /**
     * 是否确定合作
     */
    private Boolean cooperation;

    /**
     * 外包合同签订时间
     */
    private String outsourcingTime;

    /**
     * 外包界面
     */
    private String outsourcingInterface;

    /**
     * 外包金额
     */
    private Double outsourcingMoney;

    /**
     * 税点
     */
    private String taxPoint;

    /**
     * 增值税发票额
     */
    private Double invoice;

    /**
     * 管理费
     */
    private Double managementFee;

    /**
     * 付款方式
     */
    private String paymentMethod;

    /**
     * 结算批次
     */
    private String settlementBatch;

    /**
     * 预估开工时间
     */
    private String expectStartDate;

    /**
     * 实际开工日期
     */
    private String realityStartDate;

    /**
     * 实际完工日期
     */
    private String realityCompleteTime;

    /**
     * 预计完工时间
     */
    private String expectCompleteTime;

    /**
     * 是否完工
     */
    private Boolean complete;

    /**
     * 是否提供完工合格依据
     */
    private Boolean qualifiedGist;

    /**
     * 是否验收
     */
    private Boolean accept;

    /**
     * 验收人
     */
    private String acceptor;

    /**
     * 验收是否通过
     */
    private Boolean acceptorPass;

    /**
     * 是否到账
     */
    private Boolean account;

    /**
     * 结算流程进度
     */
    private String settlementProgress;

    /**
     * 是否付款成功
     */
    private Boolean pay;

    /**
     * 结算完成时间
     */
    private String settlementTime;

    /**
     * 是否闭环
     */
    private Boolean closedLoop;


    public String getMeasureClassify() {
        return measureClassify;
    }

    public void setMeasureClassify(String measureClassify) {
        this.measureClassify = measureClassify;
    }

    public Boolean getMeasurePass() {
        return measurePass;
    }

    public void setMeasurePass(Boolean measurePass) {
        this.measurePass = measurePass;
    }

    public String getSignedTime() {
        return signedTime;
    }

    public void setSignedTime(String signedTime) {
        this.signedTime = signedTime;
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

    public Boolean getTaskContract() {
        return taskContract;
    }

    public void setTaskContract(Boolean taskContract) {
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

    public Boolean getCooperation() {
        return cooperation;
    }

    public void setCooperation(Boolean cooperation) {
        this.cooperation = cooperation;
    }

    public String getOutsourcingTime() {
        return outsourcingTime;
    }

    public void setOutsourcingTime(String outsourcingTime) {
        this.outsourcingTime = outsourcingTime;
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

    public String getExpectStartDate() {
        return expectStartDate;
    }

    public void setExpectStartDate(String expectStartDate) {
        this.expectStartDate = expectStartDate;
    }

    public String getRealityStartDate() {
        return realityStartDate;
    }

    public void setRealityStartDate(String realityStartDate) {
        this.realityStartDate = realityStartDate;
    }

    public String getRealityCompleteTime() {
        return realityCompleteTime;
    }

    public void setRealityCompleteTime(String realityCompleteTime) {
        this.realityCompleteTime = realityCompleteTime;
    }

    public String getExpectCompleteTime() {
        return expectCompleteTime;
    }

    public void setExpectCompleteTime(String expectCompleteTime) {
        this.expectCompleteTime = expectCompleteTime;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public Boolean getQualifiedGist() {
        return qualifiedGist;
    }

    public void setQualifiedGist(Boolean qualifiedGist) {
        this.qualifiedGist = qualifiedGist;
    }

    public Boolean getAccept() {
        return accept;
    }

    public void setAccept(Boolean accept) {
        this.accept = accept;
    }

    public String getAcceptor() {
        return acceptor;
    }

    public void setAcceptor(String acceptor) {
        this.acceptor = acceptor;
    }

    public Boolean getAcceptorPass() {
        return acceptorPass;
    }

    public void setAcceptorPass(Boolean acceptorPass) {
        this.acceptorPass = acceptorPass;
    }

    public Boolean getAccount() {
        return account;
    }

    public void setAccount(Boolean account) {
        this.account = account;
    }

    public String getSettlementProgress() {
        return settlementProgress;
    }

    public void setSettlementProgress(String settlementProgress) {
        this.settlementProgress = settlementProgress;
    }

    public Boolean getPay() {
        return pay;
    }

    public void setPay(Boolean pay) {
        this.pay = pay;
    }

    public String getSettlementTime() {
        return settlementTime;
    }

    public void setSettlementTime(String settlementTime) {
        this.settlementTime = settlementTime;
    }

    public Boolean getClosedLoop() {
        return closedLoop;
    }

    public void setClosedLoop(Boolean closedLoop) {
        this.closedLoop = closedLoop;
    }
}