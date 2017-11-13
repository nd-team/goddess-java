package com.bjike.goddess.attendance.vo.showpic;


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
public class ShowShapeBarToolTipVO implements Serializable {

    /**
     * trigger
     */
    private String trigger;

    /**
     * farmatter
     */
    private AxisPointerBarVO axisPointerBarVO;

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

    public AxisPointerBarVO getAxisPointerBarVO() {
        return axisPointerBarVO;
    }

    public void setAxisPointerBarVO(AxisPointerBarVO axisPointerBarVO) {
        this.axisPointerBarVO = axisPointerBarVO;
    }

    public ShowShapeBarToolTipVO(String trigger) {
        this.trigger = trigger;
    }

    public ShowShapeBarToolTipVO() {
    }
}