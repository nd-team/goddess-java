package com.bjike.goddess.employeecontract.bo;

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

    private String orient;

    private String left;

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

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }
}
