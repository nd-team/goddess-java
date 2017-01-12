package com.bjike.goddess.common.api.exception;


import com.bjike.goddess.common.api.type.RepExceptionType;

/**
 * dao检查异常
 */
public class RepException extends Exception {

    private static final long serialVersionUID = 71512318732946788L;

    private RepExceptionType type = RepExceptionType.UNDEFINE;

    private RepException repException;

    public RepException(RepExceptionType repExceptionType, String msg) {
        super(msg);
        this.type = repExceptionType;
    }

    public RepException getRepException() {
        return repException;
    }

    public void setRepException(RepException repException) {
        this.repException = repException;
    }

    public RepExceptionType getType() {
        return type;
    }

    public void setType(RepExceptionType type) {
        this.type = type;
    }

}
