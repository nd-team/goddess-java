package com.bjike.goddess.common.api.bo;

import java.io.Serializable;

/**
 * business object
 *
 * @Author: [liguiqin]
 * @Date: [2017-01-18 15:59]
 * @Description: [业务转换传输基础对象]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public abstract  class BaseBO implements Serializable {
    /**
     * 数据行id
     */
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
