package com.bjike.goddess.problemhandle.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 项目中问题受理和处理图形柱状图文字描述传数对象
 * @Author: [lijuntao]
 * @Date: [2017-09-09 15:32]
 * @Description: [项目中问题受理和处理图形柱状图文字描述传数对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class LegendBO extends BaseBO{
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
