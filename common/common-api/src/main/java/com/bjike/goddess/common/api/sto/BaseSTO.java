package com.bjike.goddess.common.api.sto;

import java.io.Serializable;

/**
 * @Author: [liguiqin]
 * @Date: [2017-01-18 15:59]
 * @Description: [业务转换传输基础对象]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class BaseSTO implements Serializable{
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
