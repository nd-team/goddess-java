package com.bjike.goddess.businessproject.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 商务项目合同业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-19 11:36 ]
 * @Description: [ 商务项目合同业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BusinessContractProgressBO extends BaseBO {

    /**
     * 地区
     */
    private String area;

    /**
     * 所属项目组
     */
    private String projectGroup;
    /**
     * 未派工金额
     */
    private Double notTaskMoney;
    /**
     * 已派工金额
     */
    private Double hadTaskMoney;
    /**
     * 合同签订数
     */
    private Integer contractNum;
    /**
     * 通报新合同单数
     */
    private Integer passNum;
    /**
     * 内包项目数
     */
    private Integer insideNum;

    /**
     * 外包项目数
     */
    private Integer outsourcingNum;
    /**
     * 半外包项目数
     */
    private Integer halfOutsourcingNum;

    /**
     * 总金额
     */
    private Double totalMoney;

    /**
     * 立项总金额
     */
    private Double makeMoney;

    /**
     * 结算完成金额
     */
    private Double settlementAmount;

    /**
     * 立项总单价
     */
    private Double makeUnit;

    /**
     * 2014立项金额
     */
    private Double makeMoneyA;

    /**
     * 2015立项金额
     */
    private Double makeMoneyB;

    /**
     * 2016立项金额
     */
    private Double makeMoneyC;

    /**
     * 2017立项金额
     */
    private Double makeMoneyD;

    /**
     * 2018立项金额
     */
    private Double makeMoneyE;
    /**
     * 预估总金额
     */
    private Double forecastMoney;

    /**
     * 预估规模
     */
    private Double scale;

    /**
     * 预估单价
     */
    private Double forecastUnit;

    /**
     * 预估转正金额
     */
    private Double forecastRoundMoney;
    /**
     * 清理派工归属数量
     */
    private Integer forecastFinishMoney;
    /**
     * 解决派工和现场数量不一致数量
     */
    private Integer solutionBalance;
    /**
     * 不需派工单但要跟进回款单数
     */
    private Integer notTaskNum;
    /**
     * 不需派工单但要跟进回款金额
     */
    private Double notTaskBack;
    /**
     * 预估市场亏损金额
     */
    private Double estimatedMarketLosses;

    /**
     * 已立项合同单数数量
     */
    private Integer hadMakeNum;

    /**
     * 未立项合同单数数量
     */
    private Integer noMakeNum;

    /**
     * 不立项合同单数数量
     */
    private Integer notMakeNum;

    /**
     * 总规模数量
     */
    private Double scaleContract;

    /**
     * 实际完成规模数量
     */
    private Double finishScale;

    /**
     * 未进场
     */
    private Integer notApproach;

    /**
     * 进场
     */
    private Integer approach;

    /**
     * 停工
     */
    private Integer shutdown;


    /**
     * 已完工单数
     */
    private Integer complete;
    /**
     * 完工金额
     */
    private Double completeMoney;
    /**
     * 进行
     */
    private Integer march;

    /**
     * 已初验单数
     */
    private Integer initialTest;
    /**
     * 初验金额
     */
    private Double initialTestMoney;

    /**
     * 已终验单数
     */
    private Integer finalTest;
    /**
     * 终验金额
     */
    private Double finalTestMoney;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public Double getNotTaskMoney() {
        return notTaskMoney;
    }

    public void setNotTaskMoney(Double notTaskMoney) {
        this.notTaskMoney = notTaskMoney;
    }

    public Double getHadTaskMoney() {
        return hadTaskMoney;
    }

    public void setHadTaskMoney(Double hadTaskMoney) {
        this.hadTaskMoney = hadTaskMoney;
    }

    public Integer getContractNum() {
        return contractNum;
    }

    public void setContractNum(Integer contractNum) {
        this.contractNum = contractNum;
    }

    public Integer getPassNum() {
        return passNum;
    }

    public void setPassNum(Integer passNum) {
        this.passNum = passNum;
    }

    public Integer getInsideNum() {
        return insideNum;
    }

    public void setInsideNum(Integer insideNum) {
        this.insideNum = insideNum;
    }

    public Integer getOutsourcingNum() {
        return outsourcingNum;
    }

    public void setOutsourcingNum(Integer outsourcingNum) {
        this.outsourcingNum = outsourcingNum;
    }

    public Integer getHalfOutsourcingNum() {
        return halfOutsourcingNum;
    }

    public void setHalfOutsourcingNum(Integer halfOutsourcingNum) {
        this.halfOutsourcingNum = halfOutsourcingNum;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Double getMakeMoney() {
        return makeMoney;
    }

    public void setMakeMoney(Double makeMoney) {
        this.makeMoney = makeMoney;
    }

    public Double getSettlementAmount() {
        return settlementAmount;
    }

    public void setSettlementAmount(Double settlementAmount) {
        this.settlementAmount = settlementAmount;
    }

    public Double getMakeUnit() {
        return makeUnit;
    }

    public void setMakeUnit(Double makeUnit) {
        this.makeUnit = makeUnit;
    }

    public Double getMakeMoneyA() {
        return makeMoneyA;
    }

    public void setMakeMoneyA(Double makeMoneyA) {
        this.makeMoneyA = makeMoneyA;
    }

    public Double getMakeMoneyB() {
        return makeMoneyB;
    }

    public void setMakeMoneyB(Double makeMoneyB) {
        this.makeMoneyB = makeMoneyB;
    }

    public Double getMakeMoneyC() {
        return makeMoneyC;
    }

    public void setMakeMoneyC(Double makeMoneyC) {
        this.makeMoneyC = makeMoneyC;
    }

    public Double getMakeMoneyD() {
        return makeMoneyD;
    }

    public void setMakeMoneyD(Double makeMoneyD) {
        this.makeMoneyD = makeMoneyD;
    }

    public Double getMakeMoneyE() {
        return makeMoneyE;
    }

    public void setMakeMoneyE(Double makeMoneyE) {
        this.makeMoneyE = makeMoneyE;
    }

    public Double getForecastMoney() {
        return forecastMoney;
    }

    public void setForecastMoney(Double forecastMoney) {
        this.forecastMoney = forecastMoney;
    }

    public Double getScale() {
        return scale;
    }

    public void setScale(Double scale) {
        this.scale = scale;
    }

    public Double getForecastUnit() {
        return forecastUnit;
    }

    public void setForecastUnit(Double forecastUnit) {
        this.forecastUnit = forecastUnit;
    }

    public Double getForecastRoundMoney() {
        return forecastRoundMoney;
    }

    public void setForecastRoundMoney(Double forecastRoundMoney) {
        this.forecastRoundMoney = forecastRoundMoney;
    }

    public Integer getForecastFinishMoney() {
        return forecastFinishMoney;
    }

    public void setForecastFinishMoney(Integer forecastFinishMoney) {
        this.forecastFinishMoney = forecastFinishMoney;
    }

    public Integer getSolutionBalance() {
        return solutionBalance;
    }

    public void setSolutionBalance(Integer solutionBalance) {
        this.solutionBalance = solutionBalance;
    }

    public Integer getNotTaskNum() {
        return notTaskNum;
    }

    public void setNotTaskNum(Integer notTaskNum) {
        this.notTaskNum = notTaskNum;
    }

    public Double getNotTaskBack() {
        return notTaskBack;
    }

    public void setNotTaskBack(Double notTaskBack) {
        this.notTaskBack = notTaskBack;
    }

    public Double getEstimatedMarketLosses() {
        return estimatedMarketLosses;
    }

    public void setEstimatedMarketLosses(Double estimatedMarketLosses) {
        this.estimatedMarketLosses = estimatedMarketLosses;
    }

    public Integer getHadMakeNum() {
        return hadMakeNum;
    }

    public void setHadMakeNum(Integer hadMakeNum) {
        this.hadMakeNum = hadMakeNum;
    }

    public Integer getNoMakeNum() {
        return noMakeNum;
    }

    public void setNoMakeNum(Integer noMakeNum) {
        this.noMakeNum = noMakeNum;
    }

    public Integer getNotMakeNum() {
        return notMakeNum;
    }

    public void setNotMakeNum(Integer notMakeNum) {
        this.notMakeNum = notMakeNum;
    }

    public Double getScaleContract() {
        return scaleContract;
    }

    public void setScaleContract(Double scaleContract) {
        this.scaleContract = scaleContract;
    }

    public Double getFinishScale() {
        return finishScale;
    }

    public void setFinishScale(Double finishScale) {
        this.finishScale = finishScale;
    }

    public Integer getNotApproach() {
        return notApproach;
    }

    public void setNotApproach(Integer notApproach) {
        this.notApproach = notApproach;
    }

    public Integer getApproach() {
        return approach;
    }

    public void setApproach(Integer approach) {
        this.approach = approach;
    }

    public Integer getShutdown() {
        return shutdown;
    }

    public void setShutdown(Integer shutdown) {
        this.shutdown = shutdown;
    }

    public Integer getComplete() {
        return complete;
    }

    public void setComplete(Integer complete) {
        this.complete = complete;
    }

    public Double getCompleteMoney() {
        return completeMoney;
    }

    public void setCompleteMoney(Double completeMoney) {
        this.completeMoney = completeMoney;
    }

    public Integer getMarch() {
        return march;
    }

    public void setMarch(Integer march) {
        this.march = march;
    }

    public Integer getInitialTest() {
        return initialTest;
    }

    public void setInitialTest(Integer initialTest) {
        this.initialTest = initialTest;
    }

    public Double getInitialTestMoney() {
        return initialTestMoney;
    }

    public void setInitialTestMoney(Double initialTestMoney) {
        this.initialTestMoney = initialTestMoney;
    }

    public Integer getFinalTest() {
        return finalTest;
    }

    public void setFinalTest(Integer finalTest) {
        this.finalTest = finalTest;
    }

    public Double getFinalTestMoney() {
        return finalTestMoney;
    }

    public void setFinalTestMoney(Double finalTestMoney) {
        this.finalTestMoney = finalTestMoney;
    }
}