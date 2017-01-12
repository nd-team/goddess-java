package com.bjike.goddess.common.api.exception;

/**
 * 业务检测异常
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
