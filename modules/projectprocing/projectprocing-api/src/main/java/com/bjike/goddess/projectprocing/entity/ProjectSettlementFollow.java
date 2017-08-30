package com.bjike.goddess.projectprocing.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 项目结算跟进
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-31 03:49 ]
 * @Description: [ 项目结算跟进 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectprocing_projectsettlementfollow")
public class ProjectSettlementFollow extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 业务类型
     */
    @Column(name = "businessType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务类型'")
    private String businessType;

    /**
     * 业务方向科目
     */
    @Column(name = "businessSubject", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务方向科目'")
    private String businessSubject;

    /**
     * 运营商名称
     */
    @Column(name = "operatorName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '运营商名称'")
    private String operatorName;

    /**
     * 厂家名称
     */
    @Column(name = "manufacturerName",  columnDefinition = "VARCHAR(255)   COMMENT '厂家名称'")
    private String manufacturerName;

    /**
     * 外包单位
     */
    @Column(name = "outsourcingUnit", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '外包单位'")
    private String outsourcingUnit;

    /**
     * 合同外部项目名称
     */
    @Column(name = "outerName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '合同外部项目名称'")
    private String outerName;

    /**
     * 内部项目名称
     */
    @Column(name = "innerName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '内部项目名称'")
    private String innerName;

    /**
     * 合同编号
     */
    @Column(name = "contractNum",  columnDefinition = "VARCHAR(255)   COMMENT '合同编号'")
    private String contractNum;

    /**
     * 派工单编号
     */
    @Column(name = "dispatchNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '派工单编号'")
    private String dispatchNum;

    /**
     * 销售合同号
     */
    @Column(name = "saleNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '销售合同号'")
    private String saleNum;

    /**
     * 合同金额
     */
    @Column(name = "contractMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '合同金额'")
    private Double contractMoney;

    /**
     * 合同规模数
     */
    @Column(name = "contractSizeNum", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '合同规模数'")
    private Double contractSizeNum;

    /**
     * 派工情况
     */
    @Column(name = "dispatchCondition",  columnDefinition = "VARCHAR(255)   COMMENT '派工情况'")
    private String dispatchCondition;

    /**
     * 派工金额
     */
    @Column(name = "dispatchMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '派工金额'")
    private Double dispatchMoney;

    /**
     * 单价
     */
    @Column(name = "price", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '单价'")
    private Double price;

    /**
     * BBU规模
     */
    @Column(name = "bbuSize",  columnDefinition = "VARCHAR(255)   COMMENT 'BBU规模'")
    private String bbuSize;

    /**
     * RRU规模
     */
    @Column(name = "rruSize",  columnDefinition = "VARCHAR(255)   COMMENT 'RRU规模'")
    private String rruSize;

    /**
     * 已开通站点数
     */
    @Column(name = "openStationPointNum",  columnDefinition = "VARCHAR(255)   COMMENT '已开通站点数'")
    private String openStationPointNum;

    /**
     * 已开通BBU数
     */
    @Column(name = "openBbuPointNum",  columnDefinition = "VARCHAR(255)   COMMENT '已开通BBU数'")
    private String openBbuPointNum;

    /**
     * 已开通RRU数
     */
    @Column(name = "openRruPointNum",  columnDefinition = "VARCHAR(255)   COMMENT '已开通RRU数'")
    private String openRruPointNum;

    /**
     * 未开通站点
     */
    @Column(name = "noOpenStationPointNum",  columnDefinition = "VARCHAR(255)   COMMENT '未开通站点'")
    private String noOpenStationPointNum;

    /**
     * 未开通BBU数
     */
    @Column(name = "noOpenBbuPointNum", columnDefinition = "VARCHAR(255)   COMMENT '未开通BBU数'")
    private String noOpenBbuPointNum;

    /**
     * 未开通RRU数
     */
    @Column(name = "noOpenRruPointNum",  columnDefinition = "VARCHAR(255)   COMMENT '未开通RRU数'")
    private String noOpenRruPointNum;

    /**
     * 开通比例
     */
    @Column(name = "openRate",  columnDefinition = "VARCHAR(255)   COMMENT '开通比例'")
    private String openRate;

    /**
     * 设备型号
     */
    @Column(name = "deviceType",  columnDefinition = "VARCHAR(255)   COMMENT '设备型号'")
    private String deviceType;

    /**
     * 完工数
     */
    @Column(name = "completeNum",  columnDefinition = "VARCHAR(255)   COMMENT '完工数'")
    private String completeNum;

    /**
     * 未完工数
     */
    @Column(name = "noCompleteNum", columnDefinition = "VARCHAR(255)   COMMENT '未完工数'")
    private String noCompleteNum;

    /**
     * 是否初验
     */
    @Column(name = "startCheckCondition", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '是否初验(已初验/未初验)'")
    private String startCheckCondition;

    /**
     * 是否终验
     */
    @Column(name = "finalCheckCondition", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '是否终验(已终验/未终验)'")
    private String finalCheckCondition;

    /**
     * 是否已制作申请结算资料
     */
    @Column(name = "settleFileCondition", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '是否已制作申请结算资料(是/否)'")
    private String settleFileCondition;

    /**
     * 是否影响结算
     */
    @Column(name = "settleCondition", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '是否影响结算(是/否)'")
    private String settleCondition;

    /**
     * 结算计划
     */
    @Column(name = "settlePlan",  columnDefinition = "VARCHAR(255)   COMMENT '结算计划'")
    private String settlePlan;

    /**
     * 归属
     */
    @Column(name = "beLongs",  columnDefinition = "VARCHAR(255)   COMMENT '归属'")
    private String beLongs;

    /**
     * 签字审批时间
     */
    @Column(name = "signAuditTime",  columnDefinition = "DATE   COMMENT '签字审批时间'")
    private LocalDate signAuditTime;

    /**
     * ERP结算审批时间
     */
    @Column(name = "eRPAuditTime",  columnDefinition = "DATE  COMMENT 'ERP结算审批时间'")
    private LocalDate eRPAuditTime;

    /**
     * 发票审核时间
     */
    @Column(name = "billAuditTime",  columnDefinition = "DATE  COMMENT '发票审核时间'")
    private LocalDate billAuditTime;

    /**
     * 预计支付时间
     */
    @Column(name = "budgetAuditTime",  columnDefinition = "DATE  COMMENT '预计支付时间'")
    private LocalDate budgetAuditTime;

    /**
     * 管理费
     */
    @Column(name = "manageFee", nullable = false, columnDefinition = "DECIMAL(10,2)  COMMENT '管理费'")
    private Double manageFee;

    /**
     * 违约金
     */
    @Column(name = "betraFee", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '违约金'")
    private Double betraFee;

    /**
     * 违约金扣款事项
     */
    @Column(name = "betraFeeList",  columnDefinition = "VARCHAR(255)   COMMENT '违约金扣款事项'")
    private String betraFeeList;

    /**
     * 结算进度A
     */
    @Column(name = "settleProcingA", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '结算进度A'")
    private String settleProcingA;

    /**
     * 结算进度B
     */
    @Column(name = "settleProcingB", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '结算进度B'")
    private String settleProcingB;

    /**
     * 结算进度C
     */
    @Column(name = "settleProcingC", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '结算进度C'")
    private String settleProcingC;

    /**
     * 结算金额
     */
    @Column(name = "settleMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '结算金额'")
    private Double settleMoney;

    /**
     * 剩余结算数量
     */
    @Column(name = "restSettleNum", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '剩余结算数量'")
    private Double restSettleNum;

    /**
     * 剩余结算金额
     */
    @Column(name = "restSettleMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '剩余结算金额'")
    private Double restSettleMoney;

    /**
     * 到帐时间
     */
    @Column(name = "receiveMoneyTime",  columnDefinition = "DATE  COMMENT '到帐时间'")
    private LocalDate receiveMoneyTime;

    /**
     * 到帐金额
     */
    @Column(name = "receiveMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '到帐金额'")
    private Double receiveMoney;

    /**
     * 分批结算
     */
    @Column(name = "batchSettle",  columnDefinition = "VARCHAR(255)   COMMENT '分批结算'")
    private String batchSettle;

    /**
     * 备注
     */
    @Column(name = "remark",  columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    /**
     * 商务合同外部项目名称id
     */
    @Column(name = "outerNameId",columnDefinition = "VARCHAR(255)   COMMENT '商务合同外部项目名称id'")
    private String outerNameId;

    /**
     * 市场信息记录内部项目名称id
     */
    @Column(name = "innerNameId", columnDefinition = "VARCHAR(255)   COMMENT '市场信息记录内部项目名称id'")
    private String innerNameId;

    /**
     * 商务项目合同基本信息对应销售合同号id
     */
    @Column(name = "saleNumId",  columnDefinition = "VARCHAR(255)   COMMENT '商务项目合同基本信息对应销售合同号id'")
    private String saleNumId;

    /**
     * 市场信息收集公司业务/发展方向id
     */
    @Column(name = "businessId",  columnDefinition = "VARCHAR(255)   COMMENT '市场信息收集公司业务/发展方向id'")
    private String businessId;


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

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getOutsourcingUnit() {
        return outsourcingUnit;
    }

    public void setOutsourcingUnit(String outsourcingUnit) {
        this.outsourcingUnit = outsourcingUnit;
    }

    public String getOuterName() {
        return outerName;
    }

    public void setOuterName(String outerName) {
        this.outerName = outerName;
    }

    public String getInnerName() {
        return innerName;
    }

    public void setInnerName(String innerName) {
        this.innerName = innerName;
    }

    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    public String getDispatchNum() {
        return dispatchNum;
    }

    public void setDispatchNum(String dispatchNum) {
        this.dispatchNum = dispatchNum;
    }

    public String getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(String saleNum) {
        this.saleNum = saleNum;
    }

    public Double getContractMoney() {
        return contractMoney;
    }

    public void setContractMoney(Double contractMoney) {
        this.contractMoney = contractMoney;
    }

    public Double getContractSizeNum() {
        return contractSizeNum;
    }

    public void setContractSizeNum(Double contractSizeNum) {
        this.contractSizeNum = contractSizeNum;
    }

    public String getDispatchCondition() {
        return dispatchCondition;
    }

    public void setDispatchCondition(String dispatchCondition) {
        this.dispatchCondition = dispatchCondition;
    }

    public Double getDispatchMoney() {
        return dispatchMoney;
    }

    public void setDispatchMoney(Double dispatchMoney) {
        this.dispatchMoney = dispatchMoney;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBbuSize() {
        return bbuSize;
    }

    public void setBbuSize(String bbuSize) {
        this.bbuSize = bbuSize;
    }

    public String getRruSize() {
        return rruSize;
    }

    public void setRruSize(String rruSize) {
        this.rruSize = rruSize;
    }

    public String getOpenStationPointNum() {
        return openStationPointNum;
    }

    public void setOpenStationPointNum(String openStationPointNum) {
        this.openStationPointNum = openStationPointNum;
    }

    public String getOpenBbuPointNum() {
        return openBbuPointNum;
    }

    public void setOpenBbuPointNum(String openBbuPointNum) {
        this.openBbuPointNum = openBbuPointNum;
    }

    public String getOpenRruPointNum() {
        return openRruPointNum;
    }

    public void setOpenRruPointNum(String openRruPointNum) {
        this.openRruPointNum = openRruPointNum;
    }

    public String getNoOpenStationPointNum() {
        return noOpenStationPointNum;
    }

    public void setNoOpenStationPointNum(String noOpenStationPointNum) {
        this.noOpenStationPointNum = noOpenStationPointNum;
    }

    public String getNoOpenBbuPointNum() {
        return noOpenBbuPointNum;
    }

    public void setNoOpenBbuPointNum(String noOpenBbuPointNum) {
        this.noOpenBbuPointNum = noOpenBbuPointNum;
    }

    public String getNoOpenRruPointNum() {
        return noOpenRruPointNum;
    }

    public void setNoOpenRruPointNum(String noOpenRruPointNum) {
        this.noOpenRruPointNum = noOpenRruPointNum;
    }

    public String getOpenRate() {
        return openRate;
    }

    public void setOpenRate(String openRate) {
        this.openRate = openRate;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getCompleteNum() {
        return completeNum;
    }

    public void setCompleteNum(String completeNum) {
        this.completeNum = completeNum;
    }

    public String getNoCompleteNum() {
        return noCompleteNum;
    }

    public void setNoCompleteNum(String noCompleteNum) {
        this.noCompleteNum = noCompleteNum;
    }

    public String getStartCheckCondition() {
        return startCheckCondition;
    }

    public void setStartCheckCondition(String startCheckCondition) {
        this.startCheckCondition = startCheckCondition;
    }

    public String getFinalCheckCondition() {
        return finalCheckCondition;
    }

    public void setFinalCheckCondition(String finalCheckCondition) {
        this.finalCheckCondition = finalCheckCondition;
    }

    public String getSettleFileCondition() {
        return settleFileCondition;
    }

    public void setSettleFileCondition(String settleFileCondition) {
        this.settleFileCondition = settleFileCondition;
    }

    public String getSettleCondition() {
        return settleCondition;
    }

    public void setSettleCondition(String settleCondition) {
        this.settleCondition = settleCondition;
    }

    public String getSettlePlan() {
        return settlePlan;
    }

    public void setSettlePlan(String settlePlan) {
        this.settlePlan = settlePlan;
    }

    public String getBeLongs() {
        return beLongs;
    }

    public void setBeLongs(String beLongs) {
        this.beLongs = beLongs;
    }


    public LocalDate getSignAuditTime() {
        return signAuditTime;
    }

    public void setSignAuditTime(LocalDate signAuditTime) {
        this.signAuditTime = signAuditTime;
    }

    public LocalDate geteRPAuditTime() {
        return eRPAuditTime;
    }

    public void seteRPAuditTime(LocalDate eRPAuditTime) {
        this.eRPAuditTime = eRPAuditTime;
    }

    public LocalDate getBillAuditTime() {
        return billAuditTime;
    }

    public void setBillAuditTime(LocalDate billAuditTime) {
        this.billAuditTime = billAuditTime;
    }

    public LocalDate getBudgetAuditTime() {
        return budgetAuditTime;
    }

    public void setBudgetAuditTime(LocalDate budgetAuditTime) {
        this.budgetAuditTime = budgetAuditTime;
    }

    public void setReceiveMoneyTime(LocalDate receiveMoneyTime) {
        this.receiveMoneyTime = receiveMoneyTime;
    }

    public Double getManageFee() {
        return manageFee;
    }

    public void setManageFee(Double manageFee) {
        this.manageFee = manageFee;
    }

    public Double getBetraFee() {
        return betraFee;
    }

    public void setBetraFee(Double betraFee) {
        this.betraFee = betraFee;
    }

    public String getBetraFeeList() {
        return betraFeeList;
    }

    public void setBetraFeeList(String betraFeeList) {
        this.betraFeeList = betraFeeList;
    }

    public String getSettleProcingA() {
        return settleProcingA;
    }

    public void setSettleProcingA(String settleProcingA) {
        this.settleProcingA = settleProcingA;
    }

    public String getSettleProcingB() {
        return settleProcingB;
    }

    public void setSettleProcingB(String settleProcingB) {
        this.settleProcingB = settleProcingB;
    }

    public String getSettleProcingC() {
        return settleProcingC;
    }

    public void setSettleProcingC(String settleProcingC) {
        this.settleProcingC = settleProcingC;
    }

    public Double getSettleMoney() {
        return settleMoney;
    }

    public void setSettleMoney(Double settleMoney) {
        this.settleMoney = settleMoney;
    }

    public Double getRestSettleNum() {
        return restSettleNum;
    }

    public void setRestSettleNum(Double restSettleNum) {
        this.restSettleNum = restSettleNum;
    }

    public Double getRestSettleMoney() {
        return restSettleMoney;
    }

    public void setRestSettleMoney(Double restSettleMoney) {
        this.restSettleMoney = restSettleMoney;
    }

    public LocalDate getReceiveMoneyTime() {
        return receiveMoneyTime;
    }

    public Double getReceiveMoney() {
        return receiveMoney;
    }

    public void setReceiveMoney(Double receiveMoney) {
        this.receiveMoney = receiveMoney;
    }

    public String getBatchSettle() {
        return batchSettle;
    }

    public void setBatchSettle(String batchSettle) {
        this.batchSettle = batchSettle;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOuterNameId() {
        return outerNameId;
    }

    public void setOuterNameId(String outerNameId) {
        this.outerNameId = outerNameId;
    }

    public String getInnerNameId() {
        return innerNameId;
    }

    public void setInnerNameId(String innerNameId) {
        this.innerNameId = innerNameId;
    }

    public String getSaleNumId() {
        return saleNumId;
    }

    public void setSaleNumId(String saleNumId) {
        this.saleNumId = saleNumId;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }
}