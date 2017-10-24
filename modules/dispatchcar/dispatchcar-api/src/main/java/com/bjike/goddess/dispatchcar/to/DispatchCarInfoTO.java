package com.bjike.goddess.dispatchcar.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.dispatchcar.enums.Acctype;
import com.bjike.goddess.dispatchcar.enums.Evaluate;
import com.bjike.goddess.dispatchcar.enums.FindType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Map;

/**
 * 出车记录
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-12 05:26 ]
 * @Description: [ 出车记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DispatchCarInfoTO extends BaseTO {

    /**
     * 司机名称
     */
    @NotBlank(message = "司机名称不能为空", groups = {ADD.class, EDIT.class})
    private String driver;


    /**
     * 用车人
     */
    @NotBlank(message = "用车人不能为空", groups = {ADD.class, EDIT.class})
    private String carUser;

    /**
     * 员工编号
     */
    @NotBlank(message = "员工编号不能为空", groups = {ADD.class, EDIT.class})
    private String userNumber;

    /**
     * 所属地区
     */
    @NotBlank(message = "所属地区不能为空", groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 所属项目组
     */
    @NotBlank(message = "所属项目组不能为空", groups = {ADD.class, EDIT.class})
    private String group;

//    /**
//     * 是否立项
//     */
//    @NotNull(message = "是否立项不能为空", groups = {ADD.class, EDIT.class})
//    private Boolean projectApproval;

    /**
     * 项目名称
     */
    @NotBlank(message = "项目名称不能为空", groups = {ADD.class, EDIT.class})
    private String project;

    /**
     * 科目类型
     */
    @NotNull(message = "科目类型不能为空", groups = {ADD.class, EDIT.class})
    private Acctype acctype;

    /**
     * 出车日期
     */
    @NotBlank(message = "出车日期不能为空", groups = {ADD.class, EDIT.class})
    private String dispatchDate;

    /**
     * 出车开始时间
     */
    @NotBlank(message = "出车开始时间不能为空", groups = {ADD.class, EDIT.class})
    private String startTime;

    /**
     * 出车结束时间
     */
    @NotBlank(message = "出车结束时间不能为空", groups = {ADD.class, EDIT.class})
    private String endTime;

    /**
     * 是否午休
     */
    @NotNull(message = "是否午休不能为空", groups = {ADD.class, EDIT.class})
    private Boolean siesta;

    /**
     * 加班时长
     */
    @NotNull(message = "加班时长不能为空", groups = {ADD.class, EDIT.class})
    private Double overWorkTime;

    /**
     * 用车事由
     */
    @NotBlank(message = "用车事由不能为空", groups = {ADD.class, EDIT.class})
    private String dispatchReason;

    /**
     * 用车随同人员
     */
    @NotBlank(message = "用车随同人员不能为空", groups = {ADD.class, EDIT.class})
    private String accompanyUser;

    /**
     * 车牌号码
     */
    @NotBlank(message = "车牌号码不能为空", groups = {ADD.class, EDIT.class})
    private String carNumber;

    /**
     * 所用油卡编号
     */
    @NotBlank(message = "所用油卡编号不能为空", groups = {ADD.class, EDIT.class})
    private String oilCardNumber;

    /**
     * 是否开空调
     */
    @NotNull(message = "是否开空调不能为空", groups = {ADD.class, EDIT.class})
    private Boolean aircondition;

    /**
     * 是否市内
     */
    @NotNull(message = "是否市内不能为空", groups = {ADD.class, EDIT.class})
    private Boolean downtown;

    /**
     * 当天是否加油
     */
    @NotNull(message = "当天是否加油不能为空", groups = {ADD.class, EDIT.class})
    private Boolean addOil;

    /**
     * 补加油说明
     */
    @NotBlank(message = "补加油说明不能为空", groups = {ADD.class, EDIT.class})
    private String addOilExplain;


    /**
     * 油卡余额
     */
    @NotNull(message = "油卡余额不能为空", groups = {ADD.class, EDIT.class})
    private Double oilCardBalance;

    /**
     * 欠油说明
     */
    @NotBlank(message = "欠油说明不能为空", groups = {ADD.class, EDIT.class})
    private String oweOilExplain;



    /**
     * 加油时间
     */
    @NotBlank(message = "加油时间不能为空", groups = {ADD.class, EDIT.class})
    private String addOilTime;


    /**
     * 当天油价
     */
    @NotNull(message = "当天油价不能为空", groups = {ADD.class, EDIT.class})
    private Double oilPrice;

    /**
     * 任务下达人
     */
    @NotBlank(message = "任务下达人不能为空", groups = {ADD.class, EDIT.class})
    private String principal;

    /**
     * 计划任务数量
     */
    @NotNull(message = "计划任务数量不能为空", groups = {ADD.class, EDIT.class})
    private Integer planTaskAmount;

    /**
     * 完成任务数量
     */
    @NotNull(message = "完成任务数量不能为空", groups = {ADD.class, EDIT.class})
    private Integer finishTaskAmount;

    /**
     * 出车开始里程数
     */
    @NotNull(message = "出车开始里程数不能为空", groups = {ADD.class, EDIT.class})
    private Double startMileage;

    /**
     * 出车结束里程数
     */
    @NotNull(message = "出车结束里程数不能为空", groups = {ADD.class, EDIT.class})
    private Double endMileage;

    /**
     * GPS轨迹总里程数
     */
    @NotNull(message = "GPS轨迹总里程数不能为空", groups = {ADD.class, EDIT.class})
    private Double mileageOfGPS;

    /**
     * 停车费
     */
    @NotNull(message = "停车费不能为空", groups = {ADD.class, EDIT.class})
    private Double parkCost;

    /**
     * 过路费
     */
    @NotNull(message = "过路费不能为空", groups = {ADD.class, EDIT.class})
    private Double roadCost;

    /**
     * 评价司机
     */
    @NotNull(message = "评价司机不能为空", groups = {ADD.class, EDIT.class})
    private Evaluate evaluatedriver;

    /**
     * 实际加油量
     */
    @NotNull(message = "实际加油量不能为空", groups = {ADD.class, EDIT.class})
    private Double addOilAmount;

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
     * 预算模块意见
     */
    private String budgetModuleIdea;


    /**
     * 账务模块意见
     */
    private String accountModuleIdea;


    /**
     * 核对依据是否齐全无误
     */
    private Boolean isCorrect;

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
     * 报销联是否寄件
     */
    private Boolean ifSendReimbursementAl;



    /**
     * 小票总数
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
     * 资金模块意见
     */
    private String moneyModuleIdea;

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

    /**
     * 租车费用
     */
    @NotNull(message = "租车费用不能为空",groups = {ADD.class, EDIT.class})
    private Double carRentalCost;

//    /**
//     * 处罚汇总
//     */
//    @NotNull(message = "处罚汇总不能为空", groups = {ADD.class, EDIT.class})
//    private Double punishCost;
//
//    /**
//     * 异常分析
//     */
//    @NotBlank(message = "异常分析不能为空", groups = {ADD.class, EDIT.class})
    private String exceptionAnalyze;

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
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

    public Double getOilCardBalance() {
        return oilCardBalance;
    }

    public void setOilCardBalance(Double oilCardBalance) {
        this.oilCardBalance = oilCardBalance;
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

//    public Boolean getProjectApproval() {
//        return projectApproval;
//    }
//
//    public void setProjectApproval(Boolean projectApproval) {
//        this.projectApproval = projectApproval;
//    }

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

//    public Double getPunishCost() {
//        return punishCost;
//    }
//
//    public void setPunishCost(Double punishCost) {
//        this.punishCost = punishCost;
//    }


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

    public Boolean getIfSendArchiveAL() {
        return ifSendArchiveAL;
    }

    public void setIfSendArchiveAL(Boolean ifSendArchiveAL) {
        this.ifSendArchiveAL = ifSendArchiveAL;
    }

    public Boolean getIfSendReimbursementAl() {
        return ifSendReimbursementAl;
    }

    public void setIfSendReimbursementAl(Boolean ifSendReimbursementAl) {
        this.ifSendReimbursementAl = ifSendReimbursementAl;
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

    public String getMoneyModuleIdea() {
        return moneyModuleIdea;
    }

    public void setMoneyModuleIdea(String moneyModuleIdea) {
        this.moneyModuleIdea = moneyModuleIdea;
    }

    public String getSendDate() {
        return sendDate;
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

    public String getExceptionAnalyze() {
        return exceptionAnalyze;
    }

    public void setExceptionAnalyze(String exceptionAnalyze) {
        this.exceptionAnalyze = exceptionAnalyze;
    }

    public Double getCarRentalCost() {
        return carRentalCost;
    }

    public void setCarRentalCost(Double carRentalCost) {
        this.carRentalCost = carRentalCost;
    }

    public Double getAddOilAmount() {
        return addOilAmount;
    }

    public void setAddOilAmount(Double addOilAmount) {
        this.addOilAmount = addOilAmount;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }
}