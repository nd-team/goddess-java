package com.bjike.goddess.materialbuy.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 转正图形展示柱状图文字描述传数对象
 * @Author: [chenjunhao]
 * @Date: [2018-01-15 09:42]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class LegendBO extends BaseBO {
    /**
     * 数据
     */
    private String[] data;

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }
}
