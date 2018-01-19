package com.bjike.goddess.projectcalculation.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.api.type.Status;

/**
 * 项目测算明细
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-05 02:48 ]
 * @Description: [ 项目测算明细 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CalculationDetailTO extends BaseTO {

    /**
     * 计划测算时间
     */
    private String calculationTime;

    /**
     * 年份
     */
    private String year;

    /**
     * 地区
     */
    private String area;

    /**
     * 是否转换商机
     */
    private Boolean conversionBuissOpp;

    /**
     * 市场信息编号
     */
    private String marketInfoNum;

    /**
     * 测算项目名称
     */
    private String projectName;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 业务方向
     */
    private String businessDirection;

    /**
     * 参加竞标单位
     */
    private String bidUnit;

    /**
     * 需求
     */
    private String demand;

    /**
     * 工期
     */
    private String timeForProject;

    /**
     * 发起时间
     */
    private String initiateDate;

    /**
     * 结束时间
     */
    private String endDate;

    /**
     * 合作方式
     */
    private String cooperationWay;

    /**
     * 合同金额
     */
    private Double contractAmount;

    /**
     * 专业
     */
    private String majors;

    /**
     * 技术人员数量
     */
    private Integer peopelNum;

    /**
     * 技能人员是否能储备/调配
     */
    private Boolean artisanAllocate;

    /**
     * 管理人员是否能储备/调配
     */
    private Boolean administratorAllocate;

    /**
     * 规模数量
     */
    private Double scale;

    /**
     * 预估金额
     */
    private Double estimatedAmount;

    /**
     * 设备要求
     */
    private String configRequirements;

    /**
     * 设备调配/库存是否满足
     */
    private Boolean equipmentAllocate;

    /**
     * 车辆要求
     */
    private Double vehicleCost;

    /**
     * 车辆是否有可调配资源或者储备资源
     */
    private Boolean vehicleAllocate;

    /**
     * 预估人工费用
     */
    private Double estimateArtificialCost;

    /**
     * 预估设备费用
     */
    private Double estimateEquipmentCost;

    /**
     * 预估车辆费用
     */
    private Double estimateVehicleCost;

    /**
     * 预估总成本费用
     */
    private Double estimateTheTotalCost;

    /**
     * 结算形式
     */
    private String formOfSettlement;

    /**
     * 结算方式
     */
    private String payWay;

    /**
     * 预估利润
     */
    private Double forecastProfit;

    /**
     * 资金是否可以支撑
     */
    private Boolean capitalSupport;

    /**
     * 测算进度
     */
    private String calculationProgress;

    /**
     * 测算完成时间
     */
    private String calculationFinishTime;

    /**
     * 测算分类
     */
    private String calculationType;

    /**
     * 测算是否通过
     */
    private Boolean calculationPass;

    /**
     * 测算通过时间
     */
    private String calculationPassTime;

    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCalculationTime() {
        return calculationTime;
    }

    public void setCalculationTime(String calculationTime) {
        this.calculationTime = calculationTime;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
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

    public String getInitiateDate() {
        return initiateDate;
    }

    public void setInitiateDate(String initiateDate) {
        this.initiateDate = initiateDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCooperationWay() {
        return cooperationWay;
    }

    public void setCooperationWay(String cooperationWay) {
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

    public String getCalculationFinishTime() {
        return calculationFinishTime;
    }

    public void setCalculationFinishTime(String calculationFinishTime) {
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

    public String getCalculationPassTime() {
        return calculationPassTime;
    }

    public void setCalculationPassTime(String calculationPassTime) {
        this.calculationPassTime = calculationPassTime;
    }
}