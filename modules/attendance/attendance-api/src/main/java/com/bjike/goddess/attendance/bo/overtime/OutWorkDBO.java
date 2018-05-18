package com.bjike.goddess.attendance.bo.overtime;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 加班汇总第四层
 *
 * @Author: [chenjunhao]
 * @Date: [2017-10-23 14:12]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class OutWorkDBO extends BaseBO {
    /**
     * 加班人
     */
    private String name;
    /**
     * 加班时长
     */
    private Double outWorkTime;
    /**
     * 正常工作日内加班时长
     */
    private Double normalTime;

    public OutWorkDBO() {
    }

    public OutWorkDBO(String name, Double outWorkTime, Double normalTime) {
        this.name = name;
        this.outWorkTime = outWorkTime;
        this.normalTime = normalTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getOutWorkTime() {
        return outWorkTime;
    }

    public void setOutWorkTime(Double outWorkTime) {
        this.outWorkTime = outWorkTime;
    }

    public Double getNormalTime() {
        return normalTime;
    }

    public void setNormalTime(Double normalTime) {
        this.normalTime = normalTime;
    }
}
