package com.bjike.goddess.dbs.common.enums;


/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [jpa数据类型映射]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public enum DataType {
    STRING(0),
    INT(1),
    FLOAT(2),
    CHAR(3),
    DOUBLE(4),
    BOOLEAN(5),
    LONG(6),
    LOCALDATE(7),
    LOCALTIME(8),
    LOCALDATETIME(9),
    ENUM(10);
    private int code;

    DataType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
