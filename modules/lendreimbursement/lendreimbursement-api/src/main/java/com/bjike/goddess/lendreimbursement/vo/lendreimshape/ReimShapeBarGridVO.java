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
public class ReimShapeBarGridVO implements Serializable {

    /**
     * left
     */
    private String left;

    /**
     * rigth
     */
    private String right;

    /**
     * bottom
     */
    private String bottom;

    /**
     * containLabel
     */
    private Boolean containLabel;

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public String getBottom() {
        return bottom;
    }

    public void setBottom(String bottom) {
        this.bottom = bottom;
    }

    public Boolean getContainLabel() {
        return containLabel;
    }

    public void setContainLabel(Boolean containLabel) {
        this.containLabel = containLabel;
    }

    public ReimShapeBarGridVO() {
    }

    public ReimShapeBarGridVO(String left, String right, String bottom, Boolean containLabel) {
        this.left = left;
        this.right = right;
        this.bottom = bottom;
        this.containLabel = containLabel;
    }
}