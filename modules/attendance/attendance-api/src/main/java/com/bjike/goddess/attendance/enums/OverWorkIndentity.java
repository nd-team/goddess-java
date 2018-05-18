package com.bjike.goddess.attendance.enums;

/**
 * 手机端加班的身份
 * @Author: [chenjunhao]
 * @Date: [2017-10-07 17:31]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum OverWorkIndentity {
    /**
     * 项目经理
     */
    MANAGE(0),
    /**
     * 负责人
     */
    CHARGE(1),
    /**
     * 普通
     */
    NOMAL(2);
    private int code;

    OverWorkIndentity(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }


    
}
