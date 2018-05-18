package com.bjike.goddess.interiorrecommend.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.interiorrecommend.enums.PathWay;
import com.bjike.goddess.interiorrecommend.enums.RecommendType;

import javax.persistence.*;
import java.time.LocalDate;


/**
* 内部推荐方案实施
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-12 11:29 ]
* @Description:	[ 内部推荐方案实施 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Entity
@Table(name = "interiorrecommend_schemeimplement")
public class SchemeImplement extends BaseEntity {

    /**
     * 方案名称
     */
    @Column(name = "type",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '方案名称'"  )
    private String  type;

    /**
     * 推荐发起人
     */
    @Column(name = "initiator",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '推荐发起人'"  )
    private String  initiator;

    /**
     * 推荐的途径
     */
    @Column(name = "pathway",nullable = false,columnDefinition = "TINYINT(4)   COMMENT '推荐的途径'"  )
    private PathWay pathway;

    /**
     * 推荐时间
     */
    @Column(name = "recommendTime",nullable = false,columnDefinition = "DATE   COMMENT '推荐时间'"  )
    private LocalDate recommendTime;

    /**
     * 推荐类型
     */
    @Column(name = "",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '推荐类型'"  )
    private RecommendType recommendType;

    /**
     * 推荐岗位
     */
    @Column(name = "recommendPosition",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '推荐岗位'"  )
    private String  recommendPosition;

    /**
     * 被推荐人
     */
    @Column(name = "beRecommender",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '被推荐人'"  )
    private String  beRecommender;

    /**
     * 是否入职
     */
    @Column(name = "is_isEntry",nullable = false,columnDefinition = "TINYINT(1)   COMMENT '是否入职'" )
    private Boolean  isEntry;

    /**
     * 是否转正
     */
    @Column(name = "is_isRegular",nullable = false,columnDefinition = "TINYINT(1)  COMMENT '是否转正'"   )
    private Boolean  isRegular;

    /**
     * 是否获得推荐奖
     */
    @Column(name = "is_isAcquire",nullable = false,columnDefinition = "TINYINT(1) COMMENT '是否获得推荐奖'"  )
    private Boolean  isAcquire;

    /**
     * 推荐奖金额
     */
    @Column(name = "",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '推荐奖金额'"  )
    private Integer  referralBonus;

    /**
     * 奖励金额发放时间
     */
    @Column(name = "grantDate",columnDefinition = "DATE   COMMENT '奖励金额发放时间'"  )
    private LocalDate  grantDate;



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

    public LocalDate getRecommendTime () {
        return recommendTime;
    }
    public void setRecommendTime (LocalDate recommendTime ) {
        this.recommendTime = recommendTime ;
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
    public LocalDate getGrantDate () {
        return grantDate;
    }
    public void setGrantDate (LocalDate grantDate ) {
        this.grantDate = grantDate ;
    }
 }