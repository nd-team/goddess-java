package com.bjike.goddess.progressmanage.enums;

/**
 * 表头类型
 * <p>
 * Created by ike on 17-6-9.
 */
public enum HeadType {
    /**
     * 文本
     */
    TEXT(0),
    /**
     * 整数
     */
    INT(1),
    /**
     * 非整数
     */
    UNINT(2),
    /**
     * 日期
     */
    DATE(3),
    /**
     * 时间日期
     */
    DATETIME(4),
    /**
     * 逻辑类型
     */
    LOGIC(4);;


    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    HeadType(int code) {
        this.code = code;
    }

}
