package com.bjike.goddess.organize.enums;

/**
 * @Author: [dengjunren]
 * @Date: [2017-04-26 18:45]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum ArrangementType {
    /**
     * 决策层
     */
    DM(0),
    /**
     * 管理层
     */
    MG(1),
    /**
     * 执行层
     */
    ET(2);


    private Integer value;

    public Integer getValue() {
        return value;
    }

    ArrangementType(Integer value) {
        this.value = value;
    }
}
