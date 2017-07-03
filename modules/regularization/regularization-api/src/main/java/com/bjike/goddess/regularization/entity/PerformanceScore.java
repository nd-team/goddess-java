package com.bjike.goddess.regularization.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 工作表现评分
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-15 04:55 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "regularization_performancescore")
public class PerformanceScore extends BaseEntity {

    /**
     * 岗位层级
     */
    @Column(name = "postHierarchy", nullable = false, unique = true, columnDefinition = "VARCHAR(255) COMMENT '岗位层级'")
    private String postHierarchy;

    /**
     * 评分内容
     */
    @Column(name = "scoreContent", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '评分内容'")
    private String scoreContent;


    public String getPostHierarchy() {
        return postHierarchy;
    }

    public void setPostHierarchy(String postHierarchy) {
        this.postHierarchy = postHierarchy;
    }

    public String getScoreContent() {
        return scoreContent;
    }

    public void setScoreContent(String scoreContent) {
        this.scoreContent = scoreContent;
    }
}