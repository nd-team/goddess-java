package com.bjike.goddess.dimission.enums;

/**
 * 员工状态
 *
 * @Author: [dengjunren]
 * @Date: [2017-04-17 14:54]
 * @Description: [ 员工状态 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum EmployeeStatus {
    /**
     * 试用期员工
     */
    TRIAL(0),
    /**
     * 正式员工
     */
    FORMAL(1);

    private int value;

    public int getValue() {
        return value;
    }

    EmployeeStatus(int value) {
        this.value = value;
    }
}
