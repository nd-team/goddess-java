package com.bjike.goddess.organize.bo;

import java.io.Serializable;

/**
 * @Author: [dengjunren]
 * @Date: [2017-12-19 18:25]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DataBO implements Serializable{
    private String name;

    private Long value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
