package com.bjike.goddess.projectprocing.vo;

/**
 * 项目结算跟进表现层对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-31 03:49 ]
 * @Description: [ 项目结算跟进表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectSettlementFollowVO {

    /**
     * id
     */
    private String id;
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
     * 运营商名称
     */
    private String operatorName;

    /**
     * 厂家名称
     */
    private String manufacturerName;

    /**
     * 外包单位
     */
    private String outsourcingUnit;

    /**
     * 合同外部项目名称
     */
    private String outerName;

    /**
     * 内部项目名称
     */
    private String innerName;

    /**
     * 合同编号
     */
    private String contractNum;

    /**
     * 派工单编号
     */
    private String dispatchNum;

    /**
     * 销售合同号
     */
    private String saleNum;

    /**
     * 合同金额
     */
    private Double contractMoney;

    /**
     * 合同规模数
     */
    private Double contractSizeNum;

    /**
     * 派工情况
     */
    private String dispatchCondition;

    /**
     * 派工金额
     */
    private Double dispatchMoney;

    /**
     * 单价
     */
    private Double price;

    /**
     * BBU规模
     */
    private String bbuSize;

    /**
     * RRU规模
     */
    private String rruSize;

    /**
     * 已开通站点数
     */
    private String openStationPointNum;

    /**
     * 已开通BBU数
     */
    private String openBbuPointNum;

    /**
     * 已开通RRU数
     */
    private String openRruPointNum;

    /**
     * 未开通站点
     */
    private String noOpenStationPointNum;

    /**
     * 未开通BBU数
     */
    private String noOpenBbuPointNum;

    /**
     * 未开通RRU数
     */
    private String noOpenRruPointNum;

    /**
     * 开通比例
     */
    private String openRate;

    /**
     * 设备型号
     */
    private String deviceType;

    /**
     * 完工数
     */
    private String completeNum;

    /**
     * 未完工数
     */
    private String noCompleteNum;

    /**
     * 是否初验
     */
    private String startCheckCondition;

    /**
     * 是否终验
     */
    private String finalCheckCondition;

    /**
     * 是否已制作申请结算资料
     */
    private String settleFileCondition;

    /**
     * 是否影响结算
     */
    private String settleCondition;

    /**
     * 结算计划
     */
    private String settlePlan;

    /**
     * 归属
     */
    private String beLongs;

    /**
     * 签字审批时间
     */
    private String signAuditTime;

    /**
     * ERP结算审批时间
     */
    private String eRPAuditTime;

    /**
     * 发票审核时间
     */
    private String billAuditTime;

    /**
     * 预计支付时间
     */
    private String budgetAuditTime;

    /**
     * 管理费
     */
    private Double manageFee;

    /**
     * 违约金
     */
    private Double betraFee;

    /**
     * 违约金扣款事项
     */
    private String betraFeeList;

    /**
     * 结算进度A
     */
    private String settleProcingA;

    /**
     * 结算进度B
     */
    private String settleProcingB;

    /**
     * 结算进度C
     */
    private String settleProcingC;

    /**
     * 结算金额
     */
    private Double settleMoney;

    /**
     * 剩余结算数量
     */
    private Double restSettleNum;

    /**
     * 剩余结算金额
     */
    private Double restSettleMoney;

    /**
     * 到帐时间
     */
    private String receiveMoneyTime;

    /**
     * 到帐金额
     */
    private Double receiveMoney;

    /**
     * 分批结算
     */
    private String batchSettle;

    /**
     * 备注
     */
    private String remark;


    /**
     * 商务合同外部项目名称id
     */
    private String outerNameId;

    /**
     * 市场信息记录内部项目名称id
     */
    private String innerNameId;

    /**
     * 商务项目合同基本信息对应销售合同号id
     */
    private String saleNumId;

    /**
     * 市场信息收集公司业务/发展方向id
     */
    private String businessId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSignAuditTime() {
        return signAuditTime;
    }

    public void setSignAuditTime(String signAuditTime) {
        this.signAuditTime = signAuditTime;
    }

    public String geteRPAuditTime() {
        return eRPAuditTime;
    }

    public void seteRPAuditTime(String eRPAuditTime) {
        this.eRPAuditTime = eRPAuditTime;
    }

    public String getBillAuditTime() {
        return billAuditTime;
    }

    public void setBillAuditTime(String billAuditTime) {
        this.billAuditTime = billAuditTime;
    }

    public String getBudgetAuditTime() {
        return budgetAuditTime;
    }

    public void setBudgetAuditTime(String budgetAuditTime) {
        this.budgetAuditTime = budgetAuditTime;
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

    public String getReceiveMoneyTime() {
        return receiveMoneyTime;
    }

    public void setReceiveMoneyTime(String receiveMoneyTime) {
        this.receiveMoneyTime = receiveMoneyTime;
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
}