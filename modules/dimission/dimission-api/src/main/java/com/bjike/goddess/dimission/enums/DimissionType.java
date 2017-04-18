package com.bjike.goddess.dimission.enums;

/**
 * 离职类型
 *
 * @Author: [dengjunren]
 * @Date: [2017-04-17 14:50]
 * @Description: [ 离职类型 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum DimissionType {
    /**
     * 正常离职
     */
    NORMAL(0),
    /**
     * 提前离职
     */
    ADVANCE(1),
    /**
     * 自离
     */
    PRESUME(2),
    /**
     * 辞退
     */
    REFUSE(3);

    private int value;

    public int getValue() {
        return value;
    }

    public String getStringValue() {
        switch (value) {
            case 0:
                return "正常离职";
            case 1:
                return "提前离职";
            case 2:
                return "自离";
            default:
                return "辞退";
        }
    }

    DimissionType(int value) {
        this.value = value;
    }
}
