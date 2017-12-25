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
public class ReimShapeToolTipVO implements Serializable{

    /**
     * trigger
     */
    private String trigger;

    /**
     * farmatter
     */
    private String formatter;


    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }

    public ReimShapeToolTipVO(String trigger, String formatter) {
        this.trigger = trigger;
        this.formatter = formatter;
    }

    public ReimShapeToolTipVO() {
    }
}