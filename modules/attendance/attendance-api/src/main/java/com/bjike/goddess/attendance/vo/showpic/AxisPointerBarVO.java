package com.bjike.goddess.attendance.vo.showpic;

import java.io.Serializable;

/**
 * @Author: [tanghaixiang]
 * @Date: [2017-10-25 16:26]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class AxisPointerBarVO implements Serializable {

    /**
     * type
     */
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AxisPointerBarVO(String type) {
        this.type = type;
    }

    public AxisPointerBarVO() {
    }
}
