package com.bjike.goddess.attainment.enums;

/**
 * 调研状态
 *
 * @Author: [dengjunren]
 * @Date: [2017-04-06 14:06]
 * @Description: [ 调研状态 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum SurveyStatus {
    /**
     * 调研中
     */
    UNDERWAY(0)
    /**
     * 调研完成
     */
    , FINISH(1)
    /**
     * 调研失败
     */
    , FAIL(2)
    /**
     * 调研过期
     */
    , EXPIRE(3)
    /**
     * 调研超时
     */
    , OVERTIME(4)
    /**
     * 未调研
     */
    , NONE(5);

    private int value;

    SurveyStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
