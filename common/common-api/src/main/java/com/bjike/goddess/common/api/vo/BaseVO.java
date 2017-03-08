package com.bjike.goddess.common.api.vo;

import java.io.Serializable;

/**
 * view object
 * action 数据封装实体基类
 *
 * @Author: [liguiqin]
 * @Date: [2017-03-01 17:29]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public abstract class BaseVO implements Serializable {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
