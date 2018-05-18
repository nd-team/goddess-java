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
}