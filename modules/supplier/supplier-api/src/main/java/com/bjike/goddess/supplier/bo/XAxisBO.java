package com.bjike.goddess.supplier.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 供应商管理图形展示横坐标传输对象
 * @Author: [lijuntao]
 * @Date: [2017-09-09 15:32]
 * @Description: [供应商管理图形展示纵坐标传数对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class XAxisBO extends BaseBO{
    /**
     * 数据
     */
    private String[] data;
    /**
     * 展示图个数限制
     */
    private AxisLabelBO axisLabel;
    /**
     * 可选类型
     */
    private String type;
    /**
     * 刻度是否从横坐标0刻度开始
     */
    private Boolean boundaryGap;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getBoundaryGap() {
        return boundaryGap;
    }

    public void setBoundaryGap(Boolean boundaryGap) {
        this.boundaryGap = boundaryGap;
    }
}
