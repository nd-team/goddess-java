package com.bjike.goddess.materialbuy.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 悬停提示传输对象
 * @Author: [chenjunhao]
 * @Date: [2018-01-15 09:36]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class TooltipBO extends BaseBO {

    /**
     * 垂直线
     */
    private String trigger;

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }


}
