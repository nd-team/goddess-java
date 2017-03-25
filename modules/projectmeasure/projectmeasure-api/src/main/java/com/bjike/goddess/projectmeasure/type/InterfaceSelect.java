package com.bjike.goddess.projectmeasure.type;

/**
 * 界面选择
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-23 11:27]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum InterfaceSelect {

    /**
     * 施工
     */
    CONSTRUCTION(0),
    /**
     * 设计
     */
    DESIGN(1),
    /**
     * 督导
     */
    SUPERVISE(2),
    /**
     * 方案提供
     */
    SOLUTION_PROVIDER(3),
    /**
     * 监理
     */
    CONTROL(4);

    private int value;

    InterfaceSelect(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
