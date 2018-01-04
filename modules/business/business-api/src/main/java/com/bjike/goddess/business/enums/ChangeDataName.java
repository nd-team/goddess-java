package com.bjike.goddess.business.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 变更资料名称
 */
public enum ChangeDataName {

    /**
     * 注册公司名称
     */
    REGISTERCOMPANYNAME(0),
    /**
     * 注册号/统一社会信用代码
     */
    REGISTERNUM(1),
    /**
     * 经营期限
     */
    OPERATIONPERIOD(2),
    /**
     * 注册类型
     */
    REGISTERTYPE(3),
    /**
     * 注册资本
     */
    REGISTERCAPITAL(4),
    /**
     * 经营范围
     */
    OPERATIONSCOPE(5),
    /**
     * 法人
     */
    LEGALPERSON(6),
    /**
     * 股东：股权比例
     */
    SHAREHOLDERS(7),
    /**
     * 地址
     */
    ADDRESS(8),
    /**
     * 状态
     */
    STATUS(9),
    /**
     * 核发日期
     */
    ISSUINGDATE(10),
    /**
     * 登记机关
     */
    REGISTRATIONAUTHOR(11),
    /**
     * 组织结构成员名称
     */
    ORGANIZATIONNEMNAME(12),
    /**
     * 职务
     */
    POSITION(13),
    /**
     * 职务产生方式
     */
    POSITIONWAY(14),
    /**
     * 是否法定代表人
     */
    REPRESENTATIVELEGAL(15),
    ;
    private int code;

    ChangeDataName(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
