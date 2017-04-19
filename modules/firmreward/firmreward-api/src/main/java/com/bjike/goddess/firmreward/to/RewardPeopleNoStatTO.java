package com.bjike.goddess.firmreward.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 奖励人数统计
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-12 05:45 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RewardPeopleNoStatTO extends BaseTO {

    /**
     * 月份
     */
    private String month;

    /**
     * 奖励项目
     */
    private String awardProgram;

    /**
     * 排名人数
     */
    private Integer rankingPeopleNo;

    /**
     * 获奖人数
     */
    private Integer awardPersonNo;

    /**
     * 获奖名次
     */
    private String[] awardRankings;

    /**
     * 获奖人姓名
     */
    private String[] prizewinners;

    /**
     * 奖金额度
     */
    private Double[] bonusLimits;

    /**
     * 经验值额度
     */
    private Double[] empiricalValueLimits;

    /**
     * 荣誉衍生奖品额度
     */
    private Double[] honorAwardLimits;


    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getAwardProgram() {
        return awardProgram;
    }

    public void setAwardProgram(String awardProgram) {
        this.awardProgram = awardProgram;
    }

    public Integer getRankingPeopleNo() {
        return rankingPeopleNo;
    }

    public void setRankingPeopleNo(Integer rankingPeopleNo) {
        this.rankingPeopleNo = rankingPeopleNo;
    }

    public Integer getAwardPersonNo() {
        return awardPersonNo;
    }

    public void setAwardPersonNo(Integer awardPersonNo) {
        this.awardPersonNo = awardPersonNo;
    }

    public String[] getAwardRankings() {
        return awardRankings;
    }

    public void setAwardRankings(String[] awardRankings) {
        this.awardRankings = awardRankings;
    }

    public String[] getPrizewinners() {
        return prizewinners;
    }

    public void setPrizewinners(String[] prizewinners) {
        this.prizewinners = prizewinners;
    }

    public Double[] getBonusLimits() {
        return bonusLimits;
    }

    public void setBonusLimits(Double[] bonusLimits) {
        this.bonusLimits = bonusLimits;
    }

    public Double[] getEmpiricalValueLimits() {
        return empiricalValueLimits;
    }

    public void setEmpiricalValueLimits(Double[] empiricalValueLimits) {
        this.empiricalValueLimits = empiricalValueLimits;
    }

    public Double[] getHonorAwardLimits() {
        return honorAwardLimits;
    }

    public void setHonorAwardLimits(Double[] honorAwardLimits) {
        this.honorAwardLimits = honorAwardLimits;
    }
}