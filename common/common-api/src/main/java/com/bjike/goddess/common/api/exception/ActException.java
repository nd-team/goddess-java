package com.bjike.goddess.common.api.exception;


/**
 * 请求检查异常
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ActException extends Exception {

    private static final long serialVersionUID = -4245853247366157120L;

    public ActException() {
        super();
    }

    public ActException(String message) {
        super(message);
    }
}
