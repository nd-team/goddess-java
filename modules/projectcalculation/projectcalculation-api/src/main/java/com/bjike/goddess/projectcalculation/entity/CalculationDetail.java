package com.bjike.goddess.projectcalculation.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.market.enums.CooperationWay;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 项目测算明细
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-05 02:48 ]
 * @Description: [ 项目测算明细 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectcalculation_calculationdetail")
public class CalculationDetail extends BaseEntity {

    /**
     * 计划测算时间
     */
    @Column(name = "calculationTime", nullable = false, columnDefinition = "DATE   COMMENT '计划测算时间'")
    private LocalDate calculationTime;

    /**
     * 年份
     */
    @Column(name = "year", nullable = false, columnDefinition = "DATE   COMMENT '年份'")
    private LocalDate year;

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 是否转换商机
     */
    @Column(name = "is_conversionBuissOpp", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否转换商机'", insertable = false)
    private Boolean conversionBuissOpp;

    /**
     * 市场信息编号
     */
    @Column(name = "marketInfoNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '市场信息编号'")
    private String marketInfoNum;

    /**
     * 测算项目名称
     */
    @Column(name = "projectName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '测算项目名称'")
    private String projectName;

    /**
     * 业务类型
     */
    @Column(name = "businessType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务类型'")
    private String businessType;

    /**
     * 业务方向
     */
    @Column(name = "businessDirection", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务方向'")
    private String businessDirection;

    /**
     * 参加竞标单位
     */
    @Column(name = "bidUnit", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '参加竞标单位'")
    private String bidUnit;

    /**
     * 需求
     */
    @Column(name = "demand", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '需求'")
    private String demand;

    /**
     * 工期
     */
    @Column(name = "timeForProject", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '工期'")
    private String timeForProject;

    /**
     * 发起时间
     */
    @Column(name = "initiateDate", nullable = false, columnDefinition = "DATE   COMMENT '发起时间'")
    private LocalDate initiateDate;

    /**
     * 结束时间
     */
    @Column(name = "endDate", nullable = false, columnDefinition = "DATE   COMMENT '结束时间'")
    private LocalDate endDate;

    /**
     * 合作方式
     */
    @Column(name = "cooperationWay", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '合作方式'")
    private CooperationWay cooperationWay;

    /**
     * 合同金额
     */
    @Column(name = "contractAmount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '合同金额'")
    private Double contractAmount;

    /**
     * 专业
     */
    @Column(name = "majors", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '专业'")
    private String majors;

    /**
     * 技术人员数量
     */
    @Column(name = "peopelNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '技术人员数量'")
    private Integer peopelNum;

    /**
     * 技能人员是否能储备/调配
     */
    @Column(name = "is_artisanAllocate", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '技能人员是否能储备/调配'", insertable = false)
    private Boolean artisanAllocate;

    /**
     * 管理人员是否能储备/调配
     */
    @Column(name = "is_administratorAllocate", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '管理人员是否能储备/调配'", insertable = false)
    private Boolean administratorAllocate;

    /**
     * 规模数量
     */
    @Column(name = "scale", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '规模数量'")
    private Double scale;

    /**
     * 预估金额
     */
    @Column(name = "estimatedAmount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '预估金额'")
    private Double estimatedAmount;

    /**
     * 设备要求
     */
    @Column(name = "configRequirements", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '设备要求'")
    private String configRequirements;

    /**
     * 设备调配/库存是否满足
     */
    @Column(name = "is_equipmentAllocate", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '设备调配/库存是否满足'", insertable = false)
    private Boolean equipmentAllocate;

    /**
     * 车辆要求
     */
    @Column(name = "vehicleCost", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '车辆要求'")
    private Double vehicleCost;

    /**
     * 车辆是否有可调配资源或者储备资源
     */
    @Column(name = "is_vehicleAllocate", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '车辆是否有可调配资源或者储备资源'", insertable = false)
    private Boolean vehicleAllocate;

    /**
     * 预估人工费用
     */
    @Column(name = "estimateArtificialCost", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '预估人工费用'")
    private Double estimateArtificialCost;

    /**
     * 预估设备费用
     */
    @Column(name = "estimateEquipmentCost", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '预估设备费用'")
    private Double estimateEquipmentCost;

    /**
     * 预估车辆费用
     */
    @Column(name = "estimateVehicleCost", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '预估车辆费用'")
    private Double estimateVehicleCost;

    /**
     * 预估总成本费用
     */
    @Column(name = "estimateTheTotalCost", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '预估总成本费用'")
    private Double estimateTheTotalCost;

    /**
     * 结算形式
     */
    @Column(name = "formOfSettlement", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '结算形式'")
    private String formOfSettlement;

    /**
     * 结算方式
     */
    @Column(name = "payWay", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '结算方式'")
    private String payWay;

    /**
     * 预估利润
     */
    @Column(name = "forecastProfit", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '预估利润'")
    private Double forecastProfit;

    /**
     * 资金是否可以支撑
     */
    @Column(name = "is_capitalSupport", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '资金是否可以支撑'", insertable = false)
    private Boolean capitalSupport;

    /**
     * 测算进度
     */
    @Column(name = "calculationProgress", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '测算进度'")
    private String calculationProgress;

    /**
     * 测算完成时间
     */
    @Column(name = "calculationFinishTime", nullable = false, columnDefinition = "DATE   COMMENT '测算完成时间'")
    private LocalDate calculationFinishTime;

    /**
     * 测算分类
     */
    @Column(name = "calculationType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '测算分类'")
    private String calculationType;

    /**
     * 测算是否通过
     */
    @Column(name = "is_calculationPass", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '测算是否通过'", insertable = false)
    private Boolean calculationPass;

    /**
     * 测算通过时间
     */
    @Column(name = "calculationPassTime", nullable = false, columnDefinition = "DATE   COMMENT '测算通过时间'")
    private LocalDate calculationPassTime;


    @Column(name = "status",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '是否冻结'"  )
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getCalculationTime() {
        return calculationTime;
    }

    public void setCalculationTime(LocalDate calculationTime) {
        this.calculationTime = calculationTime;
    }

    public LocalDate getYear() {
        return year;
    }

    public void setYear(LocalDate year) {
        this.year = year;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Boolean getConversionBuissOpp() {
        return conversionBuissOpp;
    }

    public void setConversionBuissOpp(Boolean conversionBuissOpp) {
        this.conversionBuissOpp = conversionBuissOpp;
    }

    public String getMarketInfoNum() {
        return marketInfoNum;
    }

    public void setMarketInfoNum(String marketInfoNum) {
        this.marketInfoNum = marketInfoNum;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBusinessDirection() {
        return businessDirection;
    }

    public void setBusinessDirection(String businessDirection) {
        this.businessDirection = businessDirection;
    }

    public String getBidUnit() {
        return bidUnit;
    }

    public void setBidUnit(String bidUnit) {
        this.bidUnit = bidUnit;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public String getTimeForProject() {
        return timeForProject;
    }

    public void setTimeForProject(String timeForProject) {
        this.timeForProject = timeForProject;
    }

    public LocalDate getInitiateDate() {
        return initiateDate;
    }

    public void setInitiateDate(LocalDate initiateDate) {
        this.initiateDate = initiateDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public CooperationWay getCooperationWay() {
        return cooperationWay;
    }

    public void setCooperationWay(CooperationWay cooperationWay) {
        this.cooperationWay = cooperationWay;
    }

    public Double getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(Double contractAmount) {
        this.contractAmount = contractAmount;
    }

    public String getMajors() {
        return majors;
    }

    public void setMajors(String majors) {
        this.majors = majors;
    }

    public Integer getPeopelNum() {
        return peopelNum;
    }

    public void setPeopelNum(Integer peopelNum) {
        this.peopelNum = peopelNum;
    }

    public Boolean getArtisanAllocate() {
        return artisanAllocate;
    }

    public void setArtisanAllocate(Boolean artisanAllocate) {
        this.artisanAllocate = artisanAllocate;
    }

    public Boolean getAdministratorAllocate() {
        return administratorAllocate;
    }

    public void setAdministratorAllocate(Boolean administratorAllocate) {
        this.administratorAllocate = administratorAllocate;
    }

    public Double getScale() {
        return scale;
    }

    public void setScale(Double scale) {
        this.scale = scale;
    }

    public Double getEstimatedAmount() {
        return estimatedAmount;
    }

    public void setEstimatedAmount(Double estimatedAmount) {
        this.estimatedAmount = estimatedAmount;
    }

    public String getConfigRequirements() {
        return configRequirements;
    }

    public void setConfigRequirements(String configRequirements) {
        this.configRequirements = configRequirements;
    }

    public Boolean getEquipmentAllocate() {
        return equipmentAllocate;
    }

    public void setEquipmentAllocate(Boolean equipmentAllocate) {
        this.equipmentAllocate = equipmentAllocate;
    }

    public Double getVehicleCost() {
        return vehicleCost;
    }

    public void setVehicleCost(Double vehicleCost) {
        this.vehicleCost = vehicleCost;
    }

    public Boolean getVehicleAllocate() {
        return vehicleAllocate;
    }

    public void setVehicleAllocate(Boolean vehicleAllocate) {
        this.vehicleAllocate = vehicleAllocate;
    }

    public Double getEstimateArtificialCost() {
        return estimateArtificialCost;
    }

    public void setEstimateArtificialCost(Double estimateArtificialCost) {
        this.estimateArtificialCost = estimateArtificialCost;
    }

    public Double getEstimateEquipmentCost() {
        return estimateEquipmentCost;
    }

    public void setEstimateEquipmentCost(Double estimateEquipmentCost) {
        this.estimateEquipmentCost = estimateEquipmentCost;
    }

    public Double getEstimateVehicleCost() {
        return estimateVehicleCost;
    }

    public void setEstimateVehicleCost(Double estimateVehicleCost) {
        this.estimateVehicleCost = estimateVehicleCost;
    }

    public Double getEstimateTheTotalCost() {
        return estimateTheTotalCost;
    }

    public void setEstimateTheTotalCost(Double estimateTheTotalCost) {
        this.estimateTheTotalCost = estimateTheTotalCost;
    }

    public String getFormOfSettlement() {
        return formOfSettlement;
    }

    public void setFormOfSettlement(String formOfSettlement) {
        this.formOfSettlement = formOfSettlement;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public Double getForecastProfit() {
        return forecastProfit;
    }

    public void setForecastProfit(Double forecastProfit) {
        this.forecastProfit = forecastProfit;
    }

    public Boolean getCapitalSupport() {
        return capitalSupport;
    }

    public void setCapitalSupport(Boolean capitalSupport) {
        this.capitalSupport = capitalSupport;
    }

    public String getCalculationProgress() {
        return calculationProgress;
    }

    public void setCalculationProgress(String calculationProgress) {
        this.calculationProgress = calculationProgress;
    }

    public LocalDate getCalculationFinishTime() {
        return calculationFinishTime;
    }

    public void setCalculationFinishTime(LocalDate calculationFinishTime) {
        this.calculationFinishTime = calculationFinishTime;
    }

    public String getCalculationType() {
        return calculationType;
    }

    public void setCalculationType(String calculationType) {
        this.calculationType = calculationType;
    }

    public Boolean getCalculationPass() {
        return calculationPass;
    }

    public void setCalculationPass(Boolean calculationPass) {
        this.calculationPass = calculationPass;
    }

    public LocalDate getCalculationPassTime() {
        return calculationPassTime;
    }

    public void setCalculationPassTime(LocalDate calculationPassTime) {
        this.calculationPassTime = calculationPassTime;
    }
}