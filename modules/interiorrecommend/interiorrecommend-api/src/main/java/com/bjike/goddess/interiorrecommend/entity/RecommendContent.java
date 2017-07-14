package com.bjike.goddess.interiorrecommend.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 推荐内容
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-10 02:51 ]
 * @Description: [ 推荐内容 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "interiorrecommend_recommendcontent")
public class RecommendContent extends BaseEntity {

    /**
     * 推荐信息
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "recommendInfo_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '推荐信息'")
    private RecommendInfo recommendInfo;

    /**
     * 推荐内容
     */
    @Column(name = "content", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '推荐内容'")
    private String content;

    /**
     * 内容明细
     */
    @Column(name = "detail", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '内容明细'")
    private String detail;


    public RecommendInfo getRecommendInfo() {
        return recommendInfo;
    }

    public void setRecommendInfo(RecommendInfo recommendInfo) {
        this.recommendInfo = recommendInfo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

}