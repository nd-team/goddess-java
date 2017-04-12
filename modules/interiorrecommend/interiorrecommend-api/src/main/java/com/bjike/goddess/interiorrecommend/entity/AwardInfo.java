package com.bjike.goddess.interiorrecommend.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
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
     * 推荐信息id
     */
    @Column(name = "infoId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '推荐信息id'")
    private String infoId;

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


    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
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