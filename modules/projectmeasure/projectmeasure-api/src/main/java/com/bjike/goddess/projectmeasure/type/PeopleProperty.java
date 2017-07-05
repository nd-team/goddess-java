package com.bjike.goddess.projectmeasure.type;

/**
 * 人员性质
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-23 11:58]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum PeopleProperty {

    /**
     * 内部
     */
    INTERIOR(0),
    /**
     * 租赁
     */
    LEASE(1);

    private int value;

    PeopleProperty(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
