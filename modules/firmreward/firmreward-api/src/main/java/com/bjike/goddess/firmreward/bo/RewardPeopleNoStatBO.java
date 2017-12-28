package com.bjike.goddess.firmreward.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;

/**
 * 奖励人数统计业务传输对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-12 05:45 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RewardPeopleNoStatBO extends BaseBO {

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