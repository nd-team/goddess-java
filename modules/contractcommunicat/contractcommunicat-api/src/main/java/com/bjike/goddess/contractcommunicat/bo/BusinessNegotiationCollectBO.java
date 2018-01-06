package com.bjike.goddess.contractcommunicat.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 商务洽谈业务汇总传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-28 11:24 ]
 * @Description: [ 商务洽谈业务汇总传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BusinessNegotiationCollectBO extends BaseBO {

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组/部门
     */
    private String department;

    /**
     * 问题归属
     */
    private String problemBelong;

    /**
     * 需要洽谈数量
     */
    private Integer needNegotiationAmount;

    /**
     * 需要持续跟进数
     */
    private Integer continueFollowUpAmount;

    /**
     * 已解决(是否闭环:是)
     */
    private Integer solvedAmount;

    /**
     * 未解决(是否闭环:否)
     */
    private Integer notSolvedAmount;

    /**
     * 转换市场招待(转换市场招待:是)
     */
    private Integer marketForAmount;

    /**
     * 路费
     */
    private Double trip;

    /**
     * 洽谈次数
     */
    private Integer rounds;

    /**
     * 需要协助数
     */
    private Integer needAssistAmount;

    /**
     * 发送协助函数次数(是否已发送协助函：是)
     */
    private Integer assistLetterAmount;

    /**
     * 洽谈转入已立项数
     */
    private Integer hasProjectAmount;

    /**
     * 洽谈转入已立项金额
     */
    private Double hasProjectCost;

    /**
     * 是否转入合同管理-市场费用数
     */
    private Integer marketCostAmount;

    /**
     * 是否转入合同管理-市场费用
     */
    private Double marketCost;

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProblemBelong() {
        return problemBelong;
    }

    public void setProblemBelong(String problemBelong) {
        this.problemBelong = problemBelong;
    }

    public Integer getNeedNegotiationAmount() {
        return needNegotiationAmount;
    }

    public void setNeedNegotiationAmount(Integer needNegotiationAmount) {
        this.needNegotiationAmount = needNegotiationAmount;
    }

    public Integer getContinueFollowUpAmount() {
        return continueFollowUpAmount;
    }

    public void setContinueFollowUpAmount(Integer continueFollowUpAmount) {
        this.continueFollowUpAmount = continueFollowUpAmount;
    }

    public Integer getSolvedAmount() {
        return solvedAmount;
    }

    public void setSolvedAmount(Integer solvedAmount) {
        this.solvedAmount = solvedAmount;
    }

    public Integer getNotSolvedAmount() {
        return notSolvedAmount;
    }

    public void setNotSolvedAmount(Integer notSolvedAmount) {
        this.notSolvedAmount = notSolvedAmount;
    }

    public Integer getMarketForAmount() {
        return marketForAmount;
    }

    public void setMarketForAmount(Integer marketForAmount) {
        this.marketForAmount = marketForAmount;
    }

    public Double getTrip() {
        return trip;
    }

    public void setTrip(Double trip) {
        this.trip = trip;
    }

    public Integer getAssistLetterAmount() {
        return assistLetterAmount;
    }

    public void setAssistLetterAmount(Integer assistLetterAmount) {
        this.assistLetterAmount = assistLetterAmount;
    }

    public Integer getHasProjectAmount() {
        return hasProjectAmount;
    }

    public void setHasProjectAmount(Integer hasProjectAmount) {
        this.hasProjectAmount = hasProjectAmount;
    }


    public Integer getRounds() {
        return rounds;
    }

    public void setRounds(Integer rounds) {
        this.rounds = rounds;
    }

    public Integer getMarketCostAmount() {
        return marketCostAmount;
    }

    public void setMarketCostAmount(Integer marketCostAmount) {
        this.marketCostAmount = marketCostAmount;
    }

    public Double getHasProjectCost() {
        return hasProjectCost;
    }

    public void setHasProjectCost(Double hasProjectCost) {
        this.hasProjectCost = hasProjectCost;
    }

    public Double getMarketCost() {
        return marketCost;
    }

    public void setMarketCost(Double marketCost) {
        this.marketCost = marketCost;
    }

    public Integer getNeedAssistAmount() {
        return needAssistAmount;
    }

    public void setNeedAssistAmount(Integer needAssistAmount) {
        this.needAssistAmount = needAssistAmount;
    }
}