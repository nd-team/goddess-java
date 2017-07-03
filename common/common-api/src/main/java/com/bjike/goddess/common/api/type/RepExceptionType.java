package com.bjike.goddess.common.api.type;

/**
 * jpa dao查询错误类型
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum RepExceptionType {
    /**
     * 未定义错误
     */
    UNDEFINE,
    /**
     * 类属性不存在
     */
    NOT_FIND_FIELD,
    /**
     * 参数数量错误
     */
    ERROR_ARGUMENTS,
    /**
     * 时间类型参数转换错误
     */
    ERROR_PARSE_DATE,
    /**
     * 字符串转换整形在错误
     */
    ERROR_NUMBER_FORMAT,
}
