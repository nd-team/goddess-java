package com.bjike.goddess.businessprojectmanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.organize.bo.AxisLabelBO;

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
    /**
     * 类型
     */
    private String type;
    private Boolean boundaryGap;

    public Boolean getBoundaryGap() {
        return boundaryGap;
    }

    public void setBoundaryGap(Boolean boundaryGap) {
        this.boundaryGap = boundaryGap;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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
