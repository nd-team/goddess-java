package com.bjike.goddess.recruit.type;

/**
 * 模板状态
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-10 17:59]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum TemplateStatus {
    /**
     * 使用中
     */
    USING(0),
    /**
     * 未使用
     */
    UNUSE(1);

    private int value;

    TemplateStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
