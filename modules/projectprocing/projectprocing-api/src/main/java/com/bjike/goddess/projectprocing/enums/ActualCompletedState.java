package com.bjike.goddess.projectprocing.enums;

/**
 * 类型
 *
 * @Author: [lijuntao]
 * @Date: [17-3-9 下午4:10]
 * @Package:[ com.bjike.goddess.projectprocing.enums ]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum ActualCompletedState {
    /**
     * 完工
     */
    COMPLETION(0),
    /**
     * 未完工
     */
    UNFINISHED(1);

    private int value;

    ActualCompletedState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
