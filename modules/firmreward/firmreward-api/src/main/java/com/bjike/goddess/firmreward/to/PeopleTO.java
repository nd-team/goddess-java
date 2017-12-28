package com.bjike.goddess.firmreward.to;

import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 奖励人数统计
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-12 05:45 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PeopleTO extends BaseTO {

    public interface IAwardDetail {
    }

    /**
     * 获奖名次
     */
    @NotNull(groups = {PeopleTO.IAwardDetail.class}, message = "获奖名次不能为空")
    private String awardRankings;

    /**
     * 获奖人姓名
     */
    @NotNull(groups = {PeopleTO.IAwardDetail.class}, message = "获奖人姓名不能为空")
    private String prizewinners;

    /**
     * 奖金额度
     */
    @NotNull(groups = {PeopleTO.IAwardDetail.class}, message = "奖金额度不能为空")
    private Double bonusLimits;

    /**
     * 经验值额度
     */
    @NotNull(groups = {PeopleTO.IAwardDetail.class}, message = "经验值额度不能为空")
    private Double empiricalValueLimits;

    /**
     * 荣誉衍生奖品额度
     */
    @NotNull(groups = {PeopleTO.IAwardDetail.class}, message = "经验值额度不能为空")
    private Double honorAwardLimits;

    public String getAwardRankings() {
        return awardRankings;
    }

    public void setAwardRankings(String awardRankings) {
        this.awardRankings = awardRankings;
    }

    public String getPrizewinners() {
        return prizewinners;
    }

    public void setPrizewinners(String prizewinners) {
        this.prizewinners = prizewinners;
    }

    public Double getBonusLimits() {
        return bonusLimits;
    }

    public void setBonusLimits(Double bonusLimits) {
        this.bonusLimits = bonusLimits;
    }

    public Double getEmpiricalValueLimits() {
        return empiricalValueLimits;
    }

    public void setEmpiricalValueLimits(Double empiricalValueLimits) {
        this.empiricalValueLimits = empiricalValueLimits;
    }

    public Double getHonorAwardLimits() {
        return honorAwardLimits;
    }

    public void setHonorAwardLimits(Double honorAwardLimits) {
        this.honorAwardLimits = honorAwardLimits;
    }
}