package com.bjike.goddess.staffentry.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 悬停提示传输对象
 * @Author: [lijuntao]
 * @Date: [2017-09-09 15:32]
 * @Description: [悬停提示传输对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class TooltipBO extends BaseBO{
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
