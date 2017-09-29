package com.bjike.goddess.dispatchcar.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 旧服务器上的出车记录
 *
 * @Author: [ jiangzaixuan ]
 * @Date: [ 2017-09-07 06:20 ]
 * @Description: [ 旧服务器上的出车记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ServerCarInfoTO extends BaseTO {


    /**
     * 状态
     */
    private Integer status;

    /**
     * 填写人
     */
    private String writer;

    /**
     * 地区
     */
    private String area;

    /**
     * 单号
     */
    private String runNum;

    /**
     * 日期
     */
    private String toDate;

    /**
     * 用车人
     */
    private String owner;

    /**
     * 开始里程数
     */
    private Double startNum;

    /**
     * 结束里程数
     */
    private Double endNum;

    /**
     * 是否拍照
     */
    private Integer isPg;

    /**
     * 是否开空调
     */
    private Boolean isAri;

    /**
     * 是否在市内
     */
    private Boolean isCity;

    /**
     * 总油耗
     */
    private Double oilTotal;

    /**
     * 加油量
     */
    private Double oilMass;

    /**
     * 当天油价
     */
    private Double oilPrice;

    /**
     * 加油费
     */
    private Double fuelCosts;

    /**
     * 加油日期
     */
    private String fuelDate;

    /**
     * 油卡余额
     */
    private Double balance;

    /**
     * 随同人员
     */
    private String withOwner;

    /**
     * 模块负责人
     */
    private String name;

    /**
     * 租车单价
     */
    private Double carPrice;

    /**
     * 1
     */
    private Integer isRest;

    /**
     * 加班时间
     */
    private Double overTime;

    /**
     * 开始时间
     */
    private String startDate;

    /**
     * 结束时间
     */
    private String endDate;

    /**
     * 加班单价
     */
    private Double price;

    /**
     * 加班费
     */
    private Double cost;

    /**
     * 停车/过路费
     */
    private Double passCosts;

    /**
     * 1
     */
    private Double footCost;

    /**
     * 总金额
     */
    private Double money;

    /**
     * 合计
     */
    private Double total;

    /**
     * 项目名称
     */
    private String project;

    /**
     * 核内容
     */
    private String matter;

    /**
     * 备注
     */
    private String remark;

    /**
     * 初测/复测
     */
    private String isRetest;

    /**
     * 计划数
     */
    private Double planNum;

    /**
     * 完成数
     */
    private Double finishNum;

    /**
     * 1
     */
    private Integer isTask;

    /**
     * 是否熟悉路况
     */
    private Integer isRoad;

    /**
     * 服务态度
     */
    private String manner;

    /**
     * 司机名称
     */
    private String drivername;

    /**
     * 寄件人
     */
    private String sender;

    /**
     * 寄送时间
     */
    private String sendDate;

    /**
     * 存档联是否寄件
     */
    private Integer isFile;

    /**
     * 报销联是否寄件
     */
    private Integer isApply;

    /**
     * 加油小票是否寄件
     */
    private Integer isNote;

    /**
     * 小票总数
     */
    private Double noteNum;

    /**
     * 过路停车费总额
     */
    private Double carCost;

    /**
     * 收票人
     */
    private String biller;

    /**
     * 收到发票情况
     */
    private String billCase;

    /**
     * 收到发票时间
     */
    private String billDate;

    /**
     * 预计付款时间
     */
    private String planDate;

    /**
     * 付款计划
     */
    private String payPlan;

    /**
     * 审核意见
     */
    private String manageIdea;

    /**
     * 规划模块
     */
    private String planName;

    /**
     * 财务模块审核
     */
    private String finance;

    /**
     * 审核意见
     */
    private String financeIdea;

    /**
     * 付款时间
     */
    private String payDate;

    /**
     * 支付来源
     */
    private String payment;

    /**
     * 1
     */
    private Integer pastState;

    /**
     * 1
     */
    private Integer payState;

    /**
     * 1
     */
    private Integer planState;

    /**
     * 管理审核状态
     */
    private Integer manageState;

    /**
     * 资金审核状态
     */
    private Integer fundState;

    /**
     * 预算审核状态
     */
    private Integer budgetState;

    /**
     * 1
     */
    private String driver_id;

    /**
     * 油卡ID
     */
    private String fatCard_id;

    /**
     * 1
     */
    private String begin_position_id;

    /**
     * 1
     */
    private String end_position_id;

    /**
     * 1
     */
    private String subject;

    /**
     * 1
     */
    private Integer establishment;

    /**
     * 1
     */
    private String projectGroup;

    /**
     * 1
     */
    private Integer company;

    /**
     * 1
     */
    private String refueling;

    /**
     * 1
     */
    private String lessOil;

    /**
     * 1
     */
    private String mark;

    /**
     * 1
     */
    private String milage;

    /**
     * 1
     */
    private String commerce;

    /**
     * 1
     */
    private Integer commerceState;

    /**
     * 1
     */
    private String manage;

    /**
     * 1
     */
    private Integer manageAudit;


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getRunNum() {
        return runNum;
    }

    public void setRunNum(String runNum) {
        this.runNum = runNum;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Double getStartNum() {
        return startNum;
    }

    public void setStartNum(Double startNum) {
        this.startNum = startNum;
    }

    public Double getEndNum() {
        return endNum;
    }

    public void setEndNum(Double endNum) {
        this.endNum = endNum;
    }

    public Integer getIsPg() {
        return isPg;
    }

    public void setIsPg(Integer isPg) {
        this.isPg = isPg;
    }

    public Boolean getIsAri() {
        return isAri;
    }

    public void setIsAri(Boolean isAri) {
        this.isAri = isAri;
    }

    public Boolean getIsCity() {
        return isCity;
    }

    public void setIsCity(Boolean isCity) {
        this.isCity = isCity;
    }

    public Double getOilTotal() {
        return oilTotal;
    }

    public void setOilTotal(Double oilTotal) {
        this.oilTotal = oilTotal;
    }

    public Double getOilMass() {
        return oilMass;
    }

    public void setOilMass(Double oilMass) {
        this.oilMass = oilMass;
    }

    public Double getOilPrice() {
        return oilPrice;
    }

    public void setOilPrice(Double oilPrice) {
        this.oilPrice = oilPrice;
    }

    public Double getFuelCosts() {
        return fuelCosts;
    }

    public void setFuelCosts(Double fuelCosts) {
        this.fuelCosts = fuelCosts;
    }

    public String getFuelDate() {
        return fuelDate;
    }

    public void setFuelDate(String fuelDate) {
        this.fuelDate = fuelDate;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getWithOwner() {
        return withOwner;
    }

    public void setWithOwner(String withOwner) {
        this.withOwner = withOwner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(Double carPrice) {
        this.carPrice = carPrice;
    }

    public Integer getIsRest() {
        return isRest;
    }

    public void setIsRest(Integer isRest) {
        this.isRest = isRest;
    }

    public Double getOverTime() {
        return overTime;
    }

    public void setOverTime(Double overTime) {
        this.overTime = overTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getPassCosts() {
        return passCosts;
    }

    public void setPassCosts(Double passCosts) {
        this.passCosts = passCosts;
    }

    public Double getFootCost() {
        return footCost;
    }

    public void setFootCost(Double footCost) {
        this.footCost = footCost;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getMatter() {
        return matter;
    }

    public void setMatter(String matter) {
        this.matter = matter;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsRetest() {
        return isRetest;
    }

    public void setIsRetest(String isRetest) {
        this.isRetest = isRetest;
    }

    public Double getPlanNum() {
        return planNum;
    }

    public void setPlanNum(Double planNum) {
        this.planNum = planNum;
    }

    public Double getFinishNum() {
        return finishNum;
    }

    public void setFinishNum(Double finishNum) {
        this.finishNum = finishNum;
    }

    public Integer getIsTask() {
        return isTask;
    }

    public void setIsTask(Integer isTask) {
        this.isTask = isTask;
    }

    public Integer getIsRoad() {
        return isRoad;
    }

    public void setIsRoad(Integer isRoad) {
        this.isRoad = isRoad;
    }

    public String getManner() {
        return manner;
    }

    public void setManner(String manner) {
        this.manner = manner;
    }

    public String getDrivername() {
        return drivername;
    }

    public void setDrivername(String drivername) {
        this.drivername = drivername;
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

    public Integer getIsFile() {
        return isFile;
    }

    public void setIsFile(Integer isFile) {
        this.isFile = isFile;
    }

    public Integer getIsApply() {
        return isApply;
    }

    public void setIsApply(Integer isApply) {
        this.isApply = isApply;
    }

    public Integer getIsNote() {
        return isNote;
    }

    public void setIsNote(Integer isNote) {
        this.isNote = isNote;
    }

    public Double getNoteNum() {
        return noteNum;
    }

    public void setNoteNum(Double noteNum) {
        this.noteNum = noteNum;
    }

    public Double getCarCost() {
        return carCost;
    }

    public void setCarCost(Double carCost) {
        this.carCost = carCost;
    }

    public String getBiller() {
        return biller;
    }

    public void setBiller(String biller) {
        this.biller = biller;
    }

    public String getBillCase() {
        return billCase;
    }

    public void setBillCase(String billCase) {
        this.billCase = billCase;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getPlanDate() {
        return planDate;
    }

    public void setPlanDate(String planDate) {
        this.planDate = planDate;
    }

    public String getPayPlan() {
        return payPlan;
    }

    public void setPayPlan(String payPlan) {
        this.payPlan = payPlan;
    }

    public String getManageIdea() {
        return manageIdea;
    }

    public void setManageIdea(String manageIdea) {
        this.manageIdea = manageIdea;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getFinance() {
        return finance;
    }

    public void setFinance(String finance) {
        this.finance = finance;
    }

    public String getFinanceIdea() {
        return financeIdea;
    }

    public void setFinanceIdea(String financeIdea) {
        this.financeIdea = financeIdea;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public Integer getPastState() {
        return pastState;
    }

    public void setPastState(Integer pastState) {
        this.pastState = pastState;
    }

    public Integer getPayState() {
        return payState;
    }

    public void setPayState(Integer payState) {
        this.payState = payState;
    }

    public Integer getPlanState() {
        return planState;
    }

    public void setPlanState(Integer planState) {
        this.planState = planState;
    }

    public Integer getManageState() {
        return manageState;
    }

    public void setManageState(Integer manageState) {
        this.manageState = manageState;
    }

    public Integer getFundState() {
        return fundState;
    }

    public void setFundState(Integer fundState) {
        this.fundState = fundState;
    }

    public Integer getBudgetState() {
        return budgetState;
    }

    public void setBudgetState(Integer budgetState) {
        this.budgetState = budgetState;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public String getFatCard_id() {
        return fatCard_id;
    }

    public void setFatCard_id(String fatCard_id) {
        this.fatCard_id = fatCard_id;
    }

    public String getBegin_position_id() {
        return begin_position_id;
    }

    public void setBegin_position_id(String begin_position_id) {
        this.begin_position_id = begin_position_id;
    }

    public String getEnd_position_id() {
        return end_position_id;
    }

    public void setEnd_position_id(String end_position_id) {
        this.end_position_id = end_position_id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Integer establishment) {
        this.establishment = establishment;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public Integer getCompany() {
        return company;
    }

    public void setCompany(Integer company) {
        this.company = company;
    }

    public String getRefueling() {
        return refueling;
    }

    public void setRefueling(String refueling) {
        this.refueling = refueling;
    }

    public String getLessOil() {
        return lessOil;
    }

    public void setLessOil(String lessOil) {
        this.lessOil = lessOil;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getMilage() {
        return milage;
    }

    public void setMilage(String milage) {
        this.milage = milage;
    }

    public String getCommerce() {
        return commerce;
    }

    public void setCommerce(String commerce) {
        this.commerce = commerce;
    }

    public Integer getCommerceState() {
        return commerceState;
    }

    public void setCommerceState(Integer commerceState) {
        this.commerceState = commerceState;
    }

    public String getManage() {
        return manage;
    }

    public void setManage(String manage) {
        this.manage = manage;
    }

    public Integer getManageAudit() {
        return manageAudit;
    }

    public void setManageAudit(Integer manageAudit) {
        this.manageAudit = manageAudit;
    }
}