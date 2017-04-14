package com.bjike.goddess.interiorrecommend.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.interiorrecommend.enums.AssessWay;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 推荐要求
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-09 03:29 ]
 * @Description: [ 推荐要求 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "interiorrecommend_recommendrequire")
public class RecommendRequire extends BaseEntity {

    /**
     * 推荐方案id
     */
    @Column(name = "recommendSchemeId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '推荐方案id'")
    private String recommendSchemeId;

    /**
     * 推荐时长
     */
    @Column(name = "recommendTime", nullable = false, columnDefinition = "INT(11)   COMMENT '推荐时长'")
    private Integer recommendTime;

    /**
     * 推荐类型id
     */
    @Column(name = "recommendTypeId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '推荐类型id'")
    private String recommendTypeId;

    /**
     * 指标来源
     */
    @Column(name = "indicatorResource", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '指标来源'")
    private String indicatorResource;

    /**
     * 推荐途径
     */
    @Column(name = "recommendWay", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '推荐途径'")
    private String recommendWay;

    /**
     * 考核方式
     */
    @Column(name = "assessWay", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '考核方式'")
    private AssessWay assessWay;

    /**
     * 推荐发起人
     */
    @Column(name = "recommendSponsor", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '推荐发起人'")
    private String recommendSponsor;


    public String getRecommendSchemeId() {
        return recommendSchemeId;
    }

    public void setRecommendSchemeId(String recommendSchemeId) {
        this.recommendSchemeId = recommendSchemeId;
    }

    public Integer getRecommendTime() {
        return recommendTime;
    }

    public void setRecommendTime(Integer recommendTime) {
        this.recommendTime = recommendTime;
    }

    public String getRecommendTypeId() {
        return recommendTypeId;
    }

    public void setRecommendTypeId(String recommendTypeId) {
        this.recommendTypeId = recommendTypeId;
    }

    public String getIndicatorResource() {
        return indicatorResource;
    }

    public void setIndicatorResource(String indicatorResource) {
        this.indicatorResource = indicatorResource;
    }

    public String getRecommendWay() {
        return recommendWay;
    }

    public void setRecommendWay(String recommendWay) {
        this.recommendWay = recommendWay;
    }

    public AssessWay getAssessWay() {
        return assessWay;
    }

    public void setAssessWay(AssessWay assessWay) {
        this.assessWay = assessWay;
    }

    public String getRecommendSponsor() {
        return recommendSponsor;
    }

    public void setRecommendSponsor(String recommendSponsor) {
        this.recommendSponsor = recommendSponsor;
    }
}