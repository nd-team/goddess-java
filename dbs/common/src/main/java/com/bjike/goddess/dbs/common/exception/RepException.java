package com.bjike.goddess.dbs.common.exception;


import com.bjike.goddess.dbs.common.enums.RepExceptionType;


public class RepException extends RuntimeException {
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
