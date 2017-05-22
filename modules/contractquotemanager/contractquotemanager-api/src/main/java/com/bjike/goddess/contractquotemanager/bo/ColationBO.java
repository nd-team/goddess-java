package com.bjike.goddess.contractquotemanager.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * @Author: [dengjunren]
 * @Date: [2017-05-18 10:17]
 * @Description: [ 选项过滤传输对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ColationBO extends BaseBO {

    /**
     * 字段
     */
    private String value;

    public ColationBO(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public ColationBO() {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
