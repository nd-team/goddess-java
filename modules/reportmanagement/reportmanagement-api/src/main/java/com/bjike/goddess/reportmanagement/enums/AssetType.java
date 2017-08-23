package com.bjike.goddess.reportmanagement.enums;

/**
 * 资产类型
 */
public enum AssetType {

    /**
     * 流动资产
     */
    AFLOW(0),
    /**
     * 长期资产
     */
    BLONG(1),
    /**
     * 固定资产
     */
    CFIX(2),
    /**
     * 无形资产及其他资产
     */
    DINVISIBLE(3),
    /**
     * 递延税款
     */
    ETAX(4);
    private int code;

    AssetType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
