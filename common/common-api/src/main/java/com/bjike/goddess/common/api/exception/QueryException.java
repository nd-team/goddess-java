package com.bjike.goddess.common.api.exception;


/**
 * 查询异常，运行时异常
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class QueryException extends RuntimeException {

    public QueryException(String msg) {
        super(msg);
    }

}
