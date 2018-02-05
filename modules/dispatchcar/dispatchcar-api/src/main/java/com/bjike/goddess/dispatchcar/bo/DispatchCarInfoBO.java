package com.bjike.goddess.dispatchcar.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.dispatchcar.enums.Acctype;
import com.bjike.goddess.dispatchcar.enums.Evaluate;
import com.bjike.goddess.dispatchcar.enums.FindType;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * 出车记录业务传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-12 05:26 ]
 * @Description: [ 出车记录业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DispatchCarInfoBO extends BaseBO {

    /**
     * 出车单号
     */
    private String number;


    /**
     * 司机名称
     */
    private String driver;

    /**
     * 是否公司人员出车
     */
    private Boolean companyDispatch;

    /**
     * 用车人
     */
    private String carUser;

    /**
     * 员工编号
     */
    private String userNumber;

    /**
     * 所属地区
     */
    private String area;

    /**
     * 所属项目组
     */
    private String group;

    /**
     * 是否立项
     */
    private Boolean projectApproval;

    /**
     * 项目名称
     */
    private String project;

    /**
     * 科目类型
     */
    private Acctype acctype;

    /**
     * 出车日期
     */
    private String dispatchDate;

    /**
     * 出车开始时间
     */
    private String startTime;

    /**
     * 出车结束时间
     */
    private String endTime;

    /**
     * 是否午休
     */
    private Boolean siesta;

    /**
     * 加班时长
     */
    private Double overWorkTime;

    /**
     * 用车事由
     */
    private String dispatchReason;

    /**
     * 用车随同人员
     */
    private String accompanyUser;

    /**
     * 车牌号码
     */
    private String carNumber;

    /**
     * 所用油卡编号
     */
    private String oilCardNumber;

    /**
     * 是否开空调
     */
    private Boolean aircondition;

    /**
     * 是否市内
     */
    private Boolean downtown;

    /**
     * 当天是否加油
     */
    private Boolean addOil;

    /**
     * 补加油说明
     */
    private String addOilExplain;

    /**
     * 补加油量
     */
    private Double supplementOil;

    /**
     * 补加油费
     */
    private Double supplementFee;

    /**
     * 欠油说明
     */
    private String oweOilExplain;


    /**
     * 欠油量
     */
    private Double lessOil;

    /**
     * 欠油费
     */
    private Double lessOilFee;

    /**
     * 加油时间
     */
    private String addOilTime;

    /**
     * 应加油量
     */
    private Double shouldAmount;

    /**
     * 应加油费
     */
    private Double getShouldAmountMoney;

    /**
     * 实际加油量
     */
    private Double addOilAmount;

    /**
     * 实际加油费
     */
    private Double addOilAmountMoney;

    /**
     * 油卡余额
     */
    private Double oilCardBalance;

    /**
     * 当天油价
     */
    private Double oilPrice;

    /**
     * 任务下达人
     */
    private String principal;

    /**
     * 计划任务数量
     */
    private Integer planTaskAmount;

    /**
     * 完成任务数量
     */
    private Integer finishTaskAmount;

    /**
     * 出车开始里程数
     */
    private Double startMileage;

    /**
     * 出车结束里程数
     */
    private Double endMileage;

    /**
     * 总里程数
     */
    private Double mileageSubtract;

    /**
     * GPS轨迹总里程数
     */
    private Double mileageOfGPS;

    /**
     * 停车费
     */
    private Double parkCost;

    /**
     * 过路费
     */
    private Double roadCost;

    /**
     * 餐补费
     */
    private Double mealCost;

    /**
     * 对司机的评价
     */
    private Evaluate evaluatedriver;

    /**
     * 小票附件
     */
    private Boolean receipt;

    /**
     * 项目模块负责人
     */
    private String projectCharge;

    /**
     * 项目模块负责人意见
     */
    private String projectChargeIdea;


    /**
     * 是否通过
     */
    private Boolean ifPass;

    /**
     * 客户模块负责人
     */
    private String clientModule;

    /**
     * 客户模块负责人意见
     */
    private String clientModuleIdea;

    /**
     * 客户模块负责人审核时间
     */
    private String clientDate;

    /**
     * 是否冻结
     */
    private Boolean ifFreeze;

    /***
     * 素养模块负责人
     */
    private String headModule;

    /**
     * 素养模块负责人意见
     */
    private String headModuleIdea;

    /**
     * 素养模块负责人审核时间
     */
    private String headDate;

    /**
     * 预算模块负责人
     */
    private String budgetAuditUser;
    /**
     * 预算模块负责人意见
     */
    private String budgetModuleIdea;

    /**
     * 预算模块负责人审核时间
     */
    private String budgetAuditTime;

    /**
     * 账务模块负责人
     */
    private String accountModule;

    /**
     * 账务模块意见
     */
    private String accountModuleIdea;

    /**
     * 账务模块负责人审核时间
     */
    private String accountDate;

    /**
     * 核对依据是否齐全无误
     */
    private Boolean ifCorrect;

    /**
     * 寄件人
     */
    private String sender;

    /**
     * 寄件日期
     */
    private String sendDate;


    /**
     * 存档联是否寄件
     */
    private Boolean ifSendArchiveAL;

    /**
     * 报销联是否寄件ｃｕｎｄ
     */
    private Boolean ifSendReimbursementAl;

    /**
     * 过路停车费总额
     */
    private Double totalParking;


    /**
     * 过路停车费小票总数
     */
    private Integer totalReceipts;


    /**
     * 加油小票是否寄件
     */
    private Boolean ifSendAddOilReceipts;


    /**
     * 收票人
     */
    private String receiver;

    /**
     * 收到发票日期
     */
    private String receiveDate;

    /**
     * 收到发票情况
     */
    private String receiveReceipts;

    /**
     * 资金模块负责人
     */
    private String moneyModule;

    /**
     * 资金模块意见
     */
    private String moneyModuleIdea;

    /**
     * 资金模块负责人审核时间
     */
    private String moneyDate;
    /**
     * 预计付款日期
     */
    private String expectPayDate;

    /**
     * 付款计划
     */
    private String paymentSchedule;

    /**
     * 是否付款
     */
    private Boolean ifPayed;

//    /**
//     * 处罚汇总
//     */
//    private Double punishCost;
//
//    /**
//     * 异常分析
//     */
//    private String exceptionAnalyze;
//
//    /**
//     * 餐补费
//     */
//    private Double mealCost;
//
//    /**
//     * 资金模块意见
//     */
//    private String fundModuleSugg;
//
//    /**
//     * 资金审核人
//     */
//    private String fundAuditUser;
//
//    /**
//     * 预算审核人
//     */
//    private String budgetAuditUser;
//
//    /**
//     * 预算模块意见
//     */
//    private String budgetModuleSugg;
//
//
//    /**
//     * 任务下达人意见
//     */
//    private String principalSugg;
//
//    /**
//     * 是否通过
//     */
//    private Boolean auditResult;
//
//    /**
//     * 是否付款
//     */
//    private Boolean pay;
//
    /**
     * 租车费用
     */
    private Double carRentalCost;
//
//    /**
//     * 预计付款时间
//     */
//    private String budgetPayDate;
//
//    /**
//     * 签收小票日期
//     */
//    private String receiveReceiptDate;
//
//    /**
//     * 小票审核情况
//     */
//    private String auditReceiptSugg;
//
//    /**
//     * 小票审核结果
//     */
//    private Boolean auditReceiptResult;
//
    /**
     * 数据状态
     */
    private Status status;
//
//    /**
//     * 资金审核时间
//     */
//    private String fundAudtiTime;
//
//    /**
//     * 预算审核时间
//     */
//    private String budgetAuditTime;
//
    /**
     * 任务下达人审核时间
     */
    private String principalAuditTime;
//
//    /**
//     * 付款计划
//     */
//    private String payPlan;
//
    /**
     * 查询类型
     */
    private FindType findType;

//    /**
//     * 油耗
//     */
//    private Double oilWear;
//
//    /**
//     * 油费
//     */
//    private Double oilCost;
//
    /**
     * 加班费
     */
    private Double overWorkCost;

    /**
     * 金额
     */
    private Double cost;
//
//    /**
//     * 合计金额
//     */
//    private Double totalCost;


    /**
     * 是否有票据
     */
    private Boolean hasBill;

    /**
     * 收件人
     */
    private String sendReceiver;

//    /**
//     * 寄件时间
//     */
//    private String sendDate;

    /**
     * 地区
     */
    private String sendArea;

    /**
     * 详细地址
     */
    private String sendAddress;

    /**
     * 寄件备注
     */
    private String sendRemark;

    public Boolean getHasBill() {
        return hasBill;
    }

    public void setHasBill(Boolean hasBill) {
        this.hasBill = hasBill;
    }

    public String getSendReceiver() {
        return sendReceiver;
    }

    public void setSendReceiver(String sendReceiver) {
        this.sendReceiver = sendReceiver;
    }

    public String getSendArea() {
        return sendArea;
    }

    public void setSendArea(String sendArea) {
        this.sendArea = sendArea;
    }

    public String getSendAddress() {
        return sendAddress;
    }

    public void setSendAddress(String sendAddress) {
        this.sendAddress = sendAddress;
    }

    public String getSendRemark() {
        return sendRemark;
    }

    public void setSendRemark(String sendRemark) {
        this.sendRemark = sendRemark;
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

    public String getDispatchDate() {
        return dispatchDate;
    }

    public void setDispatchDate(String dispatchDate) {
        this.dispatchDate = dispatchDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
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

    public String getAddOilTime() {
        return addOilTime;
    }

    public void setAddOilTime(String addOilTime) {
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

    public Boolean getIfCorrect() {
        return ifCorrect;
    }

    public void setIfCorrect(Boolean ifCorrect) {
        this.ifCorrect = ifCorrect;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public Boolean getIfSendArchiveAL() {
        return ifSendArchiveAL;
    }

    public void setIfSendArchiveAL(Boolean ifSendArchiveAL) {
        this.ifSendArchiveAL = ifSendArchiveAL;
    }

    public String getExpectPayDate() {
        return expectPayDate;
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

    public String getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    public void setExpectPayDate(String expectPayDate) {
        this.expectPayDate = expectPayDate;
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

    public Double getShouldAmount() {
        return shouldAmount;
    }

    public void setShouldAmount(Double shouldAmount) {
        this.shouldAmount = shouldAmount;
    }

    public Double getGetShouldAmountMoney() {
        return getShouldAmountMoney;
    }

    public void setGetShouldAmountMoney(Double getShouldAmountMoney) {
        this.getShouldAmountMoney = getShouldAmountMoney;
    }

    public Double getMealCost() {
        return mealCost;
    }

    public void setMealCost(Double mealCost) {
        this.mealCost = mealCost;
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

    public String getClientDate() {
        return clientDate;
    }

    public void setClientDate(String clientDate) {
        this.clientDate = clientDate;
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

    public String getHeadDate() {
        return headDate;
    }

    public void setHeadDate(String headDate) {
        this.headDate = headDate;
    }

    public String getBudgetAuditUser() {
        return budgetAuditUser;
    }

    public void setBudgetAuditUser(String budgetAuditUser) {
        this.budgetAuditUser = budgetAuditUser;
    }

    public String getBudgetAuditTime() {
        return budgetAuditTime;
    }

    public void setBudgetAuditTime(String budgetAuditTime) {
        this.budgetAuditTime = budgetAuditTime;
    }

    public String getAccountModule() {
        return accountModule;
    }

    public void setAccountModule(String accountModule) {
        this.accountModule = accountModule;
    }

    public String getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(String accountDate) {
        this.accountDate = accountDate;
    }

    public String getMoneyModule() {
        return moneyModule;
    }

    public void setMoneyModule(String moneyModule) {
        this.moneyModule = moneyModule;
    }

    public String getMoneyDate() {
        return moneyDate;
    }

    public void setMoneyDate(String moneyDate) {
        this.moneyDate = moneyDate;
    }

    public String getPrincipalAuditTime() {
        return principalAuditTime;
    }

    public void setPrincipalAuditTime(String principalAuditTime) {
        this.principalAuditTime = principalAuditTime;
    }

    public FindType getFindType() {
        return findType;
    }

    public void setFindType(FindType findType) {
        this.findType = findType;
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

    public Double getAddOilAmountMoney() {
        return addOilAmountMoney;
    }

    public void setAddOilAmountMoney(Double addOilAmountMoney) {
        this.addOilAmountMoney = addOilAmountMoney;
    }

    public Double getCarRentalCost() {
        return carRentalCost;
    }

    public void setCarRentalCost(Double carRentalCost) {
        this.carRentalCost = carRentalCost;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}