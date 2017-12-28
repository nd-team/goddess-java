package com.bjike.goddess.socialinsurance.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 转正图形展示柱状图文字描述传数对象
 * @Author: [lijuntao]
 * @Date: [2017-09-09 15:32]
 * @Description: [转正图形展示柱状图文字描述传数对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class LegendBO extends BaseBO{
    /**
     * 数据
     */
    private String[] data;

    /**
     * orient
     */
    private String orient;

    /**
     * left
     */
    private String left;

    public LegendBO() {
    }

    public LegendBO(String[] data) {
        this.data = data;
    }

    public LegendBO(String[] data, String orient, String left) {
        this.data = data;
        this.orient = orient;
        this.left = left;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

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
}
