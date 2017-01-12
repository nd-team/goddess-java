package com.bjike.goddess.common.api.type;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [jpa dao查询错误类型]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum RepExceptionType {
    UNDEFINE, //未定义错误
    NOT_FIND_FIELD, //类属性不存在
    ERROR_ARGUMENTS, //参数数量错误
    ERROR_PARSE_DATE,//时间类型参数转换错误
    ERROR_NUMBER_FORMAT,//字符串转换整形在错误
}
