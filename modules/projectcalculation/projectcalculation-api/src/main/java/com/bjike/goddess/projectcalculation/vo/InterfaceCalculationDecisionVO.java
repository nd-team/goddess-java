package com.bjike.goddess.projectcalculation.vo;

import com.bjike.goddess.businessproject.enums.MakeContract;

/**
* 界面测算决策表现层对象
* @Author:			[ wanyi ]
* @Date:			[  2017-12-09 04:23 ]
* @Description:	[ 界面测算决策表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class InterfaceCalculationDecisionVO { 

/**
* id
*/
 private String  id; 
/**
* 计划测算时间
*/
 private String  calculationTime;

/**
* 地区
*/
 private String  area; 

/**
* 业务类型
*/
 private String  businessType; 

/**
* 业务方向科目
*/
 private String  businessSubject; 

/**
* 总包单位名称
*/
 private String  majorCompany; 

/**
* 分包单位名称
*/
 private String  subCompany; 

/**
* 所属项目组
*/
 private String  projectGroup; 

/**
* 类型
*/
 private String  type; 

/**
* 专业/工期
*/
 private String  major; 

/**
* 是否有合同派工
*/
 private Boolean  taskContract; 

/**
* 内部项目名称/编号
*/
 private String  internalProjectNum; 

/**
* 项目负责人
*/
 private String  projectCharge; 

/**
* 是否有项目立项
*/
 private MakeContract makeContract;

/**
* 派工金额
*/
 private Double  taskMoney; 

/**
* 预估金额
*/
 private Double  forecastMoney; 

/**
* 派工界面A
*/
 private String  dispatchInterfaceA; 

/**
* 界面内容
*/
 private String  interfaceContent; 

/**
* 条目
*/
 private String  entry; 

/**
* 外包单价
*/
 private Double  outsourcedUnitPrice; 

/**
* 规模
*/
 private String  scale; 

/**
* 条目金额
*/
 private Double  entryMoney; 

/**
* 界面派工金额
*/
 private Double  interfaceAssignment; 

/**
* 外包总金额
*/
 private Double  totalAmountOfOutsourced; 

/**
* 税点
*/
 private String  taxPoint; 

/**
* 增值税发票额
*/
 private Double  invoice; 

/**
* 管理费点数
*/
 private Double  managementFeePoints; 

/**
* 付款方式
*/
 private String  paymentMethod; 

/**
* 结算批次
*/
 private String  settlementBatch; 

/**
* 预估开工时间
*/
 private String  expectStartDate;

/**
* 预计完工时间
*/
 private String  expectCompleteTime;

/**
* 测算进度
*/
 private String  calculationProgress; 

/**
* 测算完成时间
*/
 private String  calculationFinisTime;

/**
* 备注
*/
 private String  note; 

/**
* 客户模块意见
*/
 private String  CustomerModuleOpinion; 

/**
* 预算模块意见
*/
 private String  budgetModuleOpinion; 

/**
* 规划模块意见
*/
 private String  planningModuleOpinion; 

/**
* 项目经理意见
*/
 private String  projectManagerOpinion; 

/**
* 是否外包
*/
 private Boolean  epiboly; 

/**
* 决策时间
*/
 private String  decisionTime;

/**
* 实施方
*/
 private String  implementingParty; 



 public String getId () { 
 return id;
 } 
 public void setId (String id ) { 
 this.id = id ; 
 } 
 public String getCalculationTime () {
 return calculationTime;
 } 
 public void setCalculationTime (String calculationTime ) {
 this.calculationTime = calculationTime ; 
 } 
 public String getArea () { 
 return area;
 } 
 public void setArea (String area ) { 
 this.area = area ; 
 } 
 public String getBusinessType () { 
 return businessType;
 } 
 public void setBusinessType (String businessType ) { 
 this.businessType = businessType ; 
 } 
 public String getBusinessSubject () { 
 return businessSubject;
 } 
 public void setBusinessSubject (String businessSubject ) { 
 this.businessSubject = businessSubject ; 
 } 
 public String getMajorCompany () { 
 return majorCompany;
 } 
 public void setMajorCompany (String majorCompany ) { 
 this.majorCompany = majorCompany ; 
 } 
 public String getSubCompany () { 
 return subCompany;
 } 
 public void setSubCompany (String subCompany ) { 
 this.subCompany = subCompany ; 
 } 
 public String getProjectGroup () { 
 return projectGroup;
 } 
 public void setProjectGroup (String projectGroup ) { 
 this.projectGroup = projectGroup ; 
 } 
 public String getType () { 
 return type;
 } 
 public void setType (String type ) { 
 this.type = type ; 
 } 
 public String getMajor () { 
 return major;
 } 
 public void setMajor (String major ) { 
 this.major = major ; 
 } 
 public Boolean getTaskContract () { 
 return taskContract;
 } 
 public void setTaskContract (Boolean taskContract ) { 
 this.taskContract = taskContract ; 
 } 
 public String getInternalProjectNum () { 
 return internalProjectNum;
 } 
 public void setInternalProjectNum (String internalProjectNum ) { 
 this.internalProjectNum = internalProjectNum ; 
 } 
 public String getProjectCharge () { 
 return projectCharge;
 } 
 public void setProjectCharge (String projectCharge ) { 
 this.projectCharge = projectCharge ; 
 } 
 public MakeContract getMakeContract () { 
 return makeContract;
 } 
 public void setMakeContract (MakeContract makeContract ) { 
 this.makeContract = makeContract ; 
 } 
 public Double getTaskMoney () { 
 return taskMoney;
 } 
 public void setTaskMoney (Double taskMoney ) { 
 this.taskMoney = taskMoney ; 
 } 
 public Double getForecastMoney () { 
 return forecastMoney;
 } 
 public void setForecastMoney (Double forecastMoney ) { 
 this.forecastMoney = forecastMoney ; 
 } 
 public String getDispatchInterfaceA () { 
 return dispatchInterfaceA;
 } 
 public void setDispatchInterfaceA (String dispatchInterfaceA ) { 
 this.dispatchInterfaceA = dispatchInterfaceA ; 
 } 
 public String getInterfaceContent () { 
 return interfaceContent;
 } 
 public void setInterfaceContent (String interfaceContent ) { 
 this.interfaceContent = interfaceContent ; 
 } 
 public String getEntry () { 
 return entry;
 } 
 public void setEntry (String entry ) { 
 this.entry = entry ; 
 } 
 public Double getOutsourcedUnitPrice () { 
 return outsourcedUnitPrice;
 } 
 public void setOutsourcedUnitPrice (Double outsourcedUnitPrice ) { 
 this.outsourcedUnitPrice = outsourcedUnitPrice ; 
 } 
 public String getScale () { 
 return scale;
 } 
 public void setScale (String scale ) { 
 this.scale = scale ; 
 } 
 public Double getEntryMoney () { 
 return entryMoney;
 } 
 public void setEntryMoney (Double entryMoney ) { 
 this.entryMoney = entryMoney ; 
 } 
 public Double getInterfaceAssignment () { 
 return interfaceAssignment;
 } 
 public void setInterfaceAssignment (Double interfaceAssignment ) { 
 this.interfaceAssignment = interfaceAssignment ; 
 } 
 public Double getTotalAmountOfOutsourced () { 
 return totalAmountOfOutsourced;
 } 
 public void setTotalAmountOfOutsourced (Double totalAmountOfOutsourced ) { 
 this.totalAmountOfOutsourced = totalAmountOfOutsourced ; 
 } 
 public String getTaxPoint () { 
 return taxPoint;
 } 
 public void setTaxPoint (String taxPoint ) { 
 this.taxPoint = taxPoint ; 
 } 
 public Double getInvoice () { 
 return invoice;
 } 
 public void setInvoice (Double invoice ) { 
 this.invoice = invoice ; 
 } 
 public Double getManagementFeePoints () { 
 return managementFeePoints;
 } 
 public void setManagementFeePoints (Double managementFeePoints ) { 
 this.managementFeePoints = managementFeePoints ; 
 } 
 public String getPaymentMethod () { 
 return paymentMethod;
 } 
 public void setPaymentMethod (String paymentMethod ) { 
 this.paymentMethod = paymentMethod ; 
 } 
 public String getSettlementBatch () { 
 return settlementBatch;
 } 
 public void setSettlementBatch (String settlementBatch ) { 
 this.settlementBatch = settlementBatch ; 
 } 
 public String getExpectStartDate () {
 return expectStartDate;
 } 
 public void setExpectStartDate (String expectStartDate ) {
 this.expectStartDate = expectStartDate ; 
 } 
 public String getExpectCompleteTime () {
 return expectCompleteTime;
 } 
 public void setExpectCompleteTime (String expectCompleteTime ) {
 this.expectCompleteTime = expectCompleteTime ; 
 } 
 public String getCalculationProgress () { 
 return calculationProgress;
 } 
 public void setCalculationProgress (String calculationProgress ) { 
 this.calculationProgress = calculationProgress ; 
 } 
 public String getCalculationFinisTime () {
 return calculationFinisTime;
 } 
 public void setCalculationFinisTime (String calculationFinisTime ) {
 this.calculationFinisTime = calculationFinisTime ; 
 } 
 public String getNote () { 
 return note;
 } 
 public void setNote (String note ) { 
 this.note = note ; 
 } 
 public String getCustomerModuleOpinion () { 
 return CustomerModuleOpinion;
 } 
 public void setCustomerModuleOpinion (String CustomerModuleOpinion ) { 
 this.CustomerModuleOpinion = CustomerModuleOpinion ; 
 } 
 public String getBudgetModuleOpinion () { 
 return budgetModuleOpinion;
 } 
 public void setBudgetModuleOpinion (String budgetModuleOpinion ) { 
 this.budgetModuleOpinion = budgetModuleOpinion ; 
 } 
 public String getPlanningModuleOpinion () { 
 return planningModuleOpinion;
 } 
 public void setPlanningModuleOpinion (String planningModuleOpinion ) { 
 this.planningModuleOpinion = planningModuleOpinion ; 
 } 
 public String getProjectManagerOpinion () { 
 return projectManagerOpinion;
 } 
 public void setProjectManagerOpinion (String projectManagerOpinion ) { 
 this.projectManagerOpinion = projectManagerOpinion ; 
 } 
 public Boolean getEpiboly () { 
 return epiboly;
 } 
 public void setEpiboly (Boolean epiboly ) { 
 this.epiboly = epiboly ; 
 } 
 public String getDecisionTime () {
 return decisionTime;
 } 
 public void setDecisionTime (String decisionTime ) {
 this.decisionTime = decisionTime ; 
 } 
 public String getImplementingParty () { 
 return implementingParty;
 } 
 public void setImplementingParty (String implementingParty ) { 
 this.implementingParty = implementingParty ; 
 } 
 }