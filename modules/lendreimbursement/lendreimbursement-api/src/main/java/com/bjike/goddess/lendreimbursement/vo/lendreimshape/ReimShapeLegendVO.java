package com.bjike.goddess.lendreimbursement.vo.lendreimshape;


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
public class ReimShapeLegendVO implements Serializable{

    /**
     * orient
     */
    private String orient;

    /**
     * left
     */
    private String left;

    /**
     * data
     */
    private List<String> data;

    public String getOrient() {
        return orient;
    }

    public void setOrient(String orient) {
        this.orient = orient;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public ReimShapeLegendVO(String orient, String left, List<String> data) {
        this.orient = orient;
        this.left = left;
        this.data = data;
    }

    public ReimShapeLegendVO() {
    }
}