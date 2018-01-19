package com.bjike.goddess.projectcalculation.entity;

import com.bjike.goddess.businessproject.enums.MakeContract;
import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 项目界面测算明细
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-08 03:04 ]
 * @Description: [ 项目界面测算明细 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectcalculation_interfacecalculationdetail")
public class InterfaceCalculationDetail extends BaseEntity {

    /**
     * 计划测算时间
     */
    @Column(name = "calculationTime", nullable = false, columnDefinition = "DATE   COMMENT '计划测算时间'")
    private LocalDate calculationTime;

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
     * 总包单位名称
     */
    @Column(name = "majorCompany", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '总包单位名称'")
    private String majorCompany;

    /**
     * 分包单位名称
     */
    @Column(name = "subCompany", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '分包单位名称'")
    private String subCompany;

    /**
     * 所属项目组
     */
    @Column(name = "projectGroup", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '所属项目组'")
    private String projectGroup;

    /**
     * 类型
     */
    @Column(name = "type", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '类型'")
    private String type;

    /**
     * 专业/工期
     */
    @Column(name = "major", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '专业/工期'")
    private String major;

    /**
     * 是否有合同派工
     */
    @Column(name = "is_taskContract", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否有合同派工'", insertable = false)
    private Boolean taskContract;

    /**
     * 内部项目名称/编号
     */
    @Column(name = "internalProject", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '内部项目名称/编号'")
    private String internalProject;

    /**
     * 项目负责人
     */
    @Column(name = "projectCharge", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目负责人'")
    private String projectCharge;

    /**
     * 是否有项目立项
     */
    @Column(name = "makeContract", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '是否有项目立项'")
    private MakeContract makeContract;

    /**
     * 派工金额
     */
    @Column(name = "taskMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '派工金额'")
    private Double taskMoney;

    /**
     * 预估金额
     */
    @Column(name = "forecastMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '预估金额'")
    private Double forecastMoney;

    /**
     * 派工界面A
     */
    @Column(name = "dispatchInterfaceA", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '派工界面A'")
    private String dispatchInterfaceA;

    /**
     * 规模数量
     */
    @Column(name = "scale", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '规模数量'")
    private Double scale;

    /**
     * 税点
     */
    @Column(name = "taxPoint", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '税点'")
    private String taxPoint;

    /**
     * 增值税发票额
     */
    @Column(name = "invoice", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '增值税发票额'")
    private Double invoice;

    /**
     * 付款方式
     */
    @Column(name = "paymentMethod", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '付款方式'")
    private String paymentMethod;

    /**
     * 结算批次
     */
    @Column(name = "settlementBatch", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '结算批次'")
    private String settlementBatch;

    /**
     * 预估开工时间
     */
    @Column(name = "expectStartDate", nullable = false, columnDefinition = "DATE   COMMENT '预估开工时间'")
    private LocalDate expectStartDate;

    /**
     * 预计完工时间
     */
    @Column(name = "expectCompleteTime", nullable = false, columnDefinition = "DATE   COMMENT '预计完工时间'")
    private LocalDate expectCompleteTime;

    /**
     * 测算进度
     */
    @Column(name = "calculationProgress", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '测算进度'")
    private String calculationProgress;

    /**
     * 是否外包
     */
    @Column(name = "is_epiboly", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否外包'", insertable = false)
    private Boolean epiboly;

    /**
     * 决策时间
     */
    @Column(name = "decisionTime", nullable = false, columnDefinition = "DATE   COMMENT '决策时间'")
    private LocalDate decisionTime;

    /**
     * 实施方
     */
    @Column(name = "implementingParty", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '实施方'")
    private String implementingParty;

    /**
     * 界面内容
     */
    @Column(name = "interfaceContent", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '界面内容'")
    private String interfaceContent;

    /**
     * 条目
     */
    @Column(name = "entry", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '条目'")
    private String entry;

    /**
     * 外包单价；String
     */
    @Column(name = "outsourcedUnitPrice", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '外包单价；String'")
    private Double outsourcedUnitPrice;

    /**
     * 条目金额
     */
    @Column(name = "entryMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '条目金额'")
    private Double entryMoney;

    /**
     * 界面派工金额
     */
    @Column(name = "interfaceAssignment", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '界面派工金额'")
    private Double interfaceAssignment;

    /**
     * 外包总金额
     */
    @Column(name = "totalAmountOfOutsourced", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '外包总金额'")
    private Double totalAmountOfOutsourced;

    /**
     * 备注
     */
    @Column(name = "note", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String note;

    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '冻结状态'")
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

    public Boolean getTaskContract() {
        return taskContract;
    }

    public void setTaskContract(Boolean taskContract) {
        this.taskContract = taskContract;
    }

    public String getInternalProject() {
        return internalProject;
    }

    public void setInternalProject(String internalProject) {
        this.internalProject = internalProject;
    }

    public String getProjectCharge() {
        return projectCharge;
    }

    public void setProjectCharge(String projectCharge) {
        this.projectCharge = projectCharge;
    }

    public MakeContract getMakeContract() {
        return makeContract;
    }

    public void setMakeContract(MakeContract makeContract) {
        this.makeContract = makeContract;
    }

    public Double getTaskMoney() {
        return taskMoney;
    }

    public void setTaskMoney(Double taskMoney) {
        this.taskMoney = taskMoney;
    }

    public Double getForecastMoney() {
        return forecastMoney;
    }

    public void setForecastMoney(Double forecastMoney) {
        this.forecastMoney = forecastMoney;
    }

    public String getDispatchInterfaceA() {
        return dispatchInterfaceA;
    }

    public void setDispatchInterfaceA(String dispatchInterfaceA) {
        this.dispatchInterfaceA = dispatchInterfaceA;
    }

    public Double getScale() {
        return scale;
    }

    public void setScale(Double scale) {
        this.scale = scale;
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

    public LocalDate getExpectStartDate() {
        return expectStartDate;
    }

    public void setExpectStartDate(LocalDate expectStartDate) {
        this.expectStartDate = expectStartDate;
    }

    public LocalDate getExpectCompleteTime() {
        return expectCompleteTime;
    }

    public void setExpectCompleteTime(LocalDate expectCompleteTime) {
        this.expectCompleteTime = expectCompleteTime;
    }

    public String getCalculationProgress() {
        return calculationProgress;
    }

    public void setCalculationProgress(String calculationProgress) {
        this.calculationProgress = calculationProgress;
    }

    public Boolean getEpiboly() {
        return epiboly;
    }

    public void setEpiboly(Boolean epiboly) {
        this.epiboly = epiboly;
    }

    public LocalDate getDecisionTime() {
        return decisionTime;
    }

    public void setDecisionTime(LocalDate decisionTime) {
        this.decisionTime = decisionTime;
    }

    public String getImplementingParty() {
        return implementingParty;
    }

    public void setImplementingParty(String implementingParty) {
        this.implementingParty = implementingParty;
    }

    public String getInterfaceContent() {
        return interfaceContent;
    }

    public void setInterfaceContent(String interfaceContent) {
        this.interfaceContent = interfaceContent;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public Double getOutsourcedUnitPrice() {
        return outsourcedUnitPrice;
    }

    public void setOutsourcedUnitPrice(Double outsourcedUnitPrice) {
        this.outsourcedUnitPrice = outsourcedUnitPrice;
    }

    public Double getEntryMoney() {
        return entryMoney;
    }

    public void setEntryMoney(Double entryMoney) {
        this.entryMoney = entryMoney;
    }

    public Double getInterfaceAssignment() {
        return interfaceAssignment;
    }

    public void setInterfaceAssignment(Double interfaceAssignment) {
        this.interfaceAssignment = interfaceAssignment;
    }

    public Double getTotalAmountOfOutsourced() {
        return totalAmountOfOutsourced;
    }

    public void setTotalAmountOfOutsourced(Double totalAmountOfOutsourced) {
        this.totalAmountOfOutsourced = totalAmountOfOutsourced;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}