package com.bjike.goddess.rentcar.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 转正图形展示横坐标传输对象
 * @Author: [lijuntao]
 * @Date: [2017-09-09 15:32]
 * @Description: [转正图形展示纵坐标传数对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class XAxisBO extends BaseBO{
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
