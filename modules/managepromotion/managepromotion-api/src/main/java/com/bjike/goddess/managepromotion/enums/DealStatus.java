package com.bjike.goddess.managepromotion.enums;

/**
 * 处理状态
 *
 * @Author: [xiazhili]
 * @Date: [2017-05-23 08:50]
 * @Description: [处理状态]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum DealStatus {

    /**
     * 正在受理
     */
    RECEIVING(0),
    /**
     * 未处理
     */
    UNTREATED(1),
    /**
     * 已完成处理
     */
    COMPLETEDPROCESSING(2),
    ;

    private int code;

    DealStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
