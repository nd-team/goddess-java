package com.bjike.goddess.dbs.jpa.exception;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [service层自定义异常]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public class SerException extends Throwable {

    public SerException() {
        super();
    }

    public SerException(String message) {
        super(message);
    }
}
