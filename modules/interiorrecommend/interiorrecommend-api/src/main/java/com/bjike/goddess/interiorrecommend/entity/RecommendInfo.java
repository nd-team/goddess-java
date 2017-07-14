package com.bjike.goddess.interiorrecommend.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.util.Set;


/**
 * 推荐信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-10 02:54 ]
 * @Description: [ 推荐信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "interiorrecommend_recommendinfo")
public class RecommendInfo extends BaseEntity {


    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "recommendRequire_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '推荐要求'")
    private RecommendRequire recommendRequire;

    /**
     * 推荐考核内容
     */
    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, fetch = FetchType.EAGER, mappedBy = "recommendInfo")
    private Set<RecommendContent> contentSet;

    /**
     * 推荐人
     */
    @Column(name = "recommendUser", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '推荐人'")
    private String recommendUser;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    /**
     * 原因
     */
    @Column(name = "reason", columnDefinition = "VARCHAR(255)   COMMENT '原因'")
    private String reason;

    /**
     * 是否采纳
     */
    @Column(name = "is_accept", columnDefinition = "TINYINT(1)   COMMENT '是否采纳'")
    private Boolean accept;

    /**
     * 是否符合奖励要求
     */
    @Column(name = "is_conform", columnDefinition = "TINYINT(1)   COMMENT '是否符合奖励要求'")
    private Boolean conform;

    public Set<RecommendContent> getContentSet() {
        return contentSet;
    }

    public void setContentSet(Set<RecommendContent> contentSet) {
        this.contentSet = contentSet;
    }

    public RecommendRequire getRecommendRequire() {
        return recommendRequire;
    }

    public void setRecommendRequire(RecommendRequire recommendRequire) {
        this.recommendRequire = recommendRequire;
    }

    public String getRecommendUser() {
        return recommendUser;
    }

    public void setRecommendUser(String recommendUser) {
        this.recommendUser = recommendUser;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Boolean getAccept() {
        return accept;
    }

    public void setAccept(Boolean accept) {
        this.accept = accept;
    }

    public Boolean getConform() {
        return conform;
    }

    public void setConform(Boolean conform) {
        this.conform = conform;
    }
}