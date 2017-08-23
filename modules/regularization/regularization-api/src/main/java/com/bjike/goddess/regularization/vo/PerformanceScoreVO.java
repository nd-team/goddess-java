package com.bjike.goddess.regularization.vo;

/**
 * 工作表现评分表现层对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-15 04:55 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PerformanceScoreVO {

    /**
     * id
     */
    private String id;
    /**
     * 岗位层级
     */
    private String postHierarchy;

    /**
     * 评分内容
     */
    private String scoreContent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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