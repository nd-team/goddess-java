package com.bjike.goddess.interiorrecommend.vo;

/**
 * 推荐奖励信息表现层对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-10 04:07 ]
 * @Description: [ 推荐奖励信息表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AwardInfoVO {

    /**
     * id
     */
    private String id;
    /**
     * 推荐信息id
     */
    private String infoId;

    /**
     * 奖励时间
     */
    private String awardTime;

    /**
     * 是否获得奖励
     */
    private Boolean getAward;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getAwardTime() {
        return awardTime;
    }

    public void setAwardTime(String awardTime) {
        this.awardTime = awardTime;
    }

    public Boolean getGetAward() {
        return getAward;
    }

    public void setGetAward(Boolean getAward) {
        this.getAward = getAward;
    }
}