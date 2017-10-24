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
public class BusinessContractDetailBO extends BaseBO {

    /**
     * 地区
     */
    private String area;

    /**
     * 专业/工期
     */
    private String major;
    /**
     * 内部项目编号
     */
    private String internalProjectNum;
    /**
     * 内部项目名称
     */
    private String innerProject;

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
     * 预估确认时间
     */
    private String forecastTime;

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
     * 预估完工金额
     */
    private Double forecastFinishMoney;
    /**
     * 预估进行金额
     */
    private Double forecastMarchMoney;

    /**
     * 预估转正时间
     */
    private String forecastRoundTime;
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
     * 进行
     */
    private Integer march;

    /**
     * 完工
     */
    private Integer complete;

    /**
     * 到货
     */
    private Integer goods;

    /**
     * 初验
     */
    private Integer initialTest;

    /**
     * 终验
     */
    private Integer finalTest;
    /**
     * 项目负责人
     */
    private String projectCharge;
    /**
     * 总包单位名称
     */
    private String majorCompany;

    /**
     * 分包单位名称
     */
    private String subCompany;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getInternalProjectNum() {
        return internalProjectNum;
    }

    public void setInternalProjectNum(String internalProjectNum) {
        this.internalProjectNum = internalProjectNum;
    }

    public String getInnerProject() {
        return innerProject;
    }

    public void setInnerProject(String innerProject) {
        this.innerProject = innerProject;
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

    public Double getMakeUnit() {
        return makeUnit;
    }

    public void setMakeUnit(Double makeUnit) {
        this.makeUnit = makeUnit;
    }

    public Double getForecastMoney() {
        return forecastMoney;
    }

    public void setForecastMoney(Double forecastMoney) {
        this.forecastMoney = forecastMoney;
    }

    public String getForecastTime() {
        return forecastTime;
    }

    public void setForecastTime(String forecastTime) {
        this.forecastTime = forecastTime;
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

    public Double getForecastFinishMoney() {
        return forecastFinishMoney;
    }

    public void setForecastFinishMoney(Double forecastFinishMoney) {
        this.forecastFinishMoney = forecastFinishMoney;
    }

    public Double getForecastMarchMoney() {
        return forecastMarchMoney;
    }

    public void setForecastMarchMoney(Double forecastMarchMoney) {
        this.forecastMarchMoney = forecastMarchMoney;
    }

    public String getForecastRoundTime() {
        return forecastRoundTime;
    }

    public void setForecastRoundTime(String forecastRoundTime) {
        this.forecastRoundTime = forecastRoundTime;
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

    public Integer getMarch() {
        return march;
    }

    public void setMarch(Integer march) {
        this.march = march;
    }

    public Integer getComplete() {
        return complete;
    }

    public void setComplete(Integer complete) {
        this.complete = complete;
    }

    public Integer getGoods() {
        return goods;
    }

    public void setGoods(Integer goods) {
        this.goods = goods;
    }

    public Integer getInitialTest() {
        return initialTest;
    }

    public void setInitialTest(Integer initialTest) {
        this.initialTest = initialTest;
    }

    public Integer getFinalTest() {
        return finalTest;
    }

    public void setFinalTest(Integer finalTest) {
        this.finalTest = finalTest;
    }

    public String getProjectCharge() {
        return projectCharge;
    }

    public void setProjectCharge(String projectCharge) {
        this.projectCharge = projectCharge;
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
}