package com.bjike.goddess.projectprocing.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 类型
 *
 * @Author: [lijuntao]
 * @Date: [17-3-9 下午4:10]
 * @Package:[ com.bjike.goddess.projectprocing.enums ]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum SettlementPlan {
    /**
     * 未完工无法结算
     */
    @ExcelValue(name = "未完工无法结算")
    UNFINISHEDBUSSCONNOTSETTLED(0),
    /**
     * 可制作申请结算
     */
    @ExcelValue(name = "可制作申请结算")
    APPLICTATIONSETTCANMADE(1),
    /**
     * 未到货无法结算
     */
    @ExcelValue(name = "未到货无法结算")
    UNDELIVEREDCONNOTSETTLED(2),
    /**
     * 已提交结算
     */
    @ExcelValue(name = "已提交结算")
    SETTLEDSETTLEMENT(3),
    /**
     * 资料已提交待会审
     */
    @ExcelValue(name = "资料已提交待会审")
    INFORMATIONSUBMITREVIEW(4),
    /**
     * 提交软调评分资料
     */
    @ExcelValue(name = "提交软调评分资料")
    SUBMITSOFTGRADING(5),
    /**
     * 等待回款
     */
    @ExcelValue(name = "等待回款")
    WAITINGRECEIVABLE(6);

    private int value;

    SettlementPlan(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
