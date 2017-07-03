package com.bjike.goddess.interiorrecommend.vo;

/**
 * 推荐内容表现层对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-09 03:28 ]
 * @Description: [ 推荐内容表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RecommendAssessDetailVO {

    /**
     * id
     */
    private String id;
    /**
     * 考核内容
     */
    private String content;

    /**
     * 内容要求
     */
    private String require;

    /**
     * 推荐要求id
     */
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}