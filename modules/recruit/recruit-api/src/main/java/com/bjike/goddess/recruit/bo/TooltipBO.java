package com.bjike.goddess.recruit.bo;

/**
 * 悬停提示传输对象
 *
 * @Author: [lijuntao]
 * @Date: [2017-09-09 15:32]
 * @Description: [悬停提示传输对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class TooltipBO {

    private String trigger = "axis";
    private String formatter;

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
