package com.bjike.goddess.dispatchcar.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.dispatchcar.enums.Acctype;
import com.bjike.goddess.dispatchcar.enums.FindType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 出车记录
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-12 05:26 ]
 * @Description: [ 出车记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "dispatchcar_basicinfo")
public class DispatchCarInfo extends BaseEntity {

    /**
     * 出车单号
     */
    @Column(name = "dispatch_number", nullable = false, unique = true, columnDefinition = "VARCHAR(255)   COMMENT '出车单号'")
    private String number;

    /**
     * 司机名称
     */
    @Column(name = "driver", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '司机名称'")
    private String driver;

    /**
     * 是否公司人员出车
     */
    @Column(name = "is_companyDispatch", nullable = false, columnDefinition = "TINYINT(1)  COMMENT '是否公司人员出车,0否1是'")
    private Boolean companyDispatch;

    /**
     * 用车人
     */
    @Column(name = "carUser", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '用车人'")
    private String carUser;

    /**
     * 员工编号
     */
    @Column(name = "userNumber", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '员工编号'")
    private String userNumber;

    /**
     * 所属地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '所属地区'")
    private String area;

    /**
     * 所属项目组
     */
    @Column(name = "project_group", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '所属项目组'")
    private String group;

    /**
     * 是否立项
     */
    @Column(name = "is_projectApproval", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否立项,0否1是'")
    private Boolean projectApproval;

    /**
     * 项目名称
     */
    @Column(name = "project", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String project;

    /**
     * 科目类型
     */
    @Column(name = "acctype", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '科目类型'")
    private Acctype acctype;

    /**
     * 出车日期
     */
    @Column(name = "dispatchDate", nullable = false, columnDefinition = "DATE   COMMENT '出车日期'")
    private LocalDate dispatchDate;

    /**
     * 出车开始时间
     */
    @Column(name = "startTime", nullable = false, columnDefinition = "DATETIME   COMMENT '出车开始时间'")
    private LocalDateTime startTime;

    /**
     * 出车开始时间
     */
    @Column(name = "endTime", nullable = false, columnDefinition = "DATETIME   COMMENT '出车开始时间'")
    private LocalDateTime endTime;

    /**
     * 是否午休
     */
    @Column(name = "is_siesta", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否午休,0否1是'")
    private Boolean siesta;

    /**
     * 加班时长
     */
    @Column(name = "overWorkTime", nullable = false, columnDefinition = "INT(11)   COMMENT '加班时长'")
    private Integer overWorkTime;

    /**
     * 用车事由
     */
    @Column(name = "dispatchReason", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '用车事由'")
    private String dispatchReason;

    /**
     * 随同人员
     */
    @Column(name = "accompanyUser", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '随同人员'")
    private String accompanyUser;

    /**
     * 车牌号码
     */
    @Column(name = "carNumber", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '车牌号码'")
    private String carNumber;

    /**
     * 所用油卡编号
     */
    @Column(name = "oilCardNumber", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '所用油卡编号'")
    private String oilCardNumber;

    /**
     * 是否开空调
     */
    @Column(name = "is_aircondition", nullable = false, columnDefinition = "TINYINT(1)   COMMENT '是否开空调,0否1是'")
    private Boolean aircondition;

    /**
     * 是否市内
     */
    @Column(name = "is_downtown", nullable = false, columnDefinition = "TINYINT(1)   COMMENT '是否市内,0否1是'")
    private Boolean downtown;

    /**
     * 当天是否加油
     */
    @Column(name = "is_addOil", nullable = false, columnDefinition = "TINYINT(1)  COMMENT '当天是否加油,0否1是'")
    private Boolean addOil;

    /**
     * 补加油说明
     */
    @Column(name = "addOilExplain", columnDefinition = "VARCHAR(255)   COMMENT '补加油说明'")
    private String addOilExplain;

    /**
     * 欠油说明
     */
    @Column(name = "oweOilExplain", columnDefinition = "VARCHAR(255)   COMMENT '欠油说明'")
    private String oweOilExplain;

    /**
     * 加油时间
     */
    @Column(name = "addOilTime", columnDefinition = "DATETIME   COMMENT '加油时间'")
    private LocalDateTime addOilTime;

    /**
     * 加油量
     */
    @Column(name = "addOilAmount", columnDefinition = "DECIMAL(10,2)   COMMENT '加油量'")
    private Double addOilAmount;

    /**
     * 油卡余额
     */
    @Column(name = "oilCardBalance", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '油卡余额'")
    private Double oilCardBalance;

    /**
     * 当天油价
     */
    @Column(name = "oilPrice", columnDefinition = "DECIMAL(10,2)   COMMENT '当天油价'")
    private Double oilPrice;

    /**
     * 任务下达人
     */
    @Column(name = "principal", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '任务下达人'")
    private String principal;

    /**
     * 计划任务数量
     */
    @Column(name = "planTaskAmount", nullable = false, columnDefinition = "INT(11)   COMMENT '计划任务数量'")
    private Integer planTaskAmount;

    /**
     * 完成任务数量
     */
    @Column(name = "finishTaskAmount", nullable = false, columnDefinition = "INT(11)   COMMENT '完成任务数量'")
    private Integer finishTaskAmount;

    /**
     * 出车开始里程数
     */
    @Column(name = "startMileage", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '出车开始里程数'")
    private Double startMileage;

    /**
     * 出车结束里程数
     */
    @Column(name = "endMileage", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '出车结束里程数'")
    private Double endMileage;

    /**
     * 总里程数
     */
    @Column(name = "mileageSubtract", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '总里程数'")
    private Double mileageSubtract;

    /**
     * GPS轨迹总里程数
     */
    @Column(name = "mileageOfGPS", columnDefinition = "DECIMAL(10,2)   COMMENT 'GPS轨迹总里程数'")
    private Double mileageOfGPS;

    /**
     * 停车费
     */
    @Column(name = "parkCost", columnDefinition = "DECIMAL(10,2)   COMMENT '停车费'")
    private Double parkCost;

    /**
     * 过路费
     */
    @Column(name = "roadCost", columnDefinition = "DECIMAL(10,2)   COMMENT '过路费'")
    private Double roadCost;

    /**
     * 评价司机
     */
    @Column(name = "evaluatedriver", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '评价司机'")
    private String evaluatedriver;

    /**
     * 小票附件
     */
    @Column(name = "is_receipt", columnDefinition = "TINYINT(1)  COMMENT '小票附件,0否1是'")
    private Boolean receipt;

    /**
     * 处罚汇总
     */
    @Column(name = "punishCost", columnDefinition = "DECIMAL(10,2)   COMMENT '处罚汇总'")
    private Double punishCost;

    /**
     * 异常分析
     */
    @Column(name = "exceptionAnalyze", columnDefinition = "VARCHAR(255)   COMMENT '异常分析'")
    private String exceptionAnalyze;

    /**
     * 餐补费
     */
    @Column(name = "mealCost", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '餐补费'")
    private Double mealCost;

    /**
     * 资金模块意见
     */
    @Column(name = "fundModuleSugg", columnDefinition = "VARCHAR(255)   COMMENT '资金模块意见'")
    private String fundModuleSugg;

    /**
     * 资金审核时间
     */
    @Column(name = "fundAudtiTime", columnDefinition = "DATETIME   COMMENT '资金模块意见'")
    private LocalDateTime fundAuditTime;

    /**
     * 资金审核人
     */
    @Column(name = "fundAudtiUser", columnDefinition = "VARCHAR(255)   COMMENT '资金模块意见'")
    private String fundAuditUser;

    /**
     * 预算模块意见
     */
    @Column(name = "budgetModuleSugg", columnDefinition = "VARCHAR(255)   COMMENT '预算模块意见'")
    private String budgetModuleSugg;
    /**
     * 预算审核人
     */
    @Column(name = "budgetAuditUser", columnDefinition = "VARCHAR(255)   COMMENT '预算模块意见'")
    private String budgetAuditUser;

    /**
     * 预算审核时间
     */
    @Column(name = "budgetAuditTime", columnDefinition = "DATETIME  COMMENT '预算模块意见'")
    private LocalDateTime budgetAuditTime;

    /**
     * 任务下达人意见
     */
    @Column(name = "principalSugg", columnDefinition = "VARCHAR(255)   COMMENT '任务下达人意见'")
    private String principalSugg;

    /**
     * 任务下达人审核时间
     */
    @Column(name = "principalAuditTime", columnDefinition = "DATETIME   COMMENT '任务下达人意见'")
    private LocalDateTime principalAuditTime;

    /**
     * 是否通过
     */
    @Column(name = "is_auditResult", columnDefinition = "TINYINT(1)  COMMENT '是否通过,0否1是'")
    private Boolean auditResult;

    /**
     * 是否付款
     */
    @Column(name = "is_pay", columnDefinition = "TINYINT(1)   COMMENT '是否付款,0否1是'")
    private Boolean pay;

    /**
     * 租车费用
     */
    @Column(name = "carRentalCost", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '租车费用'")
    private Double carRentalCost;

    /**
     * 预计付款时间
     */
    @Column(name = "budgetPayDate", columnDefinition = "DATE   COMMENT '预计付款时间'")
    private LocalDate budgetPayDate;

    /**
     * 付款计划
     */
    @Column(name = "payPlan", columnDefinition = "VARCHAR(255)   COMMENT '付款计划'")
    private String payPlan;

    /**
     * 签收小票日期
     */
    @Column(name = "receiveReceiptDate", columnDefinition = "DATE   COMMENT '签收小票日期'")
    private LocalDate receiveReceiptDate;

    /**
     * 小票审核情况
     */
    @Column(name = "auditReceiptSugg", columnDefinition = "VARCHAR(255)   COMMENT '小票审核情况'")
    private String auditReceiptSugg;

    /**
     * 小票审核结果
     */
    @Column(name = "auditReceiptResult", columnDefinition = "TINYINT(1)   COMMENT '小票审核结果'")
    private Boolean auditReceiptResult;

    /**
     * 数据状态
     */
    @Column(columnDefinition = "TINYINT(2) DEFAULT '0'  COMMENT '小票审核情况'", insertable = false)
    private Status status;

    /**
     * 查询类型
     */
    @Column(name = "findType", columnDefinition = "TINYINT(2)   COMMENT '查询类型'")
    private FindType findType;

    /**
     * 油耗
     */
    @Column(name = "oilWear", columnDefinition = "DECIMAL(10,2)   COMMENT '总油耗'")
    private Double oilWear;

    /**
     * 油费
     */
    @Column(name = "oilCost", columnDefinition = "DECIMAL(10,2)   COMMENT '油费'")
    private Double oilCost;

    /**
     * 加班费
     */
    @Column(name = "overWorkCost", columnDefinition = "DECIMAL(10,2)   COMMENT '加班费'")
    private Double overWorkCost;

    /**
     * 金额
     */
    @Column(name = "cost", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '金额，除了油费的所有费用'")
    private Double cost;

    /**
     * 合计金额
     */
    @Column(name = "totalCost", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '金额，全部费用'")
    private Double totalCost;

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Double getOverWorkCost() {
        return overWorkCost;
    }

    public void setOverWorkCost(Double overWorkCost) {
        this.overWorkCost = overWorkCost;
    }

    public Double getOilCost() {
        return oilCost;
    }

    public void setOilCost(Double oilCost) {
        this.oilCost = oilCost;
    }

    public Double getOilWear() {
        return oilWear;
    }

    public void setOilWear(Double oilWear) {
        this.oilWear = oilWear;
    }

    public FindType getFindType() {
        return findType;
    }

    public void setFindType(FindType findType) {
        this.findType = findType;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public Boolean getCompanyDispatch() {
        return companyDispatch;
    }

    public void setCompanyDispatch(Boolean companyDispatch) {
        this.companyDispatch = companyDispatch;
    }

    public String getCarUser() {
        return carUser;
    }

    public void setCarUser(String carUser) {
        this.carUser = carUser;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Boolean getProjectApproval() {
        return projectApproval;
    }

    public void setProjectApproval(Boolean projectApproval) {
        this.projectApproval = projectApproval;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Acctype getAcctype() {
        return acctype;
    }

    public void setAcctype(Acctype acctype) {
        this.acctype = acctype;
    }

    public LocalDate getDispatchDate() {
        return dispatchDate;
    }

    public void setDispatchDate(LocalDate dispatchDate) {
        this.dispatchDate = dispatchDate;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Boolean getSiesta() {
        return siesta;
    }

    public void setSiesta(Boolean siesta) {
        this.siesta = siesta;
    }

    public Integer getOverWorkTime() {
        return overWorkTime;
    }

    public void setOverWorkTime(Integer overWorkTime) {
        this.overWorkTime = overWorkTime;
    }

    public String getDispatchReason() {
        return dispatchReason;
    }

    public void setDispatchReason(String dispatchReason) {
        this.dispatchReason = dispatchReason;
    }

    public String getAccompanyUser() {
        return accompanyUser;
    }

    public void setAccompanyUser(String accompanyUser) {
        this.accompanyUser = accompanyUser;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getOilCardNumber() {
        return oilCardNumber;
    }

    public void setOilCardNumber(String oilCardNumber) {
        this.oilCardNumber = oilCardNumber;
    }

    public Boolean getAircondition() {
        return aircondition;
    }

    public void setAircondition(Boolean aircondition) {
        this.aircondition = aircondition;
    }

    public Boolean getDowntown() {
        return downtown;
    }

    public void setDowntown(Boolean downtown) {
        this.downtown = downtown;
    }

    public Boolean getAddOil() {
        return addOil;
    }

    public void setAddOil(Boolean addOil) {
        this.addOil = addOil;
    }

    public String getAddOilExplain() {
        return addOilExplain;
    }

    public void setAddOilExplain(String addOilExplain) {
        this.addOilExplain = addOilExplain;
    }

    public String getOweOilExplain() {
        return oweOilExplain;
    }

    public void setOweOilExplain(String oweOilExplain) {
        this.oweOilExplain = oweOilExplain;
    }

    public LocalDateTime getAddOilTime() {
        return addOilTime;
    }

    public void setAddOilTime(LocalDateTime addOilTime) {
        this.addOilTime = addOilTime;
    }

    public Double getAddOilAmount() {
        return addOilAmount;
    }

    public void setAddOilAmount(Double addOilAmount) {
        this.addOilAmount = addOilAmount;
    }

    public Double getOilCardBalance() {
        return oilCardBalance;
    }

    public void setOilCardBalance(Double oilCardBalance) {
        this.oilCardBalance = oilCardBalance;
    }

    public Double getOilPrice() {
        return oilPrice;
    }

    public void setOilPrice(Double oilPrice) {
        this.oilPrice = oilPrice;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public Integer getPlanTaskAmount() {
        return planTaskAmount;
    }

    public void setPlanTaskAmount(Integer planTaskAmount) {
        this.planTaskAmount = planTaskAmount;
    }

    public Integer getFinishTaskAmount() {
        return finishTaskAmount;
    }

    public void setFinishTaskAmount(Integer finishTaskAmount) {
        this.finishTaskAmount = finishTaskAmount;
    }

    public Double getStartMileage() {
        return startMileage;
    }

    public void setStartMileage(Double startMileage) {
        this.startMileage = startMileage;
    }

    public Double getEndMileage() {
        return endMileage;
    }

    public void setEndMileage(Double endMileage) {
        this.endMileage = endMileage;
    }

    public Double getMileageSubtract() {
        return mileageSubtract;
    }

    public void setMileageSubtract(Double mileageSubtract) {
        this.mileageSubtract = mileageSubtract;
    }

    public Double getMileageOfGPS() {
        return mileageOfGPS;
    }

    public void setMileageOfGPS(Double mileageOfGPS) {
        this.mileageOfGPS = mileageOfGPS;
    }

    public Double getParkCost() {
        return parkCost;
    }

    public void setParkCost(Double parkCost) {
        this.parkCost = parkCost;
    }

    public Double getRoadCost() {
        return roadCost;
    }

    public void setRoadCost(Double roadCost) {
        this.roadCost = roadCost;
    }

    public String getEvaluatedriver() {
        return evaluatedriver;
    }

    public void setEvaluatedriver(String evaluatedriver) {
        this.evaluatedriver = evaluatedriver;
    }

    public Boolean getReceipt() {
        return receipt;
    }

    public void setReceipt(Boolean receipt) {
        this.receipt = receipt;
    }

    public Double getPunishCost() {
        return punishCost;
    }

    public void setPunishCost(Double punishCost) {
        this.punishCost = punishCost;
    }

    public String getExceptionAnalyze() {
        return exceptionAnalyze;
    }

    public void setExceptionAnalyze(String exceptionAnalyze) {
        this.exceptionAnalyze = exceptionAnalyze;
    }

    public Double getMealCost() {
        return mealCost;
    }

    public void setMealCost(Double mealCost) {
        this.mealCost = mealCost;
    }

    public String getFundModuleSugg() {
        return fundModuleSugg;
    }

    public void setFundModuleSugg(String fundModuleSugg) {
        this.fundModuleSugg = fundModuleSugg;
    }

    public LocalDateTime getFundAuditTime() {
        return fundAuditTime;
    }

    public void setFundAuditTime(LocalDateTime fundAuditTime) {
        this.fundAuditTime = fundAuditTime;
    }

    public String getFundAuditUser() {
        return fundAuditUser;
    }

    public void setFundAuditUser(String fundAuditUser) {
        this.fundAuditUser = fundAuditUser;
    }

    public String getBudgetModuleSugg() {
        return budgetModuleSugg;
    }

    public void setBudgetModuleSugg(String budgetModuleSugg) {
        this.budgetModuleSugg = budgetModuleSugg;
    }

    public String getBudgetAuditUser() {
        return budgetAuditUser;
    }

    public void setBudgetAuditUser(String budgetAuditUser) {
        this.budgetAuditUser = budgetAuditUser;
    }

    public LocalDateTime getBudgetAuditTime() {
        return budgetAuditTime;
    }

    public void setBudgetAuditTime(LocalDateTime budgetAuditTime) {
        this.budgetAuditTime = budgetAuditTime;
    }

    public String getPrincipalSugg() {
        return principalSugg;
    }

    public void setPrincipalSugg(String principalSugg) {
        this.principalSugg = principalSugg;
    }

    public LocalDateTime getPrincipalAuditTime() {
        return principalAuditTime;
    }

    public void setPrincipalAuditTime(LocalDateTime principalAuditTime) {
        this.principalAuditTime = principalAuditTime;
    }

    public Boolean getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(Boolean auditResult) {
        this.auditResult = auditResult;
    }

    public Boolean getPay() {
        return pay;
    }

    public void setPay(Boolean pay) {
        this.pay = pay;
    }

    public Double getCarRentalCost() {
        return carRentalCost;
    }

    public void setCarRentalCost(Double carRentalCost) {
        this.carRentalCost = carRentalCost;
    }

    public LocalDate getBudgetPayDate() {
        return budgetPayDate;
    }

    public void setBudgetPayDate(LocalDate budgetPayDate) {
        this.budgetPayDate = budgetPayDate;
    }

    public LocalDate getReceiveReceiptDate() {
        return receiveReceiptDate;
    }

    public void setReceiveReceiptDate(LocalDate receiveReceiptDate) {
        this.receiveReceiptDate = receiveReceiptDate;
    }

    public String getAuditReceiptSugg() {
        return auditReceiptSugg;
    }

    public void setAuditReceiptSugg(String auditReceiptSugg) {
        this.auditReceiptSugg = auditReceiptSugg;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Boolean getAuditReceiptResult() {
        return auditReceiptResult;
    }

    public void setAuditReceiptResult(Boolean auditReceiptResult) {
        this.auditReceiptResult = auditReceiptResult;
    }

    public String getPayPlan() {
        return payPlan;
    }

    public void setPayPlan(String payPlan) {
        this.payPlan = payPlan;
    }
}