package com.bjike.goddess.common.api.to;

import java.io.Serializable;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-13 09:41]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public abstract  class BaseTO implements Serializable {
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
