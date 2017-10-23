package com.bjike.goddess.market.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.market.enums.CooperationWay;
import com.bjike.goddess.market.enums.ProjectType;
import com.bjike.goddess.market.enums.SourceInfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 市场信息记录
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-20 11:50 ]
 * @Description: [ 市场信息记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "market_marketinforecord")
public class MarketInfoRecord extends BaseEntity {

    /**
     * 市场信息编号
     */
    @Column(name = "marketInfoNum", nullable = false,unique = true ,columnDefinition = "VARCHAR(255)   COMMENT '市场信息编号'")
    private String marketInfoNum;

    /**
     * 信息收集日期
     */
    @Column(name = "infoCollectDate", nullable = false, columnDefinition = "DATE   COMMENT '信息收集日期'")
    private LocalDate infoCollectDate;

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
     * 专业
     */
    @Column(name = "majors", columnDefinition = "VARCHAR(255)   COMMENT '专业'")
    private String majors;

    /**
     * 合作方式
     */
    @Column(name = "cooperationWay", columnDefinition = "TINYINT(2)  COMMENT '合作方式'")
    private CooperationWay cooperationWay;

    /**
     * 项目类型
     */
    @Column(name = "projectType", columnDefinition = "TINYINT(2)   COMMENT '项目类型'")
    private ProjectType projectType;

    /**
     * 需求
     */
    @Column(name = "demand", columnDefinition = "TEXT   COMMENT '需求'")
    private String demand;

    /**
     * 合同金额
     */
    @Column(name = "contractAmount", columnDefinition = "DECIMAL(10,2)   COMMENT '合同金额'")
    private Double contractAmount;

    /**
     * 项目名称
     */
    @Column(name = "projectName", columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String projectName;

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 地区框架
     */
    @Column(name = "areaFrames", columnDefinition = "TEXT   COMMENT '地区框架'")
    private String areaFrames;

    /**
     * 参加竞标单位
     */
    @Column(name = "bidUnit", columnDefinition = "VARCHAR(255)   COMMENT '参加竞标单位'")
    private String bidUnit;

    /**
     * 信息来源
     */
    @Column(name = "sourceInfo", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '信息来源'")
    private SourceInfo sourceInfo;

    /**
     * 合作项目区域分布
     */
    @Column(name = "coopProjectAreaDistr", columnDefinition = "VARCHAR(255)   COMMENT '合作项目区域分布'")
    private String coopProjectAreaDistr;

    /**
     * 以前合作过的公司
     */
    @Column(name = "beforeCoopCompan", columnDefinition = "VARCHAR(255)   COMMENT '以前合作过的公司'")
    private String beforeCoopCompan;

    /**
     * 现业务有关联公司
     */
    @Column(name = "businessAffiliate", columnDefinition = "VARCHAR(255)   COMMENT '现业务有关联公司'")
    private String businessAffiliate;

    /**
     * 未来发展方向
     */
    @Column(name = "futureDevelopmentDire", columnDefinition = "VARCHAR(255)   COMMENT '未来发展方向'")
    private String futureDevelopmentDire;

    /**
     * 信息收集人
     */
    @Column(name = "infoCollectPepol", columnDefinition = "VARCHAR(255)   COMMENT '信息收集人'")
    private String infoCollectPepol;

    /**
     * 总金额
     */
    @Column(name = "totalAmount", columnDefinition = "DECIMAL(10,2)   COMMENT '总金额'")
    private Double totalAmount;

    /**
     * 工作量/规模数量
     */
    @Column(name = "workload", columnDefinition = "INT(11)   COMMENT '工作量/规模数量'")
    private Integer workload;

    /**
     * 人员数量
     */
    @Column(name = "peopelNum", columnDefinition = "INT(11)   COMMENT '人员数量'")
    private Integer peopelNum;

    /**
     * 配置要求
     */
    @Column(name = "configRequirements", columnDefinition = "VARCHAR(255)   COMMENT '配置要求'")
    private String configRequirements;

    /**
     * 设备费用
     */
    @Column(name = "equipmentCost", columnDefinition = "DECIMAL(10,2)   COMMENT '设备费用'")
    private Double equipmentCost;

    /**
     * 车辆费用
     */
    @Column(name = "vehicleCost", columnDefinition = "DECIMAL(10,2)   COMMENT '车辆费用'")
    private Double vehicleCost;

    /**
     * 服务费用
     */
    @Column(name = "serviceCost", columnDefinition = "DECIMAL(10,2)   COMMENT '服务费用'")
    private Double serviceCost;

    /**
     * 配置费用
     */
    @Column(name = "configCost", columnDefinition = "DECIMAL(10,2)   COMMENT '配置费用'")
    private Double configCost;

    /**
     * 资质要求
     */
    @Column(name = "qualificationRequi", columnDefinition = "VARCHAR(255)   COMMENT '资质要求'")
    private String qualificationRequi;

    /**
     * 发起时间
     */
    @Column(name = "initiateDate", columnDefinition = "DATE   COMMENT '发起时间'")
    private LocalDate initiateDate;

    /**
     * 时效开始时间
     */
    @Column(name = "agingStartTime",nullable = false ,columnDefinition = "DATE   COMMENT '时效开始时间'")
    private LocalDate agingStartTime;

    /**
     * 时效结束时间
     */
    @Column(name = "agingEndTime",nullable = false, columnDefinition = "DATE   COMMENT '时效结束时间'")
    private LocalDate agingEndTime;

    /**
     * 结束时间
     */
    @Column(name = "endDate", columnDefinition = "DATE   COMMENT '结束时间'")
    private LocalDate endDate;

    /**
     * 付款形式
     */
    @Column(name = "payWay", columnDefinition = "VARCHAR(255)   COMMENT '付款形式'")
    private String payWay;

    /**
     * 发票类型
     */
    @Column(name = "invoiceType", columnDefinition = "VARCHAR(255)   COMMENT '发票类型'")
    private String invoiceType;

    /**
     * 回款时间
     */
    @Column(name = "receivableDate", columnDefinition = "DATE   COMMENT '回款时间'")
    private LocalDate receivableDate;

    /**
     * 回款方式
     */
    @Column(name = "receivableWay", columnDefinition = "VARCHAR(255)   COMMENT '回款方式'")
    private String receivableWay;

    /**
     * 汇款内容
     */
    @Column(name = "remittanceContent", columnDefinition = "VARCHAR(255)   COMMENT '汇款内容'")
    private String remittanceContent;

    /**
     * 是否为有效信息
     */
    @Column(name = "is_effctiveInfo", columnDefinition = "TINYINT(1)    COMMENT '是否为有效信息'")
    private Boolean effctiveInfo;

    /**
     * 是否进行初步分析
     */
    @Column(name = "is_preliminaryAnaly", columnDefinition = "TINYINT(1)   COMMENT '是否进行初步分析'")
    private Boolean preliminaryAnaly;

    /**
     * 内部项目名称
     */
    @Column(name = "internalProjectName", columnDefinition = "VARCHAR(255)   COMMENT '内部项目名称'")
    private String internalProjectName;

    /**
     * 客户信息编号
     */
    @Column(name = "customerInfoNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '客户信息编号'")
    private String customerInfoNum;

    /**
     * 客户姓名
     */
    @Column(name = "customerName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '客户姓名'")
    private String customerName;

    /**
     * 组织机构名称
     */
    @Column(name = "organizationName", columnDefinition = "VARCHAR(255)   COMMENT '组织机构名称'")
    private String organizationName;

    /**
     * 竞争对手名称
     */
    @Column(name = "competitorsName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '竞争对手名称'")
    private String competitorsName;

    /**
     * 客户/竞争对手信息填写人
     */
    @Column(name = "infoFillPerson", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '客户/竞争对手信息填写人'")
    private String infoFillPerson;

    /**
     * 是否转换商机
     */
    @Column(name = "is_conversionBuissOpp", columnDefinition = "TINYINT(1)  COMMENT '是否转换商机'")
    private Boolean conversionBuissOpp;

    /**
     * 是否转换为市场招待
     */
    @Column(name = "is_conversionMarketFor", columnDefinition = "TINYINT(1)  COMMENT '是否转换为市场招待'")
    private Boolean conversionMarketFor;

    /**
     * 是否转换为商务洽谈
     */
    @Column(name = "is_conversionBussNegotia", columnDefinition = "TINYINT(1)  COMMENT '是否转换为商务洽谈'")
    private Boolean conversionBussNegotia;

    /**
     * 预估转正金额
     */
    @Column(name = "estimateTransferAmount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '预估转正金额'")
    private Double estimateTransferAmount;

    /**
     * 预估市场亏损金额
     */
    @Column(name = "estimateMarketLoss", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '预估市场亏损金额'")
    private Double estimateMarketLoss;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public String getMarketInfoNum() {
        return marketInfoNum;
    }

    public void setMarketInfoNum(String marketInfoNum) {
        this.marketInfoNum = marketInfoNum;
    }

    public LocalDate getInfoCollectDate() {
        return infoCollectDate;
    }

    public void setInfoCollectDate(LocalDate infoCollectDate) {
        this.infoCollectDate = infoCollectDate;
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

    public String getMajors() {
        return majors;
    }

    public void setMajors(String majors) {
        this.majors = majors;
    }

    public CooperationWay getCooperationWay() {
        return cooperationWay;
    }

    public void setCooperationWay(CooperationWay cooperationWay) {
        this.cooperationWay = cooperationWay;
    }

    public ProjectType getProjectType() {
        return projectType;
    }

    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public Double getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(Double contractAmount) {
        this.contractAmount = contractAmount;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAreaFrames() {
        return areaFrames;
    }

    public void setAreaFrames(String areaFrames) {
        this.areaFrames = areaFrames;
    }

    public String getBidUnit() {
        return bidUnit;
    }

    public void setBidUnit(String bidUnit) {
        this.bidUnit = bidUnit;
    }

    public SourceInfo getSourceInfo() {
        return sourceInfo;
    }

    public void setSourceInfo(SourceInfo sourceInfo) {
        this.sourceInfo = sourceInfo;
    }

    public String getCoopProjectAreaDistr() {
        return coopProjectAreaDistr;
    }

    public void setCoopProjectAreaDistr(String coopProjectAreaDistr) {
        this.coopProjectAreaDistr = coopProjectAreaDistr;
    }

    public String getBeforeCoopCompan() {
        return beforeCoopCompan;
    }

    public void setBeforeCoopCompan(String beforeCoopCompan) {
        this.beforeCoopCompan = beforeCoopCompan;
    }

    public String getBusinessAffiliate() {
        return businessAffiliate;
    }

    public void setBusinessAffiliate(String businessAffiliate) {
        this.businessAffiliate = businessAffiliate;
    }

    public String getFutureDevelopmentDire() {
        return futureDevelopmentDire;
    }

    public void setFutureDevelopmentDire(String futureDevelopmentDire) {
        this.futureDevelopmentDire = futureDevelopmentDire;
    }

    public String getInfoCollectPepol() {
        return infoCollectPepol;
    }

    public void setInfoCollectPepol(String infoCollectPepol) {
        this.infoCollectPepol = infoCollectPepol;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getWorkload() {
        return workload;
    }

    public void setWorkload(Integer workload) {
        this.workload = workload;
    }

    public Integer getPeopelNum() {
        return peopelNum;
    }

    public void setPeopelNum(Integer peopelNum) {
        this.peopelNum = peopelNum;
    }

    public String getConfigRequirements() {
        return configRequirements;
    }

    public void setConfigRequirements(String configRequirements) {
        this.configRequirements = configRequirements;
    }

    public Double getEquipmentCost() {
        return equipmentCost;
    }

    public void setEquipmentCost(Double equipmentCost) {
        this.equipmentCost = equipmentCost;
    }

    public Double getVehicleCost() {
        return vehicleCost;
    }

    public void setVehicleCost(Double vehicleCost) {
        this.vehicleCost = vehicleCost;
    }

    public Double getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(Double serviceCost) {
        this.serviceCost = serviceCost;
    }

    public Double getConfigCost() {
        return configCost;
    }

    public void setConfigCost(Double configCost) {
        this.configCost = configCost;
    }

    public String getQualificationRequi() {
        return qualificationRequi;
    }

    public void setQualificationRequi(String qualificationRequi) {
        this.qualificationRequi = qualificationRequi;
    }

    public LocalDate getInitiateDate() {
        return initiateDate;
    }

    public void setInitiateDate(LocalDate initiateDate) {
        this.initiateDate = initiateDate;
    }

    public LocalDate getAgingStartTime() {
        return agingStartTime;
    }

    public void setAgingStartTime(LocalDate agingStartTime) {
        this.agingStartTime = agingStartTime;
    }

    public LocalDate getAgingEndTime() {
        return agingEndTime;
    }

    public void setAgingEndTime(LocalDate agingEndTime) {
        this.agingEndTime = agingEndTime;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public LocalDate getReceivableDate() {
        return receivableDate;
    }

    public void setReceivableDate(LocalDate receivableDate) {
        this.receivableDate = receivableDate;
    }

    public String getReceivableWay() {
        return receivableWay;
    }

    public void setReceivableWay(String receivableWay) {
        this.receivableWay = receivableWay;
    }

    public String getRemittanceContent() {
        return remittanceContent;
    }

    public void setRemittanceContent(String remittanceContent) {
        this.remittanceContent = remittanceContent;
    }

    public Boolean getEffctiveInfo() {
        return effctiveInfo;
    }

    public void setEffctiveInfo(Boolean effctiveInfo) {
        this.effctiveInfo = effctiveInfo;
    }

    public Boolean getPreliminaryAnaly() {
        return preliminaryAnaly;
    }

    public void setPreliminaryAnaly(Boolean preliminaryAnaly) {
        this.preliminaryAnaly = preliminaryAnaly;
    }

    public String getInternalProjectName() {
        return internalProjectName;
    }

    public void setInternalProjectName(String internalProjectName) {
        this.internalProjectName = internalProjectName;
    }

    public String getCustomerInfoNum() {
        return customerInfoNum;
    }

    public void setCustomerInfoNum(String customerInfoNum) {
        this.customerInfoNum = customerInfoNum;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getCompetitorsName() {
        return competitorsName;
    }

    public void setCompetitorsName(String competitorsName) {
        this.competitorsName = competitorsName;
    }

    public String getInfoFillPerson() {
        return infoFillPerson;
    }

    public void setInfoFillPerson(String infoFillPerson) {
        this.infoFillPerson = infoFillPerson;
    }

    public Boolean getConversionBuissOpp() {
        return conversionBuissOpp;
    }

    public void setConversionBuissOpp(Boolean conversionBuissOpp) {
        this.conversionBuissOpp = conversionBuissOpp;
    }

    public Boolean getConversionMarketFor() {
        return conversionMarketFor;
    }

    public void setConversionMarketFor(Boolean conversionMarketFor) {
        this.conversionMarketFor = conversionMarketFor;
    }

    public Boolean getConversionBussNegotia() {
        return conversionBussNegotia;
    }

    public void setConversionBussNegotia(Boolean conversionBussNegotia) {
        this.conversionBussNegotia = conversionBussNegotia;
    }

    public Double getEstimateTransferAmount() {
        return estimateTransferAmount;
    }

    public void setEstimateTransferAmount(Double estimateTransferAmount) {
        this.estimateTransferAmount = estimateTransferAmount;
    }

    public Double getEstimateMarketLoss() {
        return estimateMarketLoss;
    }

    public void setEstimateMarketLoss(Double estimateMarketLoss) {
        this.estimateMarketLoss = estimateMarketLoss;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}