package com.bjike.goddess.lendreimbursement.vo.lendreimshape;


import java.io.Serializable;

/**
 * 日/周/月每个人的报销的情况
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-06 10:01 ]
 * @Description: [ 日/周/月每个人的报销的情况 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AxisLabelVO implements Serializable{

    /**
     * formatter
     */
    private String formatter;

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }

    public AxisLabelVO(String formatter) {
        this.formatter = formatter;
    }

    public AxisLabelVO() {
    }
}