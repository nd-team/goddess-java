package com.bjike.goddess.contractcommunicat.enums;

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
    COOPERATE(0),
    /**
     * 项目跟进
     */
    TRAIL(1),
    /**
     * 项目丢弃
     */
    ABANDON(2);

    private int code;

    CommunicateResult(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
