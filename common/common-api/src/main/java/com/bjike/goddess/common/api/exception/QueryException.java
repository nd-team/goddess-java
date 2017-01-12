package com.bjike.goddess.common.api.exception;

/**
 * 查询异常，运行时异常
 */
public class QueryException extends RuntimeException {

    public QueryException(String msg) {
        super(msg);
    }

}
