package com.bjike.goddess.taskallotment.vo;

import java.util.List;

/**
 * 人员标准工时汇总
 *
 * @Author: [chenjunhao]
 * @Date: [2017-09-18 18:19]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class TimeCountVO {
    /**
     * 地区
     */
    private String area;
    /**
     * 子表
     */
    private List<TimeSonVO> timeSonS;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<TimeSonVO> getTimeSonS() {
        return timeSonS;
    }

    public void setTimeSonS(List<TimeSonVO> timeSonS) {
        this.timeSonS = timeSonS;
    }
}
