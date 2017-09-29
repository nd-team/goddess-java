package com.bjike.goddess.dispatchcar.excel;

import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.dispatchcar.enums.Acctype;
import com.bjike.goddess.dispatchcar.enums.CarSource;
import com.bjike.goddess.dispatchcar.enums.Evaluate;
import com.bjike.goddess.dispatchcar.enums.FindType;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-26 15:42]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DispatchCarInfoSetExcel {
    /**
     * 出车记录来源
     */
    @ExcelHeader(name="出车记录来源",notNull = true)
    private CarSource carSource;

    /**
     * 出车单号
     */
    @ExcelHeader(name="出车单号",notNull = true)
    private String number;

    /**
     * 司机名称
     */
    @ExcelHeader(name="司机名称",notNull = true)
    private String driver;

    /**
     * 是否公司人员出车
     */
    @ExcelHeader(name="是否公司人员出车",notNull = true)
    private Boolean companyDispatch;

    /**
     * 用车人
     */
    @ExcelHeader(name="用车人",notNull = true)
    private String carUser;

    /**
     * 员工编号
     */
    @ExcelHeader(name="员工编号",notNull = true)
    private String userNumber;

    /**
     * 所属地区
     */
    @ExcelHeader(name="所属地区",notNull = true)
    private String area;

    /**
     * 所属项目组
     */
    @ExcelHeader(name="所属项目组",notNull = true)
    private String group;

    /**
     * 是否立项
     */
    @ExcelHeader(name="是否立项",notNull = true)
    private Boolean projectApproval;

    /**
     * 项目名称
     */
    @ExcelHeader(name="项目名称",notNull = true)
    private String project;

    /**
     * 科目类型
     */
    @ExcelHeader(name="科目类型",notNull = true)
    private Acctype acctype;

    /**
     * 出车日期
     */
    @ExcelHeader(name="出车日期",notNull = true)
    private String dispatchDate;

    /**
     * 出车开始时间
     */
    @ExcelHeader(name="出车开始时间",notNull = true)
    private String startTime;

    /**
     * 出车结束时间
     */
    @ExcelHeader(name="出车结束时间",notNull = true)
    private String endTime;

    /**
     * 是否午休
     */
    @ExcelHeader(name="是否午休",notNull = true)
    private Boolean siesta;

    /**
     * 加班时长
     */
    @ExcelHeader(name="加班时长",notNull = true)
    private Double overWorkTime;

    /**
     * 用车事由
     */
    @ExcelHeader(name="用车事由",notNull = true)
    private String dispatchReason;

    /**
     * 用车随同人员
     */
    @ExcelHeader(name="用车随同人员",notNull = true)
    private String accompanyUser;

    /**
     * 车牌号码
     */
    @ExcelHeader(name="车牌号码",notNull = true)
    private String carNumber;

    /**
     * 所用油卡编号
     */
    @ExcelHeader(name="所用油卡编号",notNull = true)
    private String oilCardNumber;

    /**
     * 是否开空调
     */
    @ExcelHeader(name="是否开空调",notNull = true)
    private Boolean aircondition;

    /**
     * 是否市内
     */
    @ExcelHeader(name="是否市内",notNull = true)
    private Boolean downtown;

    /**
     * 当天是否加油
     */
    @ExcelHeader(name="当天是否加油",notNull = true)
    private Boolean addOil;

    /**
     * 补加油说明
     */
    @ExcelHeader(name="补加油说明",notNull = true)
    private String addOilExplain;

    /**
     * 补加油量
     */
    @ExcelHeader(name="补加油量",notNull = true)
    private Double supplementOil;

    /**
     * 补加油费
     */
    @ExcelHeader(name="补加油费",notNull = true)
    private Double supplementFee;


    /**
     * 欠油说明
     */
    @ExcelHeader(name="欠油说明",notNull = true)
    private String oweOilExplain;


    /**
     * 欠油量
     */
    @ExcelHeader(name="欠油量",notNull = true)
    private Double lessOil;

    /**
     * 欠油费
     */
    @ExcelHeader(name="欠油费",notNull = true)
    private Double lessOilFee;



    /**
     * 加油时间
     */
    @ExcelHeader(name="加油时间",notNull = true)
    private String addOilTime;

    /**
     * 应加油量
     */
    @ExcelHeader(name="应加油量",notNull = true)
    private Double shouldAmount;

    /**
     * 应加油费
     */
    @ExcelHeader(name="应加油费",notNull = true)
    private Double shouldAmountMoney;

    /**
     * 实际加油量
     */
    @ExcelHeader(name="实际加油量",notNull = true)
    private Double addOilAmount;

    /**
     * 实际加油费
     */
    @ExcelHeader(name="实际加油费",notNull = true)
    private Double addOilAmountMoney;

    /**
     * 油卡余额
     */
    @ExcelHeader(name="油卡余额",notNull = true)
    private Double oilCardBalance;

    /**
     * 当天油价
     */
    @ExcelHeader(name="当天油价",notNull = true)
    private Double oilPrice;

    /**
     * 任务下达人
     */
    @ExcelHeader(name="任务下达人",notNull = true)
    private String principal;

    /**
     * 计划任务数量
     */
    @ExcelHeader(name="计划任务数量",notNull = true)
    private Integer planTaskAmount;

    /**
     * 完成任务数量
     */
    @ExcelHeader(name="完成任务数量",notNull = true)
    private Integer finishTaskAmount;

    /**
     * 出车开始里程数
     */
    @ExcelHeader(name="出车开始里程数",notNull = true)
    private Double startMileage;

    /**
     * 出车结束里程数
     */
    @ExcelHeader(name="出车结束里程数",notNull = true)
    private Double endMileage;

    /**
     * 总里程数
     */
    @ExcelHeader(name="总里程数",notNull = true)
    private Double mileageSubtract;

    /**
     * GPS轨迹总里程数
     */
    @ExcelHeader(name="GPS轨迹总里程数",notNull = true)
    private Double mileageOfGPS;

    /**
     * 停车费
     */
    @ExcelHeader(name="停车费",notNull = true)
    private Double parkCost;

    /**
     * 过路费
     */
    @ExcelHeader(name="过路费",notNull = true)
    private Double roadCost;

    /**
     * 餐补费
     */
    @ExcelHeader(name="餐补费",notNull = true)
    private Double mealCost;

    /**
     * 对司机的评价
     */
    @ExcelHeader(name="对司机的评价",notNull = true)
    private Evaluate evaluatedriver;

    /**
     * 小票附件
     */
    @ExcelHeader(name="小票附件",notNull = true)
    private Boolean receipt;

    /**
     * 项目模块负责人
     */
    @ExcelHeader(name="项目模块负责人",notNull = true)
    private String projectCharge;

    /**
     * 项目模块负责人意见
     */
    @ExcelHeader(name="项目模块负责人意见",notNull = true)
    private String projectChargeIdea;


    /**
     * 是否通过
     */
    @ExcelHeader(name="是否通过",notNull = true)
    private Boolean ifPass;

    /**
     * 客户模块负责人
     */
    @ExcelHeader(name="客户模块负责人",notNull = true)
    private String clientModule;

    /**
     * 客户模块负责人意见
     */
    @ExcelHeader(name="客户模块负责人意见",notNull = true)
    private String clientModuleIdea;


    /**
     * 客户模块负责人审核时间
     */
    @ExcelHeader(name="客户模块负责人审核时间",notNull = true)
    private String clientDate;

    /**
     * 是否冻结
     */
    @ExcelHeader(name="是否冻结",notNull = true)
    private Boolean ifFreeze;

    /**
     * 素养模块负责人
     */
    @ExcelHeader(name="素养模块负责人",notNull = true)
    private String headModule;

    /**
     * 素养模块负责人意见
     */
    @ExcelHeader(name="素养模块负责人意见",notNull = true)
    private String headModuleIdea;


    /**
     * 素养模块负责人审核时间
     */
    @ExcelHeader(name="素养模块负责人审核时间",notNull = true)
    private String headDate;

    /**
     * 预算模块负责人
     */
    @ExcelHeader(name="预算模块负责人",notNull = true)
    private String budgetAuditUser;

    /**
     * 预算模块意见
     */
    @ExcelHeader(name="预算模块意见",notNull = true)
    private String budgetModuleIdea;

    /**
     * 预算模块负责人审核时间
     */
    @ExcelHeader(name="是否公司人员出车",notNull = true)
    private String budgetAuditTime;

    /**
     * 账务模块负责人
     */
    @ExcelHeader(name="账务模块负责人",notNull = true)
    private String accountModule;

    /**
     * 账务模块意见
     */
    @ExcelHeader(name="账务模块意见",notNull = true)
    private String accountModuleIdea;

    /**
     * 账务模块负责人审核时间
     */
    @ExcelHeader(name="账务模块负责人审核时间",notNull = true)
    private String accountDate;


    /**
     * 核对依据是否齐全无误
     */
    @ExcelHeader(name="核对依据是否齐全无误",notNull = true)
    private Boolean isCorrect;

    /**
     * 寄件人
     */
    @ExcelHeader(name="寄件人",notNull = true)
    private String sender;

    /**
     * 寄件日期
     */
    @ExcelHeader(name="寄件日期",notNull = true)
    private String sendDate;


    /**
     * 存档联是否寄件
     */
    @ExcelHeader(name="存档联是否寄件",notNull = true)
    private Boolean ifSendArchiveAl;

    /**
     * 报销联是否寄件
     */
    @ExcelHeader(name="报销联是否寄件",notNull = true)
    private Boolean ifSendReimbursementAl;

    /**
     * 过路停车费总额
     */
    @ExcelHeader(name="过路停车费总额",notNull = true)
    private Double totalParking;


    /**
     * 过路停车费小票总数
     */
    @ExcelHeader(name="过路停车费小票总数",notNull = true)
    private Integer totalReceipts;


    /**
     * 加油小票是否寄件
     */
    @ExcelHeader(name="加油小票是否寄件",notNull = true)
    private Boolean ifSendAddOilReceipts;


    /**
     * 收票人
     */
    @ExcelHeader(name="收票人",notNull = true)
    private String receiver;

    /**
     * 收到发票日期
     */
    @ExcelHeader(name="收到发票日期",notNull = true)
    private String receiveDate;

    /**
     * 收到发票情况
     */
    @ExcelHeader(name="收到发票情况",notNull = true)
    private String receiveReceipts;

    /**
     * 资金模块负责人
     */
    @ExcelHeader(name="资金模块负责人",notNull = true)
    private String moneyModule;

    /**
     * 资金模块意见
     */
    @ExcelHeader(name="资金模块意见",notNull = true)
    private String moneyModuleIdea;

    /**
     * 资金模块负责人审核时间
     */
    @ExcelHeader(name="资金模块负责人审核时间",notNull = true)
    private String moneyDate;


    /**
     * 预计付款日期
     */
    @ExcelHeader(name="预计付款日期",notNull = true)
    private String expectPayDate;

    /**
     * 付款计划
     */
    @ExcelHeader(name="付款计划",notNull = true)
    private String paymentSchedule;

    /**
     * 是否付款
     */
    @ExcelHeader(name="是否付款",notNull = true)
    private Boolean ifPayed;

    /**
     * 任务下达人审核时间
     */
    @ExcelHeader(name="任务下达人审核时间",notNull = true)
    private String principalAuditTime;

    /**
     * 租车费用
     */
    @ExcelHeader(name="租车费用",notNull = true)
    private Double carRentalCost;


    /**
     * 数据状态
     */
    @ExcelHeader(name="数据状态",notNull = true)
    private Status staus;

    /**
     * 查询类型
     */
    @ExcelHeader(name="查询类型",notNull = true)
    private FindType findType;


    public CarSource getCarSource() {
        return carSource;
    }

    public void setCarSource(CarSource carSource) {
        this.carSource = carSource;
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

    public String getOweOilExplain() {
        return oweOilExplain;
    }

    public void setOweOilExplain(String oweOilExplain) {
        this.oweOilExplain = oweOilExplain;
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

    public String getAddOilTime() {
        return addOilTime;
    }

    public void setAddOilTime(String addOilTime) {
        this.addOilTime = addOilTime;
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

    public Double getAddOilAmount() {
        return addOilAmount;
    }

    public void setAddOilAmount(Double addOilAmount) {
        this.addOilAmount = addOilAmount;
    }

    public Double getAddOilAmountMoney() {
        return addOilAmountMoney;
    }

    public void setAddOilAmountMoney(Double addOilAmountMoney) {
        this.addOilAmountMoney = addOilAmountMoney;
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

    public Double getMealCost() {
        return mealCost;
    }

    public void setMealCost(Double mealCost) {
        this.mealCost = mealCost;
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

    public String getBudgetModuleIdea() {
        return budgetModuleIdea;
    }

    public void setBudgetModuleIdea(String budgetModuleIdea) {
        this.budgetModuleIdea = budgetModuleIdea;
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

    public String getAccountModuleIdea() {
        return accountModuleIdea;
    }

    public void setAccountModuleIdea(String accountModuleIdea) {
        this.accountModuleIdea = accountModuleIdea;
    }

    public String getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(String accountDate) {
        this.accountDate = accountDate;
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

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
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

    public String getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getReceiveReceipts() {
        return receiveReceipts;
    }

    public void setReceiveReceipts(String receiveReceipts) {
        this.receiveReceipts = receiveReceipts;
    }

    public String getMoneyModule() {
        return moneyModule;
    }

    public void setMoneyModule(String moneyModule) {
        this.moneyModule = moneyModule;
    }

    public String getMoneyModuleIdea() {
        return moneyModuleIdea;
    }

    public void setMoneyModuleIdea(String moneyModuleIdea) {
        this.moneyModuleIdea = moneyModuleIdea;
    }

    public String getMoneyDate() {
        return moneyDate;
    }

    public void setMoneyDate(String moneyDate) {
        this.moneyDate = moneyDate;
    }

    public String getExpectPayDate() {
        return expectPayDate;
    }

    public void setExpectPayDate(String expectPayDate) {
        this.expectPayDate = expectPayDate;
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

    public String getPrincipalAuditTime() {
        return principalAuditTime;
    }

    public void setPrincipalAuditTime(String principalAuditTime) {
        this.principalAuditTime = principalAuditTime;
    }

    public Double getCarRentalCost() {
        return carRentalCost;
    }

    public void setCarRentalCost(Double carRentalCost) {
        this.carRentalCost = carRentalCost;
    }

    public Status getStaus() {
        return staus;
    }

    public void setStaus(Status staus) {
        this.staus = staus;
    }

    public FindType getFindType() {
        return findType;
    }

    public void setFindType(FindType findType) {
        this.findType = findType;
    }
}
