package com.bjike.goddess.task.enums;

/**汇总类型
 * @Author: [liguiqin]
 * @Date: [2017-09-25 16:01]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum SummaryType {
    /**
     * 个人汇总
     */
    PERSON(0),
    /**
     * 部门汇总
     */
    DEPT(1),
    /**
     * 整体汇总
     */
    ALL(2);


    SummaryType(int code) {
        this.code = code;
    }

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

