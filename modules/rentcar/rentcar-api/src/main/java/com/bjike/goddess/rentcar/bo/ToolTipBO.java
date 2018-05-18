package com.bjike.goddess.rentcar.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-11-07 15:42]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ToolTipBO extends BaseBO {
    /**
     * 内容
     */
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
