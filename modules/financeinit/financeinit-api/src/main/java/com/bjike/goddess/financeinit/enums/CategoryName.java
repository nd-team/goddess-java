package com.bjike.goddess.financeinit.enums;

import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * @Author: [tanghaixiang]
 * @Date: [2017-03-29 16:50]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum CategoryName {

    /**
     * 资产类
     */
    @ExcelValue(name="资产类")
    ASSETS(0),
    /**
     * 负债类
     */
    @ExcelHeader(name ="负债类")
    LIABILITIES(1),
    /**
     * 共同类
     */
    @ExcelHeader(name ="共同类")
    COMMON(3),
    /**
     * 权益类
     */
    @ExcelHeader(name ="权益类")
    RIGHTSINTERESTS(4),
    /**
     * 成本类
     */
    @ExcelHeader(name ="成本类")
    COST(5),
    /**
     * 损益类
     */
    @ExcelHeader(name ="损益类")
    PROFITLOSS(6)
    ;

    private int code;

    CategoryName(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }


}
