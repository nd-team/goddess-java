package com.bjike.goddess.dispatchcar.enums;

/**
 * 导航栏类型
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-03-20 19:57]
 * @Description: [导航栏类型]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum GuideAddrStatus {

    /**
     * 周汇总
     */
    WEEK(0),
    /**
     * 月汇总
     */
    MONTH(1),
    /**
     * 地区汇总
     */
    AREACOLLECT(2),
    /**
     * 项目组汇总
     */
    GROUPCOLLECT(3),
    /**
     * 司机汇总
     */
    DRIVERCOLLECT(4),
    /**
     * 地区分析
     */
    AREAANALYZE(5),
    /**
     * 项目组分析
     */
    groupAnalyze(6),
    /**
     * 司机分析
     */
    DRIVERANALYZE(7),
    /**
     * 查询汇总详情
     */
    DETAIL(8);


    private int code;

    GuideAddrStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
