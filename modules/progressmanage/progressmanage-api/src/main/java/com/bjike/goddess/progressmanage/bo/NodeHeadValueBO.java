package com.bjike.goddess.progressmanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 节点表头值业务传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-26 09:54 ]
 * @Description: [ 节点表头值业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class NodeHeadValueBO extends BaseBO {

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