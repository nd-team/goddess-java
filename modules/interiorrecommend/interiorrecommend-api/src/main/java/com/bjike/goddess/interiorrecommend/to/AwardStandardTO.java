package com.bjike.goddess.interiorrecommend.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 推荐奖励要求标准
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-10 11:39 ]
 * @Description: [ 推荐奖励要求标准 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AwardStandardTO extends BaseTO {

    /**
     * 推荐要求设定id
     */
    private String requireId;

    /**
     * 奖励周期
     */
    private Integer awardCycle;

    /**
     * 奖励类型
     */
    private String awardType;

    /**
     * 奖励内容
     */
    private String awardContent;

    /**
     * 奖励数量
     */
    private Integer awardAmount;

    /**
     * 奖励发放方式
     */
    private String awardSendWay;

    /**
     * 奖励预算
     */
    private Double awardCost;

    /**
     * 奖励发放人
     */
    private String awardGrantor;


    public String getRequireId() {
        return requireId;
    }

    public void setRequireId(String requireId) {
        this.requireId = requireId;
    }

    public Integer getAwardCycle() {
        return awardCycle;
    }

    public void setAwardCycle(Integer awardCycle) {
        this.awardCycle = awardCycle;
    }

    public String getAwardType() {
        return awardType;
    }

    public void setAwardType(String awardType) {
        this.awardType = awardType;
    }

    public String getAwardContent() {
        return awardContent;
    }

    public void setAwardContent(String awardContent) {
        this.awardContent = awardContent;
    }

    public Integer getAwardAmount() {
        return awardAmount;
    }

    public void setAwardAmount(Integer awardAmount) {
        this.awardAmount = awardAmount;
    }

    public String getAwardSendWay() {
        return awardSendWay;
    }

    public void setAwardSendWay(String awardSendWay) {
        this.awardSendWay = awardSendWay;
    }

    public Double getAwardCost() {
        return awardCost;
    }

    public void setAwardCost(Double awardCost) {
        this.awardCost = awardCost;
    }

    public String getAwardGrantor() {
        return awardGrantor;
    }

    public void setAwardGrantor(String awardGrantor) {
        this.awardGrantor = awardGrantor;
    }
}