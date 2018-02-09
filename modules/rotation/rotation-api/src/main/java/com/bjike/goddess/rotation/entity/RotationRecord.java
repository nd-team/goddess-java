package com.bjike.goddess.rotation.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;


/**
 * 岗位轮换记录
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2018-01-08 09:29 ]
 * @Description: [ 岗位轮换记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "rotation_rotationrecord")
public class RotationRecord extends BaseEntity {

    /**
     * 轮岗方式
     */
    @Column(name = "rotationType", nullable = false, columnDefinition = "VARCHAR(8)   COMMENT '轮岗方式'")
    private String rotationType;

    /**
     * 自荐表id
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional=true)
    @JoinColumn(name = "coverRotationId", columnDefinition = "VARCHAR(36)   COMMENT '申请轮换等级'")
    @NotFound(action= NotFoundAction.IGNORE)
    private CoverRotation coverRotation;

    /**
     * 推荐表id
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional=true)
    @JoinColumn(name = "recommendRotationId", columnDefinition = "VARCHAR(36)   COMMENT '申请轮换等级'")
    @NotFound(action=NotFoundAction.IGNORE)
    private RecommendRotation recommendRotation;

    public String getRotationType() {
        return rotationType;
    }

    public void setRotationType(String rotationType) {
        this.rotationType = rotationType;
    }

    public CoverRotation getCoverRotation() {
        return coverRotation;
    }

    public void setCoverRotation(CoverRotation coverRotation) {
        this.coverRotation = coverRotation;
    }

    public RecommendRotation getRecommendRotation() {
        return recommendRotation;
    }

    public void setRecommendRotation(RecommendRotation recommendRotation) {
        this.recommendRotation = recommendRotation;
    }
}