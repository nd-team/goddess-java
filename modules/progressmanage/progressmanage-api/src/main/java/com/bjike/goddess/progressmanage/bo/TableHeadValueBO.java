package com.bjike.goddess.progressmanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 进度表表头对应Value业务传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-19 04:48 ]
 * @Description: [ 进度表表头对应Value业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TableHeadValueBO extends BaseBO {

    /**
     * 表头
     */
//    private TableHeadBO tableHead;

    /**
     * 值
     */
    private String value;



    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}