package com.bjike.goddess.projectmeasure.type;

/**
 * 项目类别
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-23 11:18]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum ProjectCategory {
    /**
     * 单项目单个界面
     */
    SINGLE_SINGLE(0),
    /**
     * 单项目多个界面
     */
    SINGLE_MULTIPLE(1),
    /**
     * 多项目单个界面
     */
    MULTIPLE_SINGLE(2),
    /**
     * 多项目多个界面
     */
    MULTIPLE_MULTIPLE(3);

    private int value;

    ProjectCategory(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
