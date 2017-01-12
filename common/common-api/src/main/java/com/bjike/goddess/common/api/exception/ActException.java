package com.bjike.goddess.common.api.exception;


/**
 * 请求检查异常
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
