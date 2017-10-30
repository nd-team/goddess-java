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
public class ReimShapeYaxisVO implements Serializable{

    /**
     * type
     */
    private String type;

    /**
     * y轴名
     */
    private String name;

    /**
     * interval
     */
    private Integer interval;

    /**
     * AxisLabel
     */
    private AxisLabelVO axisLabelVO;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public AxisLabelVO getAxisLabelVO() {
        return axisLabelVO;
    }

    public void setAxisLabelVO(AxisLabelVO axisLabelVO) {
        this.axisLabelVO = axisLabelVO;
    }

    public ReimShapeYaxisVO(String type, String name, Integer interval, AxisLabelVO axisLabelVO) {
        this.type = type;
        this.name = name;
        this.interval = interval;
        this.axisLabelVO = axisLabelVO;
    }

    public ReimShapeYaxisVO() {
    }
}