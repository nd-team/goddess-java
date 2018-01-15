package com.bjike.goddess.materialbuy.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * @Author: [chenjunhao]
 * @Date: [2018-01-04 13:53]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class TitleBO extends BaseBO {
    /**
     * 名称
     */
    private String text;

    /**
     * 位置
     */
    private String left;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
