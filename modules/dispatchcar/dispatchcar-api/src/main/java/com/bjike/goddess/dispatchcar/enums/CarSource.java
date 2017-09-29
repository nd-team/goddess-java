package com.bjike.goddess.dispatchcar.enums;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-23 08:39]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum CarSource {
    /**
     * 人工录入
     */
    MANUALENTRY(0),


    /**
     * 手机录入
     */
    PHONEENTRY(1);

    private int code;

    CarSource(int code){this.code = code;}

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
