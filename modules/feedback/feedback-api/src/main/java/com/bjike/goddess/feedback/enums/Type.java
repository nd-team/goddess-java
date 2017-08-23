package com.bjike.goddess.feedback.enums;

/**
 * Created by ike on 17-8-5.
 */
public enum Type {
    /**
     * 协助函
     */
    ASSIST(0),
    /**
     * 问题反馈函
     */
    FEEDBACK(1),
    /**
     * 问题受理后的回复函
     */
    ACCEPT(2),;

    private int code;

    Type(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
