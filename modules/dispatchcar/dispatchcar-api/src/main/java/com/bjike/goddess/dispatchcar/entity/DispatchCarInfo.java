package com.bjike.goddess.dispatchcar.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.dispatchcar.enums.Acctype;
import com.bjike.goddess.dispatchcar.enums.CarSource;
import com.bjike.goddess.dispatchcar.enums.Evaluate;
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
     * 出车记录来源
     */
    @Column(name = "carSource",  columnDefinition = "TINYINT(1)  COMMENT '出车记录来源'")
    private CarSource carSource;

    /**
     * 出车单号
     */
    @Column(name = "dispatch_number",  columnDefinition = "VARCHAR(255)   COMMENT '出车单号'")
    private String number;

    /**
     * 司机名称
     */
    @Column(name = "driver", columnDefinition = "VARCHAR(255)   COMMENT '司机名称'")
    private String driver;

    /**
     * 是否公司人员出车
     */
    @Column(name = "is_companyDispatch",  columnDefinition = "TINYINT(1)  COMMENT '是否公司人员出车,0否1是'")
    private Boolean companyDispatch;

    /**
     * 用车人
     */
    @Column(name = "carUser", columnDefinition = "VARCHAR(255)   COMMENT '用车人'")
    private String carUser;

    /**
     * 员工编号
     */
    @Column(name = "userNumber", columnDefinition = "VARCHAR(255)   COMMENT '员工编号'")
    private String userNumber;

    /**
     * 所属地区
     */
    @Column(name = "area",  columnDefinition = "VARCHAR(255)   COMMENT '所属地区'")
    private String area;

    /**
     * 所属项目组
     */
    @Column(name = "project_group",  columnDefinition = "VARCHAR(255)   COMMENT '所属项目组'")
    private String group;

    /**
     * 是否立项
     */
    @Column(name = "is_projectApproval", columnDefinition = "TINYINT(1) COMMENT '是否立项,0否1是'")
    private Boolean projectApproval;

    /**
     * 项目名称
     */
    @Column(name = "project", columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String project;

    /**
     * 科目类型
     */
    @Column(name = "acctype",  columnDefinition = "TINYINT(2)   COMMENT '科目类型'")
    private Acctype acctype;

    /**
     * 出车日期
     */
    @Column(name = "dispatchDate", columnDefinition = "DATE   COMMENT '出车日期'")
    private LocalDate dispatchDate;

    /**
     * 出车开始时间
     */
    @Column(name = "startTime",  columnDefinition = "DATETIME   COMMENT '出车开始时间'")
    private LocalDateTime startTime;

    /**
     * 出车结束时间
     */
    @Column(name = "endTime",  columnDefinition = "DATETIME   COMMENT '出车结束时间'")
    private LocalDateTime endTime;

    /**
     * 是否午休
     */
    @Column(name = "is_siesta",  columnDefinition = "TINYINT(1) COMMENT '是否午休,0否1是'")
    private Boolean siesta;

    /**
     * 加班时长
     */
    @Column(name = "overWorkTime", columnDefinition = "INT(11)   COMMENT '加班时长'")
    private Double overWorkTime;

    /**
     * 用车事由
     */
    @Column(name = "dispatchReason", columnDefinition = "VARCHAR(255)   COMMENT '用车事由'")
    private String dispatchReason;

    /**
     * 用车随同人员
     */
    @Column(name = "accompanyUser",  columnDefinition = "VARCHAR(255)   COMMENT '用车随同人员'")
    private String accompanyUser;

    /**
     * 车牌号码
     */
    @Column(name = "carNumber", columnDefinition = "VARCHAR(255)   COMMENT '车牌号码'")
    private String carNumber;

    /**
     * 所用油卡编号
     */
    @Column(name = "oilCardNumber", columnDefinition = "VARCHAR(255)   COMMENT '所用油卡编号'")
    private String oilCardNumber;

    /**
     * 是否开空调
     */
    @Column(name = "is_aircondition",  columnDefinition = "TINYINT(1)   COMMENT '是否开空调,0否1是'")
    private Boolean aircondition;

    /**
     * 是否市内
     */
    @Column(name = "is_downtown",  columnDefinition = "TINYINT(1)   COMMENT '是否市内,0否1是'")
    private Boolean downtown;

    /**
     * 当天是否加油
     */
    @Column(name = "is_addOil", columnDefinition = "TINYINT(1)  COMMENT '当天是否加油,0否1是'")
    private Boolean addOil;

    /**
     * 补加油说明
     */
    @Column(name = "addOilExplain", columnDefinition = "VARCHAR(255)   COMMENT '补加油说明'")
    private String addOilExplain;

    /**
     * 补加油量
     */
    @Column(name = "supplementOil", columnDefinition = "DECIMAL(10,2)   COMMENT '补加油量'")
    private Double supplementOil;

    /**
     * 补加油费
     */
    @Column(name = "supplementFee", columnDefinition = "DECIMAL(10,2)   COMMENT '补加油费'")
    private Double supplementFee;


    /**
     * 欠油说明
     */
    @Column(name = "oweOilExplain", columnDefinition = "VARCHAR(255)   COMMENT '欠油说明'")
    private String oweOilExplain;


    /**
     * 欠油量
     */
    @Column(name = "lessOil", columnDefinition = "DECIMAL(10,2)   COMMENT '欠油量'")
    private Double lessOil;

    /**
     * 欠油费
     */
    @Column(name = "lessOilFee", columnDefinition = "DECIMAL(10,2)   COMMENT '欠油费'")
    private Double lessOilFee;



    /**
     * 加油时间
     */
    @Column(name = "addOilTime", columnDefinition = "DATETIME   COMMENT '加油时间'")
    private LocalDateTime addOilTime;

    /**
     * 应加油量
     */
    @Column(name = "shouldAmount", columnDefinition = "DECIMAL(10,2)   COMMENT '应加油量'")
    private Double shouldAmount;

    /**
     * 应加油费
     */
    @Column(name = "shouldAmountMoney", columnDefinition = "DECIMAL(10,2)   COMMENT '应加油费'")
    private Double shouldAmountMoney;

    /**
     * 实际加油量
     */
    @Column(name = "addOilAmount", columnDefinition = "DECIMAL(10,2)   COMMENT '实际加油量'")
    private Double addOilAmount;

    /**
     * 实际加油费
     */
    @Column(name = "addOilAmountMoney", columnDefinition = "DECIMAL(10,2)   COMMENT '实际加油费'")
    private Double addOilAmountMoney;

    /**
     * 油卡余额
     */
    @Column(name = "oilCardBalance",  columnDefinition = "DECIMAL(10,2)   COMMENT '油卡余额'")
    private Double oilCardBalance;

    /**
     * 当天油价
     */
    @Column(name = "oilPrice", columnDefinition = "DECIMAL(10,2)   COMMENT '当天油价'")
    private Double oilPrice;

    /**
     * 任务下达人
     */
    @Column(name = "principal", columnDefinition = "VARCHAR(255)   COMMENT '任务下达人'")
    private String principal;

    /**
     * 计划任务数量
     */
    @Column(name = "planTaskAmount",  columnDefinition = "INT(11)   COMMENT '计划任务数量'")
    private Integer planTaskAmount;

    /**
     * 完成任务数量
     */
    @Column(name = "finishTaskAmount",  columnDefinition = "INT(11)   COMMENT '完成任务数量'")
    private Integer finishTaskAmount;

    /**
     * 出车开始里程数
     */
    @Column(name = "startMileage",  columnDefinition = "DECIMAL(10,2)   COMMENT '出车开始里程数'")
    private Double startMileage;

    /**
     * 出车结束里程数
     */
    @Column(name = "endMileage",  columnDefinition = "DECIMAL(10,2)   COMMENT '出车结束里程数'")
    private Double endMileage;

    /**
     * 总里程数
     */
    @Column(name = "mileageSubtract",  columnDefinition = "DECIMAL(10,2)   COMMENT '总里程数'")
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
     * 餐补费
     */
    @Column(name = "mealCost", columnDefinition = "DECIMAL(10,2)   COMMENT '餐补费'")
    private Double mealCost;

    /**
     * 对司机的评价
     */
    @Column(name = "evaluatedriver", columnDefinition = "TINYINT(2)   COMMENT '对司机的评价'")
    private Evaluate evaluatedriver;

    /**
     * 小票附件
     */
    @Column(name = "is_receipt", columnDefinition = "TINYINT(1)  COMMENT '小票附件,0否1是'")
    private Boolean receipt;

    /**
     * 项目模块负责人
     */
    @Column(name = "projectCharge", columnDefinition = "VARCHAR(255)  COMMENT '项目模块负责人'")
    private String projectCharge;

    /**
     * 项目模块负责人意见
     */
    @Column(name = "projectChargeIdea", columnDefinition = "VARCHAR(255)  COMMENT '项目模块负责人意见'")
    private String projectChargeIdea;


    /**
     * 是否通过
     */
    @Column(name = "ifPass", columnDefinition = "TINYINT(1) COMMENT '是否通过'")
    private Boolean ifPass;

    /**
     * 客户模块负责人
     */
    @Column(name = "clientModule", columnDefinition = "VARCHAR(255)  COMMENT '客户模块负责人'")
    private String clientModule;

    /**
     * 客户模块负责人意见
     */
    @Column(name = "clientModuleIdea", columnDefinition = "VARCHAR(255)  COMMENT '客户模块负责人意见'")
    private String clientModuleIdea;


    /**
     * 客户模块负责人审核时间
     */
    @Column(name = "clientDate", columnDefinition = "DATETIME  COMMENT '客户模块负责人审核时间'")
    private LocalDateTime clientDate;

    /**
     * 是否冻结
     */
    @Column(name = "ifFreeze", columnDefinition = "TINYINT(1) COMMENT '是否冻结'")
    private Boolean ifFreeze;

    /**
     * 素养模块负责人
     */
    @Column(name = "headModule", columnDefinition = "VARCHAR(255)  COMMENT '素养模块负责人'")
    private String headModule;

    /**
     * 素养模块负责人意见
     */
    @Column(name = "headModuleIdea", columnDefinition = "VARCHAR(255)  COMMENT '素养模块负责人意见'")
    private String headModuleIdea;


    /**
     * 素养模块负责人审核时间
     */
    @Column(name = "headDate", columnDefinition = "DATETIME  COMMENT '素养模块负责人审核时间'")
    private LocalDateTime headDate;

    /**
     * 预算模块负责人
     */
    @Column(name = "budgetAuditUser", columnDefinition = "VARCHAR(255)   COMMENT '预算模块负责人'")
    private String budgetAuditUser;

    /**
     * 预算模块意见
     */
    @Column(name = "budgetModuleIdea", columnDefinition = "VARCHAR(255)  COMMENT '预算模块意见'")
    private String budgetModuleIdea;

    /**
     * 预算模块负责人审核时间
     */
    @Column(name = "budgetAuditTime", columnDefinition = "DATETIME  COMMENT '预算模块负责人审核时间'")
    private LocalDateTime budgetAuditTime;

    /**
     * 账务模块负责人
     */
    @Column(name = "accountModule", columnDefinition = "VARCHAR(255)  COMMENT '账务模块负责人'")
    private String accountModule;

    /**
     * 账务模块意见
     */
    @Column(name = "accountModuleIdea", columnDefinition = "VARCHAR(255)  COMMENT '账务模块意见'")
    private String accountModuleIdea;

    /**
     * 账务模块负责人审核时间
     */
    @Column(name = "accountDate", columnDefinition = "DATETIME  COMMENT '账务模块负责人审核时间'")
    private LocalDateTime accountDate;


    /**
     * 核对依据是否齐全无误
     */
    @Column(name = "isCorrect", columnDefinition = "TINYINT(1) COMMENT '核对依据是否齐全无误'")
    private Boolean isCorrect;

    /**
     * 寄件人
     */
    @Column(name = "sender", columnDefinition = "VARCHAR(255)  COMMENT '寄件人'")
    private String sender;

    /**
     * 寄件日期
     */
    @Column(name = "sendDate", columnDefinition = "DATE  COMMENT '寄件日期'")
    private LocalDate sendDate;


    /**
     * 存档联是否寄件
     */
    @Column(name = "ifSendArchiveAl", columnDefinition = "TINYINT(1)  COMMENT '存档联是否寄件'")
    private Boolean ifSendArchiveAl;

    /**
     * 报销联是否寄件
     */
    @Column(name = "ifSendReimbursementAl", columnDefinition = "TINYINT(1)  COMMENT '报销联是否寄件'")
    private Boolean ifSendReimbursementAl;

    /**
     * 过路停车费总额
     */
    @Column(name = "totalParking", columnDefinition = "DECIMAL(10,2)  COMMENT '过路停车费总额'")
    private Double totalParking;


    /**
     * 过路停车费小票总数
     */
    @Column(name = "totalReceipts", columnDefinition = "INT(11)  COMMENT '过路停车费小票总数'")
    private Integer totalReceipts;


    /**
     * 加油小票是否寄件
     */
    @Column(name = "ifSendAddOilReceipts", columnDefinition = "TINYINT(1)  COMMENT '加油小票是否寄件'")
    private Boolean ifSendAddOilReceipts;


    /**
     * 收票人
     */
    @Column(name = "receiver", columnDefinition = "VARCHAR(255)  COMMENT '收票人'")
    private String receiver;

    /**
     * 收到发票日期
     */
    @Column(name = "receiveDate", columnDefinition = "DATE  COMMENT '收到发票日期'")
    private LocalDate receiveDate;

    /**
     * 收到发票情况
     */
    @Column(name = "receiveReceipts", columnDefinition = "VARCHAR(255)  COMMENT '收到发票情况'")
    private String receiveReceipts;

    /**
     * 资金模块负责人
     */
    @Column(name = "moneyModule", columnDefinition = "VARCHAR(255)  COMMENT '资金模块负责人'")
    private String moneyModule;

    /**
     * 资金模块意见
     */
    @Column(name = "moneyModuleIdea", columnDefinition = "VARCHAR(255)  COMMENT '资金模块意见'")
    private String moneyModuleIdea;

    /**
     * 资金模块负责人审核时间
     */
    @Column(name = "moneyDate", columnDefinition = "DATETIME  COMMENT '资金模块负责人审核时间'")
    private LocalDateTime moneyDate;


    /**
     * 预计付款日期
     */
    @Column(name = "expectPayDate", columnDefinition = "DATE  COMMENT '预计付款日期'")
    private LocalDate expectPayDate;

    /**
     * 付款计划
     */
    @Column(name = "paymentSchedule", columnDefinition = "VARCHAR(255)  COMMENT '付款计划'")
    private String paymentSchedule;

    /**
     * 是否付款
     */
    @Column(name = "ifPayed", columnDefinition = "TINYINT(1) COMMENT '是否付款'")
    private Boolean ifPayed;



//    /**
//     * 处罚汇总
//     */
//    @Column(name = "punishCost", columnDefinition = "DECIMAL(10,2)   COMMENT '处罚汇总'")
//    private Double punishCost;
//
//    /**
//     * 异常分析
//     */
//    @Column(name = "exceptionAnalyze", columnDefinition = "VARCHAR(255)   COMMENT '异常分析'")
//    private String exceptionAnalyze;
//

//
//    /**
//     * 资金模块意见
//     */
//    @Column(name = "fundModuleSugg", columnDefinition = "VARCHAR(255)   COMMENT '资金模块意见'")
//    private String fundModuleSugg;
//
//    /**
//     * 资金审核时间
//     */
//    @Column(name = "fundAudtiTime", columnDefinition = "DATETIME   COMMENT '资金模块意见'")
//    private LocalDateTime fundAuditTime;
//
//    /**
//     * 资金审核人
//     */
//    @Column(name = "fundAudtiUser", columnDefinition = "VARCHAR(255)   COMMENT '资金模块意见'")
//    private String fundAuditUser;
//
//    /**
//     * 预算模块意见
//     */
//    @Column(name = "budgetModuleSugg", columnDefinition = "VARCHAR(255)   COMMENT '预算模块意见'")
//    private String budgetModuleSugg;

//

//
//    /**
//     * 任务下达人意见
//     */
//    @Column(name = "principalSugg", columnDefinition = "VARCHAR(255)   COMMENT '任务下达人意见'")
//    private String principalSugg;
//
    /**
     * 任务下达人审核时间
     */
    @Column(name = "principalAuditTime", columnDefinition = "DATETIME   COMMENT '任务下达人意见'")
    private LocalDateTime principalAuditTime;
//
//    /**
//     * 是否通过
//     */
//    @Column(name = "is_auditResult", columnDefinition = "TINYINT(1)  COMMENT '是否通过,0否1是'")
//    private Boolean auditResult;
//
//    /**
//     * 是否付款
//     */
//    @Column(name = "is_pay", columnDefinition = "TINYINT(1)   COMMENT '是否付款,0否1是'")
//    private Boolean pay;
//
    /**
     * 租车费用
     */
    @Column(name = "carRentalCost",  columnDefinition = "DECIMAL(10,2)   COMMENT '租车费用'")
    private Double carRentalCost;
//
//    /**
//     * 预计付款时间
//     */
//    @Column(name = "budgetPayDate", columnDefinition = "DATE   COMMENT '预计付款时间'")
//    private LocalDate budgetPayDate;
//
//    /**
//     * 付款计划
//     */
//    @Column(name = "payPlan", columnDefinition = "VARCHAR(255)   COMMENT '付款计划'")
//    private String payPlan;
//
//    /**
//     * 签收小票日期
//     */
//    @Column(name = "receiveReceiptDate", columnDefinition = "DATE   COMMENT '签收小票日期'")
//    private LocalDate receiveReceiptDate;
//
//    /**
//     * 小票审核情况
//     */
//    @Column(name = "auditReceiptSugg", columnDefinition = "VARCHAR(255)   COMMENT '小票审核情况'")
//    private String auditReceiptSugg;
//
//    /**
//     * 小票审核结果
//     */
//    @Column(name = "auditReceiptResult", columnDefinition = "TINYINT(1)   COMMENT '小票审核结果'")
//    private Boolean auditReceiptResult;
//
    /**
     * 数据状态
     */
    @Column(columnDefinition = "TINYINT(2) DEFAULT '0'  COMMENT '小票审核情况'", insertable = false)
    private Status status;
//
    /**
     * 查询类型
     */
    @Column(name = "findType",  columnDefinition = "TINYINT(2)   COMMENT '查询类型'")
    private FindType findType;
//
//    /**
//     * 油耗
//     */
//    @Column(name = "oilWear", columnDefinition = "DECIMAL(10,2)   COMMENT '总油耗'")
//    private Double oilWear;
//
//    /**
//     * 油费
//     */
//    @Column(name = "oilCost", columnDefinition = "DECIMAL(10,2)   COMMENT '油费'")
//    private Double oilCost;
//
    /**
     * 加班费
     */
    @Column(name = "overWorkCost", columnDefinition = "DECIMAL(10,2)   COMMENT '加班费'")
    private Double overWorkCost;

    /**
     * 金额
     */
    @Column(name = "cost", columnDefinition = "DECIMAL(10,2)   COMMENT '金额，除了油费的所有费用'")
    private Double cost;
//
//    /**
//     * 合计金额
//     */
//    @Column(name = "totalCost", columnDefinition = "DECIMAL(10,2)   COMMENT '金额，全部费用'")
//    private Double totalCost;


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

    public Double getOverWorkTime() {
        return overWorkTime;
    }

    public void setOverWorkTime(Double overWorkTime) {
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

    public Evaluate getEvaluatedriver() {
        return evaluatedriver;
    }

    public void setEvaluatedriver(Evaluate evaluatedriver) {
        this.evaluatedriver = evaluatedriver;
    }

    public Boolean getReceipt() {
        return receipt;
    }

    public void setReceipt(Boolean receipt) {
        this.receipt = receipt;
    }

    public CarSource getCarSource() {
        return carSource;
    }

    public void setCarSource(CarSource carSource) {
        this.carSource = carSource;
    }

    public Double getSupplementOil() {
        return supplementOil;
    }

    public void setSupplementOil(Double supplementOil) {
        this.supplementOil = supplementOil;
    }

    public Double getSupplementFee() {
        return supplementFee;
    }

    public void setSupplementFee(Double supplementFee) {
        this.supplementFee = supplementFee;
    }

    public Double getLessOil() {
        return lessOil;
    }

    public void setLessOil(Double lessOil) {
        this.lessOil = lessOil;
    }

    public Double getLessOilFee() {
        return lessOilFee;
    }

    public void setLessOilFee(Double lessOilFee) {
        this.lessOilFee = lessOilFee;
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

    public String getProjectCharge() {
        return projectCharge;
    }

    public void setProjectCharge(String projectCharge) {
        this.projectCharge = projectCharge;
    }

    public String getProjectChargeIdea() {
        return projectChargeIdea;
    }

    public void setProjectChargeIdea(String projectChargeIdea) {
        this.projectChargeIdea = projectChargeIdea;
    }

    public Boolean getIfPass() {
        return ifPass;
    }

    public void setIfPass(Boolean ifPass) {
        this.ifPass = ifPass;
    }

    public String getBudgetModuleIdea() {
        return budgetModuleIdea;
    }

    public void setBudgetModuleIdea(String budgetModuleIdea) {
        this.budgetModuleIdea = budgetModuleIdea;
    }

    public String getAccountModuleIdea() {
        return accountModuleIdea;
    }

    public void setAccountModuleIdea(String accountModuleIdea) {
        this.accountModuleIdea = accountModuleIdea;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Boolean getIfSendArchiveAl() {
        return ifSendArchiveAl;
    }

    public void setIfSendArchiveAl(Boolean ifSendArchiveAl) {
        this.ifSendArchiveAl = ifSendArchiveAl;
    }

    public Boolean getIfSendReimbursementAl() {
        return ifSendReimbursementAl;
    }

    public void setIfSendReimbursementAl(Boolean ifSendReimbursementAl) {
        this.ifSendReimbursementAl = ifSendReimbursementAl;
    }

    public Double getTotalParking() {
        return totalParking;
    }

    public void setTotalParking(Double totalParking) {
        this.totalParking = totalParking;
    }

    public Integer getTotalReceipts() {
        return totalReceipts;
    }

    public void setTotalReceipts(Integer totalReceipts) {
        this.totalReceipts = totalReceipts;
    }

    public Boolean getIfSendAddOilReceipts() {
        return ifSendAddOilReceipts;
    }

    public void setIfSendAddOilReceipts(Boolean ifSendAddOilReceipts) {
        this.ifSendAddOilReceipts = ifSendAddOilReceipts;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public LocalDate getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(LocalDate receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getReceiveReceipts() {
        return receiveReceipts;
    }

    public void setReceiveReceipts(String receiveReceipts) {
        this.receiveReceipts = receiveReceipts;
    }

    public String getMoneyModuleIdea() {
        return moneyModuleIdea;
    }

    public void setMoneyModuleIdea(String moneyModuleIdea) {
        this.moneyModuleIdea = moneyModuleIdea;
    }


    public String getPaymentSchedule() {
        return paymentSchedule;
    }

    public void setPaymentSchedule(String paymentSchedule) {
        this.paymentSchedule = paymentSchedule;
    }

    public Boolean getIfPayed() {
        return ifPayed;
    }

    public void setIfPayed(Boolean ifPayed) {
        this.ifPayed = ifPayed;
    }

    public Double getMealCost() {
        return mealCost;
    }

    public void setMealCost(Double mealCost) {
        this.mealCost = mealCost;
    }

    public Double getShouldAmount() {
        return shouldAmount;
    }

    public void setShouldAmount(Double shouldAmount) {
        this.shouldAmount = shouldAmount;
    }

    public Double getShouldAmountMoney() {
        return shouldAmountMoney;
    }

    public void setShouldAmountMoney(Double shouldAmountMoney) {
        this.shouldAmountMoney = shouldAmountMoney;
    }

    public Double getAddOilAmountMoney() {
        return addOilAmountMoney;
    }

    public void setAddOilAmountMoney(Double addOilAmountMoney) {
        this.addOilAmountMoney = addOilAmountMoney;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getClientModule() {
        return clientModule;
    }

    public void setClientModule(String clientModule) {
        this.clientModule = clientModule;
    }

    public String getClientModuleIdea() {
        return clientModuleIdea;
    }

    public void setClientModuleIdea(String clientModuleIdea) {
        this.clientModuleIdea = clientModuleIdea;
    }

    public Boolean getIfFreeze() {
        return ifFreeze;
    }

    public void setIfFreeze(Boolean ifFreeze) {
        this.ifFreeze = ifFreeze;
    }

    public String getHeadModule() {
        return headModule;
    }

    public void setHeadModule(String headModule) {
        this.headModule = headModule;
    }

    public String getHeadModuleIdea() {
        return headModuleIdea;
    }

    public void setHeadModuleIdea(String headModuleIdea) {
        this.headModuleIdea = headModuleIdea;
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

    public String getAccountModule() {
        return accountModule;
    }

    public void setAccountModule(String accountModule) {
        this.accountModule = accountModule;
    }


    public String getMoneyModule() {
        return moneyModule;
    }

    public void setMoneyModule(String moneyModule) {
        this.moneyModule = moneyModule;
    }



    public LocalDateTime getPrincipalAuditTime() {
        return principalAuditTime;
    }

    public void setPrincipalAuditTime(LocalDateTime principalAuditTime) {
        this.principalAuditTime = principalAuditTime;
    }

    public LocalDateTime getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(LocalDateTime accountDate) {
        this.accountDate = accountDate;
    }

    public LocalDate getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDate sendDate) {
        this.sendDate = sendDate;
    }

    public LocalDateTime getMoneyDate() {
        return moneyDate;
    }

    public void setMoneyDate(LocalDateTime moneyDate) {
        this.moneyDate = moneyDate;
    }

    public LocalDate getExpectPayDate() {
        return expectPayDate;
    }

    public void setExpectPayDate(LocalDate expectPayDate) {
        this.expectPayDate = expectPayDate;
    }

    public FindType getFindType() {
        return findType;
    }

    public void setFindType(FindType findType) {
        this.findType = findType;
    }

    public Double getCarRentalCost() {
        return carRentalCost;
    }

    public void setCarRentalCost(Double carRentalCost) {
        this.carRentalCost = carRentalCost;
    }

    public LocalDateTime getClientDate() {
        return clientDate;
    }

    public void setClientDate(LocalDateTime clientDate) {
        this.clientDate = clientDate;
    }

    public LocalDateTime getHeadDate() {
        return headDate;
    }

    public void setHeadDate(LocalDateTime headDate) {
        this.headDate = headDate;
    }

    public Double getOverWorkCost() {
        return overWorkCost;
    }

    public void setOverWorkCost(Double overWorkCost) {
        this.overWorkCost = overWorkCost;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}