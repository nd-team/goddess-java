package com.bjike.goddess.projectprocing.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.projectprocing.enums.ActualCompletedState;
import com.bjike.goddess.projectprocing.enums.DispatCondition;
import com.bjike.goddess.projectprocing.enums.Kpi;
import com.bjike.goddess.projectprocing.enums.SettlementPlan;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 结算进度管理
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 02:22 ]
 * @Description: [ 结算进度管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectprocing_settleprogressmanage")
public class SettleProgressManage extends BaseEntity {

    /**
     * 更改日期
     */
    @Column(name = "updateDate", columnDefinition = "DATETIME   COMMENT '更改日期'")
    private LocalDateTime updateDate;

    /**
     * 是否更改日期
     */
    @Column(name = "is_isChangeDate",  columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否更改日期'", insertable = false)
    private Boolean isChangeDate;

    /**
     * 测算分类
     */
    @Column(name = "measureType", columnDefinition = "VARCHAR(255)   COMMENT '测算分类'")
    private String measureType;

    /**
     * 测算是否通过
     */
    @Column(name = "is_measureIsThough", columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '测算是否通过'", insertable = false)
    private Boolean measureIsThough;

    /**
     * 运营商名称
     */
    @Column(name = "operatorName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '运营商名称'")
    private String operatorName;

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 外包单位
     */
    @Column(name = "outUnit", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '外包单位'")
    private String outUnit;

    /**
     * 类型
     */
    @Column(name = "types", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '类型'")
    private String types;

    /**
     * 专业
     */
    @Column(name = "majors", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '专业'")
    private String majors;

    /**
     * 派工名称
     */
    @Column(name = "dispatName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '派工名称'")
    private String dispatName;

    /**
     * 厂家
     */
    @Column(name = "manufacturer", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '厂家'")
    private String manufacturer;

    /**
     * 销售合同号
     */
    @Column(name = "saleContractNo", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '销售合同号'")
    private String saleContractNo;

    /**
     * 外包合同号
     */
    @Column(name = "contractNo", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '外包合同号'")
    private String contractNo;

    /**
     * 所属项目组
     */
    @Column(name = "projectTeam", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '所属项目组'")
    private String projectTeam;

    /**
     * 内部项目编号
     */
    @Column(name = "internalProNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '内部项目编号'")
    private String internalProNum;

    /**
     * 内部项目名称
     */
    @Column(name = "internalProName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '内部项目名称'")
    private String internalProName;

    /**
     * 派工情况
     */
    @Column(name = "dispatCondition", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '派工情况'")
    private DispatCondition dispatCondition;

    /**
     * 派工金额
     */
    @Column(name = "dispatAmount", columnDefinition = "DECIMAL(10,2)   COMMENT '派工金额'")
    private Double dispatAmount;

    /**
     * 实际完工状态
     */
    @Column(name = "actualCompletedState", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '实际完工状态'")
    private ActualCompletedState actualCompletedState;

    /**
     * 完工时间
     */
    @Column(name = "completedDate", columnDefinition = "DATE   COMMENT '完工时间'")
    private LocalDate completedDate;

    /**
     * 可结算金额
     */
    @Column(name = "payableAmount", columnDefinition = "DECIMAL(10,2)   COMMENT '可结算金额'")
    private Double payableAmount;

    /**
     * 本次预计结算金额
     */
    @Column(name = "estimSettleAmount", columnDefinition = "DECIMAL(10,2)   COMMENT '本次预计结算金额'")
    private Double estimSettleAmount;

    /**
     * 剩余未结算金额
     */
    @Column(name = "remaOutAmount", columnDefinition = "DECIMAL(10,2)   COMMENT '剩余未结算金额'")
    private Double remaOutAmount;

    /**
     * 已到帐金额
     */
    @Column(name = "accountAmount", columnDefinition = "DECIMAL(10,2)   COMMENT '已到帐金额'")
    private Double accountAmount;

    /**
     * 到账时间
     */
    @Column(name = "accountDate", columnDefinition = "DATE   COMMENT '到账时间'")
    private LocalDate accountDate;

    /**
     * 是否全部结算完成
     */
    @Column(name = "is_allSettleComple", columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否全部结算完成'", insertable = false)
    private Boolean allSettleComple;

    /**
     * 分批结算
     */
    @Column(name = "partialSettle", columnDefinition = "VARCHAR(255)   COMMENT '分批结算'")
    private String partialSettle;

    /**
     * 预计开票时间
     */
    @Column(name = "expecMakeInvoie", columnDefinition = "DATE   COMMENT '预计开票时间'")
    private LocalDate expecMakeInvoie;

    /**
     * 扣除违约金后金额
     */
    @Column(name = "damagesShall", columnDefinition = "DECIMAL(10,2)   COMMENT '扣除违约金后金额'")
    private Double damagesShall;

    /**
     * 扣除管理费后金额
     */
    @Column(name = "afterDeduction", columnDefinition = "DECIMAL(10,2)   COMMENT '扣除管理费后金额'")
    private Double afterDeduction;

    /**
     * 督导未结
     */
    @Column(name = "superviseOutstan", columnDefinition = "DECIMAL(10,2)   COMMENT '督导未结'")
    private Double superviseOutstan;

    /**
     * 状态
     */
    @Column(name = "status", columnDefinition = "VARCHAR(255)   COMMENT '状态'")
    private String status;

    /**
     * 派工条目
     */
    @Column(name = "dispatchingItems", columnDefinition = "VARCHAR(255)   COMMENT '派工条目'")
    private String dispatchingItems;

    /**
     * 单位
     */
    @Column(name = "unit", columnDefinition = "VARCHAR(255)   COMMENT '单位'")
    private String unit;

    /**
     * 工程类型
     */
    @Column(name = "projectType", columnDefinition = "VARCHAR(255)   COMMENT '工程类型'")
    private String projectType;

    /**
     * 工期
     */
    @Column(name = "timeLimit", columnDefinition = "VARCHAR(255)   COMMENT '工期'")
    private String timeLimit;

    /**
     * 数量
     */
    @Column(name = "numbers", columnDefinition = "INT(11)   COMMENT '数量'")
    private Integer numbers;

    /**
     * 单价
     */
    @Column(name = "unitPrice", columnDefinition = "DECIMAL(10,2)   COMMENT '单价'")
    private Double unitPrice;

    /**
     * 站点
     */
    @Column(name = "site", columnDefinition = "VARCHAR(255)   COMMENT '站点'")
    private String site;

    /**
     * 已完工未做结算
     */
    @Column(name = "settleComplete", columnDefinition = "DECIMAL(10,2)   COMMENT '已完工未做结算'")
    private Double settleComplete;

    /**
     * 进度
     */
    @Column(name = "progress", columnDefinition = "VARCHAR(255)   COMMENT '进度'")
    private String progress;

    /**
     * 到货时间
     */
    @Column(name = "arrivalTime", columnDefinition = "DATE   COMMENT '到货时间'")
    private LocalDate arrivalTime;

    /**
     * KPI
     */
    @Column(name = "kpi", nullable = false, columnDefinition = "INT(11)   COMMENT 'KPI'")
    private Kpi kpi;

    /**
     * 现场实际情况（KPI）
     */
    @Column(name = "actualSituation", columnDefinition = "VARCHAR(255)   COMMENT '现场实际情况（KPI）'")
    private String actualSituation;

    /**
     * 设备型号
     */
    @Column(name = "equipmentModel", columnDefinition = "VARCHAR(255)   COMMENT '设备型号'")
    private String equipmentModel;

    /**
     * 大概描述项目派工的情况备注
     */
    @Column(name = "descriptionRemark", columnDefinition = "VARCHAR(255)   COMMENT '大概描述项目派工的情况备注'")
    private String descriptionRemark;

    /**
     * 总规模数
     */
    @Column(name = "totalScaleNum", columnDefinition = "INT(11)   COMMENT '总规模数'")
    private Integer totalScaleNum;

    /**
     * 是否可制作申请结算
     */
    @Column(name = "settleMadeApple", columnDefinition = "TINYINT(2)   COMMENT '是否可制作申请结算'")
    private Boolean settleMadeApple;

    /**
     * 是否影响结算
     */
    @Column(name = "affectSettlement", columnDefinition = "TINYINT(2)   COMMENT '是否影响结算'")
    private Boolean affectSettlement;

    /**
     * 结算计划
     */
    @Column(name = "settlementPlan", columnDefinition = "VARCHAR(255)   COMMENT '结算计划'")
    private SettlementPlan settlementPlan;

    /**
     * 正在执行项目
     */
    @Column(name = "underImplemen", columnDefinition = "VARCHAR(255)   COMMENT '正在执行项目'")
    private String underImplemen;

    /**
     * 归属
     */
    @Column(name = "attribution", columnDefinition = "VARCHAR(255)   COMMENT '归属'")
    private String attribution;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
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

    public LocalDate getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(LocalDate completedDate) {
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

    public LocalDate getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(LocalDate accountDate) {
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

    public LocalDate getExpecMakeInvoie() {
        return expecMakeInvoie;
    }

    public void setExpecMakeInvoie(LocalDate expecMakeInvoie) {
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

    public LocalDate getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDate arrivalTime) {
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