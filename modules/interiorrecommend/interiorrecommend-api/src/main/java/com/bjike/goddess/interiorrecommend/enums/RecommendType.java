package com.bjike.goddess.interiorrecommend.enums;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-12 09:51]
 * @Description: [推荐类型]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum RecommendType {
    /**
     * 人才推荐
     */
    TALENNTS(0),

    /**
     * 业务信息推荐
     */
    SERVICE(1),

    /**
     * 其他
     */
    OTHER(2);

    private int code;

    RecommendType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
