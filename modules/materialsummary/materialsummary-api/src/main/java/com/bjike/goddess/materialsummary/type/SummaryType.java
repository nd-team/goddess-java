package com.bjike.goddess.materialsummary.type;

/**
 * 汇总类型
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-03-16 19:16]
 * @Description: [汇总类型]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum SummaryType {

    /**
     * 物质分类购买情况汇总
     */
    MATCLASSIFBUY(0),
    /**
     * 部门地区购买情况汇总
     */
    DEPARTAREABUY(1),
    /**
     * 个人物质购买情况汇总
     */
    PERSONMATBUY(2),
    /**
     * 入库来源汇总
     */
    STOCKSOURCE(3),
    /**
     * 地区入库情况汇总
     */
    AREASTOCK(4),
    /**
     * 地区物质盘点情况汇总
     */
    AREAMATCHECK(5),
    /**
     * 部门物质盘点情况汇总
     */
    DEPARMATCHECK(6),
    /**
     * 维修状态分类情况汇总
     */
    MAINTENSTATUS(7),
    /**
     * 保修状态分类情况汇总
     */
    WARRANTYSTATUS(8);

    private int code;

    SummaryType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}


