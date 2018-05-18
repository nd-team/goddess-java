package com.bjike.goddess.materialbuy.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.organize.bo.AxisLabelBO;

/**
 * @Author: [chenjunhao]
 * @Date: [2018-01-15 09:47]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class XAxisBO extends BaseBO {
    /**
    * 数据
    */
    private String[] data;
    private AxisLabelBO axisLabel;

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    public AxisLabelBO getAxisLabel() {
        return axisLabel;
    }

    public void setAxisLabel(AxisLabelBO axisLabel) {
        this.axisLabel = axisLabel;
    }
}
