package com.bjike.goddess.common.api.exception;

/**
 * 业务检测异常
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SerException extends Exception {

    private static final long serialVersionUID = 6229315095728639413L;

    public SerException() {
        super();
    }

    public SerException(String message) {
        super(message);
    }
}
