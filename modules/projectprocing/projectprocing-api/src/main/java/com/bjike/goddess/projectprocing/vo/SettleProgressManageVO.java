package com.bjike.goddess.projectprocing.vo;

import com.bjike.goddess.projectprocing.enums.ActualCompletedState;
import com.bjike.goddess.projectprocing.enums.DispatCondition;
import com.bjike.goddess.projectprocing.enums.Kpi;
import com.bjike.goddess.projectprocing.enums.SettlementPlan;

/**
 * 结算进度管理表现层对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 02:22 ]
 * @Description: [ 结算进度管理表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SettleProgressManageVO {

    /**
     * id
     */
    private String id;
    /**
     * 更改日期
     */
    private String updateDate;

    /**
     * 是否更改日期
     */
    private Boolean isChangeDate;

    /**
     * 测算分类
     */
    private String measureType;

    /**
     * 测算是否通过
     */
    private Boolean measureIsThough;

    /**
     * 运营商名称
     */
    private String operatorName;

    /**
     * 地区
     */
    private String area;

    /**
     * 外包单位
     */
    private String outUnit;

    /**
     * 类型
     */
    private String types;

    /**
     * 专业
     */
    private String majors;

    /**
     * 派工名称
     */
    private String dispatName;

    /**
     * 厂家
     */
    private String manufacturer;

    /**
     * 销售合同号
     */
    private String saleContractNo;

    /**
     * 外包合同号
     */
    private String contractNo;

    /**
     * 所属项目组
     */
    private String projectTeam;

    /**
     * 内部项目编号
     */
    private String internalProNum;

    /**
     * 内部项目名称
     */
    private String internalProName;

    /**
     * 派工情况
     */
    private DispatCondition dispatCondition;

    /**
     * 派工金额
     */
    private Double dispatAmount;

    /**
     * 实际完工状态
     */
    private ActualCompletedState actualCompletedState;

    /**
     * 完工时间
     */
    private String completedDate;

    /**
     * 可结算金额
     */
    private Double payableAmount;

    /**
     * 本次预计结算金额
     */
    private Double estimSettleAmount;

    /**
     * 剩余未结算金额
     */
    private Double remaOutAmount;

    /**
     * 已到帐金额
     */
    private Double accountAmount;

    /**
     * 到账时间
     */
    private String accountDate;

    /**
     * 是否全部结算完成
     */
    private Boolean allSettleComple;

    /**
     * 分批结算
     */
    private String partialSettle;

    /**
     * 预计开票时间
     */
    private String expecMakeInvoie;

    /**
     * 扣除违约金后金额
     */
    private Double damagesShall;

    /**
     * 扣除管理费后金额
     */
    private Double afterDeduction;

    /**
     * 督导未结
     */
    private Double superviseOutstan;

    /**
     * 状态
     */
    private String status;

    /**
     * 派工条目
     */
    private String dispatchingItems;

    /**
     * 单位
     */
    private String unit;

    /**
     * 工程类型
     */
    private String projectType;

    /**
     * 工期
     */
    private String timeLimit;

    /**
     * 数量
     */
    private Integer numbers;

    /**
     * 单价
     */
    private Double unitPrice;

    /**
     * 站点
     */
    private String site;

    /**
     * 已完工未做结算
     */
    private Double settleComplete;

    /**
     * 进度
     */
    private String progress;

    /**
     * 到货时间
     */
    private String arrivalTime;

    /**
     * KPI
     */
    private Kpi kpi;

    /**
     * 现场实际情况（KPI）
     */
    private String actualSituation;

    /**
     * 设备型号
     */
    private String equipmentModel;

    /**
     * 大概描述项目派工的情况备注
     */
    private String descriptionRemark;

    /**
     * 总规模数
     */
    private Integer totalScaleNum;

    /**
     * 是否可制作申请结算
     */
    private Boolean settleMadeApple;

    /**
     * 是否影响结算
     */
    private Boolean affectSettlement;

    /**
     * 结算计划
     */
    private SettlementPlan settlementPlan;

    /**
     * 正在执行项目
     */
    private String underImplemen;

    /**
     * 归属
     */
    private String attribution;

    /**
     * 备注
     */
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public Boolean getChangeDate() {
        return isChangeDate;
    }

    public void setChangeDate(Boolean changeDate) {
        isChangeDate = changeDate;
    }

    public String getMeasureType() {
        return measureType;
    }

    public void setMeasureType(String measureType) {
        this.measureType = measureType;
    }

    public Boolean getMeasureIsThough() {
        return measureIsThough;
    }

    public void setMeasureIsThough(Boolean measureIsThough) {
        this.measureIsThough = measureIsThough;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getOutUnit() {
        return outUnit;
    }

    public void setOutUnit(String outUnit) {
        this.outUnit = outUnit;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getMajors() {
        return majors;
    }

    public void setMajors(String majors) {
        this.majors = majors;
    }

    public String getDispatName() {
        return dispatName;
    }

    public void setDispatName(String dispatName) {
        this.dispatName = dispatName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getSaleContractNo() {
        return saleContractNo;
    }

    public void setSaleContractNo(String saleContractNo) {
        this.saleContractNo = saleContractNo;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getProjectTeam() {
        return projectTeam;
    }

    public void setProjectTeam(String projectTeam) {
        this.projectTeam = projectTeam;
    }

    public String getInternalProNum() {
        return internalProNum;
    }

    public void setInternalProNum(String internalProNum) {
        this.internalProNum = internalProNum;
    }

    public String getInternalProName() {
        return internalProName;
    }

    public void setInternalProName(String internalProName) {
        this.internalProName = internalProName;
    }

    public DispatCondition getDispatCondition() {
        return dispatCondition;
    }

    public void setDispatCondition(DispatCondition dispatCondition) {
        this.dispatCondition = dispatCondition;
    }

    public Double getDispatAmount() {
        return dispatAmount;
    }

    public void setDispatAmount(Double dispatAmount) {
        this.dispatAmount = dispatAmount;
    }

    public ActualCompletedState getActualCompletedState() {
        return actualCompletedState;
    }

    public void setActualCompletedState(ActualCompletedState actualCompletedState) {
        this.actualCompletedState = actualCompletedState;
    }

    public String getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(String completedDate) {
        this.completedDate = completedDate;
    }

    public Double getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(Double payableAmount) {
        this.payableAmount = payableAmount;
    }

    public Double getEstimSettleAmount() {
        return estimSettleAmount;
    }

    public void setEstimSettleAmount(Double estimSettleAmount) {
        this.estimSettleAmount = estimSettleAmount;
    }

    public Double getRemaOutAmount() {
        return remaOutAmount;
    }

    public void setRemaOutAmount(Double remaOutAmount) {
        this.remaOutAmount = remaOutAmount;
    }

    public Double getAccountAmount() {
        return accountAmount;
    }

    public void setAccountAmount(Double accountAmount) {
        this.accountAmount = accountAmount;
    }

    public String getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(String accountDate) {
        this.accountDate = accountDate;
    }

    public Boolean getAllSettleComple() {
        return allSettleComple;
    }

    public void setAllSettleComple(Boolean allSettleComple) {
        this.allSettleComple = allSettleComple;
    }

    public String getPartialSettle() {
        return partialSettle;
    }

    public void setPartialSettle(String partialSettle) {
        this.partialSettle = partialSettle;
    }

    public String getExpecMakeInvoie() {
        return expecMakeInvoie;
    }

    public void setExpecMakeInvoie(String expecMakeInvoie) {
        this.expecMakeInvoie = expecMakeInvoie;
    }

    public Double getDamagesShall() {
        return damagesShall;
    }

    public void setDamagesShall(Double damagesShall) {
        this.damagesShall = damagesShall;
    }

    public Double getAfterDeduction() {
        return afterDeduction;
    }

    public void setAfterDeduction(Double afterDeduction) {
        this.afterDeduction = afterDeduction;
    }

    public Double getSuperviseOutstan() {
        return superviseOutstan;
    }

    public void setSuperviseOutstan(Double superviseOutstan) {
        this.superviseOutstan = superviseOutstan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDispatchingItems() {
        return dispatchingItems;
    }

    public void setDispatchingItems(String dispatchingItems) {
        this.dispatchingItems = dispatchingItems;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Integer getNumbers() {
        return numbers;
    }

    public void setNumbers(Integer numbers) {
        this.numbers = numbers;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Double getSettleComplete() {
        return settleComplete;
    }

    public void setSettleComplete(Double settleComplete) {
        this.settleComplete = settleComplete;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Kpi getKpi() {
        return kpi;
    }

    public void setKpi(Kpi kpi) {
        this.kpi = kpi;
    }

    public String getActualSituation() {
        return actualSituation;
    }

    public void setActualSituation(String actualSituation) {
        this.actualSituation = actualSituation;
    }

    public String getEquipmentModel() {
        return equipmentModel;
    }

    public void setEquipmentModel(String equipmentModel) {
        this.equipmentModel = equipmentModel;
    }

    public String getDescriptionRemark() {
        return descriptionRemark;
    }

    public void setDescriptionRemark(String descriptionRemark) {
        this.descriptionRemark = descriptionRemark;
    }

    public Integer getTotalScaleNum() {
        return totalScaleNum;
    }

    public void setTotalScaleNum(Integer totalScaleNum) {
        this.totalScaleNum = totalScaleNum;
    }

    public Boolean getSettleMadeApple() {
        return settleMadeApple;
    }

    public void setSettleMadeApple(Boolean settleMadeApple) {
        this.settleMadeApple = settleMadeApple;
    }

    public Boolean getAffectSettlement() {
        return affectSettlement;
    }

    public void setAffectSettlement(Boolean affectSettlement) {
        this.affectSettlement = affectSettlement;
    }

    public SettlementPlan getSettlementPlan() {
        return settlementPlan;
    }

    public void setSettlementPlan(SettlementPlan settlementPlan) {
        this.settlementPlan = settlementPlan;
    }

    public String getUnderImplemen() {
        return underImplemen;
    }

    public void setUnderImplemen(String underImplemen) {
        this.underImplemen = underImplemen;
    }

    public String getAttribution() {
        return attribution;
    }

    public void setAttribution(String attribution) {
        this.attribution = attribution;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}