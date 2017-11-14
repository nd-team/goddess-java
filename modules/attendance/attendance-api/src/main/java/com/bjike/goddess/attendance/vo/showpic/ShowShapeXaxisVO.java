package com.bjike.goddess.attendance.vo.showpic;


import java.io.Serializable;
import java.util.List;

/**
 * 日/周/月每个人的报销的情况
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-06 10:01 ]
 * @Description: [ 日/周/月每个人的报销的情况 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ShowShapeXaxisVO implements Serializable{

    /**
     * type
     */
    private String type;

    /**
     * data
     */
    private List<String> data;

    /**
     * AxisTick
     */
    private AxisTickBarVO axisTickBarVO;

    /**
     * x轴名
     */
    private String name;

    /**
     * AxisPointer
     */
    private AxisPointerBarVO axisPointerBarVO;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public AxisTickBarVO getAxisTickBarVO() {
        return axisTickBarVO;
    }

    public void setAxisTickBarVO(AxisTickBarVO axisTickBarVO) {
        this.axisTickBarVO = axisTickBarVO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AxisPointerBarVO getAxisPointerBarVO() {
        return axisPointerBarVO;
    }

    public void setAxisPointerBarVO(AxisPointerBarVO axisPointerBarVO) {
        this.axisPointerBarVO = axisPointerBarVO;
    }

    public ShowShapeXaxisVO() {
    }

    public ShowShapeXaxisVO(String type, String name, List<String> data) {
        this.type = type;
        this.data = data;
        this.name = name;
    }
}