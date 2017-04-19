package com.bjike.goddess.firmreward.vo;

/**
 * 获奖明细表现层对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-12 06:02 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AwardDetailVO {

    /**
     * id
     */
    private String id;
    /**
     * 获奖名次
     */
    private String awardRanking;

    /**
     * 获奖人姓名
     */
    private String prizewinner;

    /**
     * 奖金额度
     */
    private Double bonusLimit;

    /**
     * 经验值额度
     */
    private Double empiricalValueLimit;

    /**
     * 荣誉衍生奖品额度
     */
    private Double honorAwardLimit;

    /**
     * 奖励排名id
     */
    private String awardPersonNoStatId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAwardRanking() {
        return awardRanking;
    }

    public void setAwardRanking(String awardRanking) {
        this.awardRanking = awardRanking;
    }

    public String getPrizewinner() {
        return prizewinner;
    }

    public void setPrizewinner(String prizewinner) {
        this.prizewinner = prizewinner;
    }

    public Double getBonusLimit() {
        return bonusLimit;
    }

    public void setBonusLimit(Double bonusLimit) {
        this.bonusLimit = bonusLimit;
    }

    public Double getEmpiricalValueLimit() {
        return empiricalValueLimit;
    }

    public void setEmpiricalValueLimit(Double empiricalValueLimit) {
        this.empiricalValueLimit = empiricalValueLimit;
    }

    public Double getHonorAwardLimit() {
        return honorAwardLimit;
    }

    public void setHonorAwardLimit(Double honorAwardLimit) {
        this.honorAwardLimit = honorAwardLimit;
    }

    public String getAwardPersonNoStatId() {
        return awardPersonNoStatId;
    }

    public void setAwardPersonNoStatId(String awardPersonNoStatId) {
        this.awardPersonNoStatId = awardPersonNoStatId;
    }
}