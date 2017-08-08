package com.bjike.goddess.interiorrecommend.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * 推荐奖励信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-10 04:07 ]
 * @Description: [ 推荐奖励信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "interiorrecommend_awardinfo")
public class AwardInfo extends BaseEntity {

    /**
     * 推荐信息
     */
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "recommendInfo_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '推荐信息'")
    private RecommendInfo recommendInfo;

    /**
     * 奖励时间
     */
    @Column(name = "awardTime", columnDefinition = "DATETIME   COMMENT '奖励时间'")
    private LocalDateTime awardTime;

    /**
     * 是否获得奖励
     */
    @Column(name = "is_getAward", columnDefinition = "TINYINT(1)   COMMENT '是否获得奖励'")
    private Boolean getAward;


    public RecommendInfo getRecommendInfo() {
        return recommendInfo;
    }

    public void setRecommendInfo(RecommendInfo recommendInfo) {
        this.recommendInfo = recommendInfo;
    }

    public LocalDateTime getAwardTime() {
        return awardTime;
    }

    public void setAwardTime(LocalDateTime awardTime) {
        this.awardTime = awardTime;
    }

    public Boolean getGetAward() {
        return getAward;
    }

    public void setGetAward(Boolean getAward) {
        this.getAward = getAward;
    }
}