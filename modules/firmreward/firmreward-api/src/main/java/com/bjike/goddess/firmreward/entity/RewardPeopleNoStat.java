package com.bjike.goddess.firmreward.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 奖励人数统计
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-12 05:45 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "firmreward_rewardpeoplenostat")
public class RewardPeopleNoStat extends BaseEntity {

    /**
     * 月份
     */
    @Column(name = "month", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '月份'")
    private String month;

    /**
     * 奖励项目
     */
    @Column(name = "awardProgram", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '奖励项目'")
    private String awardProgram;

    /**
     * 排名人数
     */
    @Column(name = "rankingPeopleNo", nullable = false, columnDefinition = "INT(11) COMMENT '排名人数'")
    private Integer rankingPeopleNo;

    /**
     * 获奖人数
     */
    @Column(name = "awardPersonNo", nullable = false, columnDefinition = "INT(11) COMMENT '获奖人数'")
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