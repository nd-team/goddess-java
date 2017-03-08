package com.bjike.goddess.common.api.type;


/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [jpa数据类型映射]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum DataType {
    STRING(0), //字符串类型
    INT(1), //整型
    FLOAT(2), //浮点型
    CHAR(3), //单字符型
    DOUBLE(4),//双精度浮点型
    BOOLEAN(5),//逻辑型
    LONG(6),//长整形
    LOCALDATE(7),//日期
    LOCALTIME(8),//时间
    LOCALDATETIME(9), //时间类型
    ENUM(10);//枚舉
    private int code;

    DataType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
