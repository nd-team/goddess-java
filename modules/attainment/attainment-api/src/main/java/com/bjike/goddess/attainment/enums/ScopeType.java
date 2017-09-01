package com.bjike.goddess.attainment.enums;

/**
 * 范围类型
 *
 * @Author: [dengjunren]
 * @Date: [2017-04-06 14:22]
 * @Description: [ 范围类型 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum ScopeType {
    /**
     * 公司
     */
    COMPANY(0),

    /**
     * 部门
     */
    DEPARTMENT(1),
    /**
     * 职位
     */
    POSITION(2),
    /**
     * 个人
     */
    PERSONAL(3);


    private int value;

    ScopeType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
