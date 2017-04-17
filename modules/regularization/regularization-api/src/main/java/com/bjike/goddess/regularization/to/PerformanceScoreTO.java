package com.bjike.goddess.regularization.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 工作表现评分
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-15 04:55 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PerformanceScoreTO extends BaseTO {

    /**
     * 岗位层级
     */
    private String postHierarchy;

    /**
     * 评分内容
     */
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