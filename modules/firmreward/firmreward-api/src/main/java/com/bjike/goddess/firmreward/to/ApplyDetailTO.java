package com.bjike.goddess.firmreward.to;

import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 奖品申请
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-13 09:04 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ApplyDetailTO extends BaseTO {

    public interface IPrizeDetail{}

    /**
     * 奖品明细
     */
    @NotNull(groups = {ApplyDetailTO.IPrizeDetail.class}, message = "奖品明细不能为空")
    private String[] prizeDetails;

    /**
     * 奖品购置途径
     */
    @NotNull(groups = {ApplyDetailTO.IPrizeDetail.class}, message = "奖品购置途径不能为空")
    private String[] prizeBuyWays;

    /**
     * 奖品发放形式
     */
    @NotNull(groups = {ApplyDetailTO.IPrizeDetail.class}, message = "奖品发放形式不能为空")
    private String[] prizeIssueForms;

    /**
     * 颁奖时间
     */
    @NotNull(groups = {ApplyDetailTO.IPrizeDetail.class}, message = "颁奖时间不能为空")
    private String[] awardTimes;


    public String[] getPrizeDetails() {
        return prizeDetails;
    }

    public void setPrizeDetails(String[] prizeDetails) {
        this.prizeDetails = prizeDetails;
    }

    public String[] getPrizeBuyWays() {
        return prizeBuyWays;
    }

    public void setPrizeBuyWays(String[] prizeBuyWays) {
        this.prizeBuyWays = prizeBuyWays;
    }

    public String[] getPrizeIssueForms() {
        return prizeIssueForms;
    }

    public void setPrizeIssueForms(String[] prizeIssueForms) {
        this.prizeIssueForms = prizeIssueForms;
    }

    public String[] getAwardTimes() {
        return awardTimes;
    }

    public void setAwardTimes(String[] awardTimes) {
        this.awardTimes = awardTimes;
    }
}