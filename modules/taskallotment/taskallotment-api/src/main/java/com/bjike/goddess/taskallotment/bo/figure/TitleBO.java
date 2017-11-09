package com.bjike.goddess.taskallotment.bo.figure;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * @Author: [chenjunhao]
 * @Date: [2017-10-27 11:30]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class TitleBO extends BaseBO {
    /**
     * 汇总标题
     */
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
