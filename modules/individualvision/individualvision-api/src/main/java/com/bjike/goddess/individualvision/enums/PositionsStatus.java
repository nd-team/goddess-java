package com.bjike.goddess.individualvision.enums;

/**
 * Created by ike on 17-6-2.
 */
public enum PositionsStatus {
    /**
     * 已转正
     */
    POSITIVE(0),
    /**
     * 管理层
     */
    MANAGEMENT(1),
    /**
     * 技术经理
     */
    TECHNICALMANAGER(2),
    ;
    private int code;

    PositionsStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
