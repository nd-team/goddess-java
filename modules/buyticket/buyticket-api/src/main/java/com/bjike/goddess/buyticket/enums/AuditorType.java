package com.bjike.goddess.buyticket.enums;

/**
 * 审核人类型枚举
 *
 * @Author: [xiazhili]
 * @Date: [17-4-25]
 * @Description: [审核人类型枚举]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess]
 */
public enum AuditorType {
    /**
     * 规划模块
     */
    PLANMODULE(0),
    /**
     * 福利模块
     */
    WELFAREMODULE(1),
    ;

    private int code;

    AuditorType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
