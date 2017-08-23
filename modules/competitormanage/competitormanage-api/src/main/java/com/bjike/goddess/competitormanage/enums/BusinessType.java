package com.bjike.goddess.competitormanage.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 竞争对手业务类型
 *
 * @Author: [Jason]
 * @Date: [17-3-22 上午10:22]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum BusinessType {
    /**
     * 通信类
     */
    @ExcelValue(name = "通信类")
    COMMUNICATE(0),
    /**
     * 软件类
     */
    @ExcelValue(name = "软件类")
    SOFTWARE(1),
    /**
     * 营销策划类
     */
    @ExcelValue(name = "营销策划类")
    MARKETINGPLAN(2),
    /**
     * 智能化类
     */
    @ExcelValue(name = "智能化类")
    INTELLIGENTIZE(3),
    /**
     * 电子商务类
     */
    @ExcelValue(name = "电子商务类")
    ELECTRONICCOMMERCE(4),
    /**
     * 房地产类
     */
    @ExcelValue(name = "房地产类")
    REALTY(5),
    /**
     * 理财类
     */
    @ExcelValue(name = "理财类")
    FINANCIAL(6),
    /**
     * 食品类
     */
    @ExcelValue(name = "食品类")
    FOOD(7);

    private int code;

    BusinessType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
