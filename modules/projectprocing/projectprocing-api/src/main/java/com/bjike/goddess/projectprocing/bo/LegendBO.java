package com.bjike.goddess.projectprocing.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 项目结算验收签字展示柱状图文字描述传数对象
 * @Author: [lijuntao]
 * @Date: [2017-09-09 15:32]
 * @Description: [项目结算验收签字展示柱状图文字描述传数对象 ]
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
