package com.bjike.goddess.interiorrecommend.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 推荐内容
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-09 03:28 ]
 * @Description: [ 推荐内容 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "interiorrecommend_recommendassessdetail")
public class RecommendAssessDetail extends BaseEntity {

    /**
     * 考核内容
     */
    @Column(name = "content", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '考核内容'")
    private String content;

    /**
     * 内容要求
     */
    @Column(name = "content_require", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '内容要求'")
    private String require;

    /**
     * 推荐要求id
     */
    @Column(name = "requireId", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '推荐要求id'")
    private String requireId;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRequire() {
        return require;
    }

    public void setRequire(String require) {
        this.require = require;
    }

    public String getRequireId() {
        return requireId;
    }

    public void setRequireId(String requireId) {
        this.requireId = requireId;
    }
}