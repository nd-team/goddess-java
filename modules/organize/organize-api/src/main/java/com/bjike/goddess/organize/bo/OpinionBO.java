package com.bjike.goddess.organize.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * @Author: [dengjunren]
 * @Date: [2017-05-15 14:17]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class OpinionBO extends BaseBO {

    /**
     * 字段
     */
    String value;

    public OpinionBO(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public OpinionBO() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
