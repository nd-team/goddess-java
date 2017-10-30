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
public class ReimShapeTitleVO implements Serializable{

    /**
     * text
     */
    private String text;

    /**
     * subtext
     */
    private String subtext;

    /**
     * x
     */
    private String x;

    /**
     * y
     */
    private String y;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSubtext() {
        return subtext;
    }

    public void setSubtext(String subtext) {
        this.subtext = subtext;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public ReimShapeTitleVO() {
    }

    public ReimShapeTitleVO(String text, String subtext, String x, String y) {
        this.text = text;
        this.subtext = subtext;
        this.x = x;
        this.y = y;
    }
}