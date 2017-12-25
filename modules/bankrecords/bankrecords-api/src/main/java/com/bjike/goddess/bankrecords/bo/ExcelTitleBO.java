package com.bjike.goddess.bankrecords.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * Excel表头字段对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-22 03:58 ]
 * @Description: [ Excel表头字段对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ExcelTitleBO extends BaseBO {

    /**
     * Excel表头字段
     */
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}