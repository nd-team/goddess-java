package com.bjike.goddess.budget.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * @Author: [dengjunren]
 * @Date: [2018-01-04 10:14]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SeriesBO extends BaseBO{
    private String name;

    private String type;

    private Double[] data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double[] getData() {
        return data;
    }

    public void setData(Double[] data) {
        this.data = data;
    }
}
