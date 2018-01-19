package com.bjike.goddess.materialbuy.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * @Author: [chenjunhao]
 * @Date: [2018-01-15 09:52]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class YAxisBO extends BaseBO {
    /**
     * 数据
     */
    private Integer[] data;

    /**
     * 类型
     */
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer[] getData() {
        return data;
    }

    public void setData(Integer[] data) {
        this.data = data;
    }
}
