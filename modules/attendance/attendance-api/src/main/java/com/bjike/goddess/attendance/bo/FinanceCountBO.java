package com.bjike.goddess.attendance.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 财务出勤汇总表
 *
 * @Author: [chenjunhao]
 * @Date: [2017-10-18 20:30]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FinanceCountBO extends BaseBO {
    /**
     * 属性值
     */
    private String value;
    /**
     * 对应的值下标
     */
    private Integer index;
    /**
     * 背景是否为红色
     */
    private Boolean red;
    /**
     * 背景是否为绿色
     */
    private Boolean green;

    public FinanceCountBO(){
        super();
    }

    public FinanceCountBO(String value, Integer index, Boolean red, Boolean green) {
        this.value = value;
        this.index = index;
        this.red = red;
        this.green = green;
    }

    public Boolean getGreen() {
        return green;
    }

    public void setGreen(Boolean green) {
        this.green = green;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Boolean getRed() {
        return red;
    }

    public void setRed(Boolean red) {
        this.red = red;
    }
}
