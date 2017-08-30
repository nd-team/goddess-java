package com.bjike.goddess.feedback.enums;

/**
 * Created by ike on 17-8-3.
 */
public enum IdentityChoice {
    /**
     * 非责任相关人意见
     */
    RESPONSIBLE(0),
    /**
     * 一线项目组意见提出人
     */
    FIRST(1),
    /**
     * 规划模块意见提出人
     */
    PLAN(2),
    /**
     * 综合素养意见提出人
     */
    LITERACY(3),

    /**
     * 商务市场部意见提出人
     */
    BUSINESS(4),
    /**
     * 资金意见提出人
     */
    MONEY(5),
    /**
     * 账务意见提出人
     */
    ACCOUNT(6),
    /**
     * 预算意见提出人
     */
    BUDGET(7),
    /**
     * 研发部意见提出人
     */
    DIVISION(8),
    /**
     * 总经办（公司宏观视角）意见提出人
     */
    GENERAL(9),;

    private int code;

    IdentityChoice(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
