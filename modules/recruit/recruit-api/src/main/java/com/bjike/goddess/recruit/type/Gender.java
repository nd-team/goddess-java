package com.bjike.goddess.recruit.type;

/**
 * 性别
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-10 13:53]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum Gender {
    /**
     * 男
     */
    MALE(0),
    /**
     * 女
     */
    FEMALE(1);

    private int value;

    Gender(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
