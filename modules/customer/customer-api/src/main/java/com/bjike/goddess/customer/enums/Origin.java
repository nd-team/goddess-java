package com.bjike.goddess.customer.enums;


import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.common.utils.excel.ExcelValue;
import com.bjike.goddess.message.entity.Message;

/**
 * 客户维护状态枚举
 * @Author: [lijuntao]
 * @Date: [2017-03-15 16:48]
 * @Description: [客户维护状态枚举]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum Origin {

    /**
     * 客户介绍
     */
    @ExcelValue(name = "客户介绍")
    CUSTOMERINTROD(0),
    /**
     * 市场招待
     */
    @ExcelValue(name = "市场招待")
    MARKETFOR(1),
    /**
     * 商务洽谈
     */
    @ExcelValue(name = "商务洽谈")
    BUSSNEGOTIATION(2),
    /**
     * 招投标
     */
    @ExcelValue(name = "招投标")
    TENDERFOR(3),
    /**
     * 网站
     */
    @ExcelValue(name = "网站")
    WEBSITE(4),
    /**
     * 员工介绍
     */
    @ExcelValue(name = "员工介绍")
    STAFFINTRODUCED(5),
    /**
     * 其他来源
     */
    @ExcelValue(name = "其他来源")
    OTHERSOURCES(6);

    private int code;

    Origin(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
