package com.bjike.goddess.businessprojectmanage.bo;

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

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }
}
