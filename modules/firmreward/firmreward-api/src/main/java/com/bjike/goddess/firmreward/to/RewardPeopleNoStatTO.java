package com.bjike.goddess.firmreward.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

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
public class RewardPeopleNoStatTO extends BaseTO {

    public interface IAwardDetail {
    }

    /**
     * 月份
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "月份不能为空")
    private String month;

    /**
     * 奖励项目
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "奖励项目不能为空")
    private String awardProgram;

    /**
     * 排名人数
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "排名人数不能为空")
    private Integer rankingPeopleNo;

    /**
     * 获奖人数
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "获奖人数不能为空")
    private Integer awardPersonNo;

    /**
     * 获奖名次
     */
    @NotNull(groups = {RewardPeopleNoStatTO.IAwardDetail.class}, message = "获奖名次不能为空")
    private String[] awardRankings;

    /**
     * 获奖人姓名
     */
    @NotNull(groups = {RewardPeopleNoStatTO.IAwardDetail.class}, message = "获奖人姓名不能为空")
    private String[] prizewinners;

    /**
     * 奖金额度
     */
    @NotNull(groups = {RewardPeopleNoStatTO.IAwardDetail.class}, message = "奖金额度不能为空")
    private Double[] bonusLimits;

    /**
     * 经验值额度
     */
    @NotNull(groups = {RewardPeopleNoStatTO.IAwardDetail.class}, message = "经验值额度不能为空")
    private Double[] empiricalValueLimits;

    /**
     * 荣誉衍生奖品额度
     */
    @NotNull(groups = {RewardPeopleNoStatTO.IAwardDetail.class}, message = "经验值额度不能为空")
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