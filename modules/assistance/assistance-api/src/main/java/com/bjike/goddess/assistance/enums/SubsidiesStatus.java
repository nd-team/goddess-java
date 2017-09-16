package com.bjike.goddess.assistance.enums;

/**
 * 补助状态
 *
 * @Author: [lijuntao]
 * @Date: [2017-03-20 19:57]
 * @Description: [补助状态]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum SubsidiesStatus {

    /**
     * 在补助
     */
    INSUBSIDIES(0),
    /**
     * 未补助
     */
    NOSUBSIDIES(1);

    private int code;

    SubsidiesStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
