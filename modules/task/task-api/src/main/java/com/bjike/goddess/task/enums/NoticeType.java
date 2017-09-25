package com.bjike.goddess.task.enums;

/**
 * 通知类型
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-25 16:51]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum NoticeType {
    /**
     * 个人
     */
    PERSON(0),
    /**
     * 部门
     */
    DEPT(1),
    /**
     * 所有
     */
    ALL(2);

    private int code;

    NoticeType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
