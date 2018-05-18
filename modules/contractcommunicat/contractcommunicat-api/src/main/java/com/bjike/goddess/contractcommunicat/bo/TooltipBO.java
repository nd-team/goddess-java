package com.bjike.goddess.contractcommunicat.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 转正图形展示传输对象
 * @Author: [lijuntao]
 * @Date: [2017-09-09 15:32]
 * @Description: [转正图形展示传输对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class TooltipBO extends BaseBO{
    /**
     * 名称
     */
    private String trigger;

    private String formatter;

    public TooltipBO() {
    }

    public TooltipBO(String trigger) {
        this.trigger = trigger;
    }

    public TooltipBO(String trigger, String formatter) {
        this.trigger = trigger;
        this.formatter = formatter;
    }

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }
}
