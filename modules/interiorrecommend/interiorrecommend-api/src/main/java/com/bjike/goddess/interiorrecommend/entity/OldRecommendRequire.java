package com.bjike.goddess.interiorrecommend.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.interiorrecommend.enums.AssessWay;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


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
public class OldRecommendRequire extends BaseEntity {

    /**
     * 推荐方案
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "recommendScheme_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '推荐方案'")
    private RecommendScheme recommendScheme;

    /**
     *
     * 推荐类型
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH,CascadeType.REMOVE})
    @JoinColumn(name = "recommendType_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '推荐类型'")
    private OldRecommendType recommendType;

    /**
     * 推荐考核内容
     */
    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, fetch = FetchType.EAGER, mappedBy = "recommendRequire")
    private Set<OldRecommendAssessDetail> detailSet = new HashSet<OldRecommendAssessDetail>();

    /**
     * 推荐时长
     */
    @Column(name = "recommendTime", nullable = false, columnDefinition = "INT(11)   COMMENT '推荐时长'")
    private Integer recommendTime;

    /**
     * 指标来源
     */
    @Column(name = "indicatorResource", columnDefinition = "VARCHAR(255)   COMMENT '指标来源'")
    private String indicatorResource;

    /**
     * 推荐途径
     */
    @Column(name = "recommendWay", columnDefinition = "VARCHAR(255)   COMMENT '推荐途径'")
    private String recommendWay;

    /**
     * 考核方式
     */
    @Column(name = "assessWay", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '考核方式'")
    private AssessWay assessWay;

    /**
     * 推荐发起人
     */
    @Column(name = "recommendSponsor", columnDefinition = "VARCHAR(255)   COMMENT '推荐发起人'")
    private String recommendSponsor;

    public Set<OldRecommendAssessDetail> getDetailSet() {
        return detailSet;
    }

    public void setDetailSet(Set<OldRecommendAssessDetail> detailSet) {
        this.detailSet = detailSet;
    }

    public Integer getRecommendTime() {
        return recommendTime;
    }

    public void setRecommendTime(Integer recommendTime) {
        this.recommendTime = recommendTime;
    }

    public RecommendScheme getRecommendScheme() {
        return recommendScheme;
    }

    public void setRecommendScheme(RecommendScheme recommendScheme) {
        this.recommendScheme = recommendScheme;
    }

    public OldRecommendType getRecommendType() {
        return recommendType;
    }

    public void setRecommendType(OldRecommendType recommendType) {
        this.recommendType = recommendType;
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