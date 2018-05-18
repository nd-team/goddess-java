package com.bjike.goddess.customer.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 饼状图数据传数对象
 * @Author: [lijuntao]
 * @Date: [2017-09-09 15:32]
 * @Description: [饼状图数据传数对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DataBO extends BaseBO{
    /**
     * 数据值
     */
    private Integer value;
    /**
     * 描述名称
     */
    private String name;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
