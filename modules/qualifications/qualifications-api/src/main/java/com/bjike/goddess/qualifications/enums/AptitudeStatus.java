package com.bjike.goddess.qualifications.enums;

/**
 * 资质状态
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-31 18:50]
 * @Description: [ 资质状态 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum AptitudeStatus {

    /**
     * 准备办理
     */
    READY(0)
    /**
     *办理中
     */
    , PROCESS(1)
    /**
     * 办理成功
     */
    , SUCCESS(2)
    /**
     * 已获取
     */
    , OBTAIN(3)
    /**
     * 办理失败
     */
    , FAIL(4);


    private int value;

    AptitudeStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
