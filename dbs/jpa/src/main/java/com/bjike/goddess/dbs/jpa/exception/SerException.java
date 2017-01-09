package com.bjike.goddess.dbs.jpa.exception;

import java.io.Serializable;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [service层自定义异常]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public class SerException extends Exception implements Serializable {

    public SerException() {
        super();
    }

    public SerException(String message) {
        super(message);
    }
}
