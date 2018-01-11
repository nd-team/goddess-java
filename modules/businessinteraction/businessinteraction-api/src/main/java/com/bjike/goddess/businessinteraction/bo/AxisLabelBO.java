package com.bjike.goddess.businessinteraction.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 商业能力互动图形展示间隔数据传输对象
 * @Author: [lijuntao]
 * @Date: [2017-09-09 15:32]
 * @Description: [商业能力互动图形展示间隔数据传输对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class AxisLabelBO extends BaseBO{
    /**
     * 间隔
     */
    private Integer interval;

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }
}
