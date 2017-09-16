package com.bjike.goddess.interiorrecommend.vo;

import com.bjike.goddess.interiorrecommend.enums.PathWay;
import com.bjike.goddess.interiorrecommend.enums.RecommendType;

import java.time.LocalDate;

/**
* 内部推荐方案实施表现层对象
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-12 11:29 ]
* @Description:	[ 内部推荐方案实施表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class SchemeImplementVO {
    /**
     * id
     */
    private String  id;
    /**
     * 方案名称
     */
    private String  type;

    /**
     * 推荐发起人
     */
    private String  initiator;

    /**
     * 推荐的途径
     */
    private PathWay pathway;

    /**
     * 推荐时间
     */
    private String recommendTime;

    /**
     * 推荐类型
     */
    private RecommendType recommendType;

    /**
     * 推荐岗位
     */
    private String  recommendPosition;

    /**
     * 被推荐人
     */
    private String  beRecommender;

    /**
     * 是否入职
     */
    private Boolean  isEntry;

    /**
     * 是否转正
     */
    private Boolean  isRegular;

    /**
     * 是否获得推荐奖
     */
    private Boolean  isAcquire;

    /**
     * 推荐奖金额
     */
    private Integer  referralBonus;

    /**
     * 奖励金额发放时间
     */
    private String  grantDate;



    public String getId () {
        return id;
    }
    public void setId (String id ) {
        this.id = id ;
    }
    public String getType () {
        return type;
    }
    public void setType (String type ) {
        this.type = type ;
    }
    public String getInitiator () {
        return initiator;
    }
    public void setInitiator (String initiator ) {
        this.initiator = initiator ;
    }

    public PathWay getPathway() {
        return pathway;
    }

    public void setPathway(PathWay pathway) {
        this.pathway = pathway;
    }

    public Boolean getEntry() {
        return isEntry;
    }

    public void setEntry(Boolean entry) {
        isEntry = entry;
    }

    public Boolean getRegular() {
        return isRegular;
    }

    public void setRegular(Boolean regular) {
        isRegular = regular;
    }

    public Boolean getAcquire() {
        return isAcquire;
    }

    public void setAcquire(Boolean acquire) {
        isAcquire = acquire;
    }


    public RecommendType getRecommendType () {
        return recommendType;
    }
    public void setRecommendType (RecommendType recommendType ) {
        this.recommendType = recommendType ;
    }
    public String getRecommendPosition () {
        return recommendPosition;
    }
    public void setRecommendPosition (String recommendPosition ) {
        this.recommendPosition = recommendPosition ;
    }
    public String getBeRecommender () {
        return beRecommender;
    }
    public void setBeRecommender (String beRecommender ) {
        this.beRecommender = beRecommender ;
    }
    public Boolean getIsEntry () {
        return isEntry;
    }
    public void setIsEntry (Boolean isEntry ) {
        this.isEntry = isEntry ;
    }
    public Boolean getIsRegular () {
        return isRegular;
    }
    public void setIsRegular (Boolean isRegular ) {
        this.isRegular = isRegular ;
    }
    public Boolean getIsAcquire () {
        return isAcquire;
    }
    public void setIsAcquire (Boolean isAcquire ) {
        this.isAcquire = isAcquire ;
    }
    public Integer getReferralBonus () {
        return referralBonus;
    }
    public void setReferralBonus (Integer referralBonus ) {
        this.referralBonus = referralBonus ;
    }

    public String getRecommendTime() {
        return recommendTime;
    }

    public void setRecommendTime(String recommendTime) {
        this.recommendTime = recommendTime;
    }

    public String getGrantDate() {
        return grantDate;
    }

    public void setGrantDate(String grantDate) {
        this.grantDate = grantDate;
    }
}