package com.bjike.goddess.common.api.type;


/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [jpa数据类型映射]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum DataType {
    /**
     * 字符串类型
     */
    STRING(0),
    /**
     * 整型
     */
    INT(1),
    /**
     * 浮点型
     */
    FLOAT(2),
    /**
     * 单字符型
     */
    CHAR(3),
    /**
     * 双精度浮点型
     */
    DOUBLE(4),
    /**
     * 逻辑型
     */
    BOOLEAN(5),
    /**
     * 长整形
     */
    LONG(6),
    /**
     * 日期
     */
    LOCALDATE(7),
    /**
     * 时间
     */
    LOCALTIME(8),
    /**
     * 时间类型
     */
    LOCALDATETIME(9),
    /**
     * 枚举
     */
    ENUM(10);
    private int code;

    DataType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
