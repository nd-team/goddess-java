package com.bjike.goddess.contractcommunicat.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 项目结果
 *
 * @Author: [Jason]
 * @Date: [17-3-18 上午9:07]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum CommunicateResult {

    /**
     * 项目合作
     */
    @ExcelValue(name = "项目合作")
    COOPERATE(0),

    /**
     * 项目跟进
     */
    @ExcelValue(name = "项目跟进")
    TRAIL(1),

    /**
     * 项目丢弃
     */
    @ExcelValue(name = "项目跟进")
    ABANDON(2);

    private int code;

    CommunicateResult(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static CommunicateResult getCommunicateResult(int code) {
        for (CommunicateResult result : CommunicateResult.values()) {
            if (code == result.getCode()) {
                return result;
            }
        }
        return null;
    }

}
