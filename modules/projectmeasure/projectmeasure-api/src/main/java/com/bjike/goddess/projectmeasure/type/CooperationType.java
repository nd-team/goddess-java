package com.bjike.goddess.projectmeasure.type;

/**
 * 合作方式
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-23 11:35]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum CooperationType {

    /**
     * 长期合作
     */
    LONG_TEAM_COOPERATION(0),
    /**
     * 事项合作
     */
    MATTER_COOPERATION(1),
    /**
     * 中介
     */
    INTERMEDIARY(2);

    private int value;

    CooperationType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
