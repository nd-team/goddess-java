package com.bjike.goddess.taskallotment.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 饼状图汇总时间类型
 * @Author: [chenjunhao]
 * @Date: [2017-11-04 11:16]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum  PersonCountType {
    /**
     * 日
     */
    DAY(0),
    /**
     * 周
     */
    WEEK(1),
    /**
     * 季度
     */
    SEASON(2),
    /**
     * 月
     */
    MONTH(3),
    /**
     * 年
     */
    YEAR(4);

    private int code;

    PersonCountType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
