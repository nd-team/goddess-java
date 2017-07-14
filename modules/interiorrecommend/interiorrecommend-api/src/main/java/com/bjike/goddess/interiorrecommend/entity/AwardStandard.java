package com.bjike.goddess.interiorrecommend.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 推荐奖励标准
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-10 11:39 ]
 * @Description: [ 推荐奖励标准 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "interiorrecommend_awardstandard")
public class AwardStandard extends BaseEntity {

    /**
     * 推荐要求
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "recommendRequire_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '推荐要求'")
    private RecommendRequire recommendRequire;

    /**
     * 奖励周期
     */
    @Column(name = "awardCycle", nullable = false, columnDefinition = "INT(11)   COMMENT '奖励周期'")
    private Integer awardCycle;

    /**
     * 奖励类型
     */
    @Column(name = "awardType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '奖励类型'")
    private String awardType;

    /**
     * 奖励内容
     */
    @Column(name = "awardContent", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '奖励内容'")
    private String awardContent;

    /**
     * 奖励数量
     */
    @Column(name = "awardAmount", nullable = false, columnDefinition = "INT(11)   COMMENT '奖励数量'")
    private Integer awardAmount;

    /**
     * 奖励发放方式
     */
    @Column(name = "awardSendWay", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '奖励发放方式'")
    private String awardSendWay;

    /**
     * 奖励预算
     */
    @Column(name = "awardCost", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '奖励预算'")
    private Double awardCost;

    /**
     * 奖励发放人
     */
    @Column(name = "awardGrantor", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '奖励发放人'")
    private String awardGrantor;


    public RecommendRequire getRecommendRequire() {
        return recommendRequire;
    }

    public void setRecommendRequire(RecommendRequire recommendRequire) {
        this.recommendRequire = recommendRequire;
    }

    public Integer getAwardCycle() {
        return awardCycle;
    }

    public void setAwardCycle(Integer awardCycle) {
        this.awardCycle = awardCycle;
    }

    public String getAwardType() {
        return awardType;
    }

    public void setAwardType(String awardType) {
        this.awardType = awardType;
    }

    public String getAwardContent() {
        return awardContent;
    }

    public void setAwardContent(String awardContent) {
        this.awardContent = awardContent;
    }

    public Integer getAwardAmount() {
        return awardAmount;
    }

    public void setAwardAmount(Integer awardAmount) {
        this.awardAmount = awardAmount;
    }

    public String getAwardSendWay() {
        return awardSendWay;
    }

    public void setAwardSendWay(String awardSendWay) {
        this.awardSendWay = awardSendWay;
    }

    public Double getAwardCost() {
        return awardCost;
    }

    public void setAwardCost(Double awardCost) {
        this.awardCost = awardCost;
    }

    public String getAwardGrantor() {
        return awardGrantor;
    }

    public void setAwardGrantor(String awardGrantor) {
        this.awardGrantor = awardGrantor;
    }
}