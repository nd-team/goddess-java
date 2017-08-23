package com.bjike.goddess.supplier.to;

import java.io.Serializable;

/**
 * @Author: [dengjunren]
 * @Date: [2017-06-14 10:58]
 * @Description: [ 供应商汇总传输对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CollectTo implements Serializable {

    /**
     * 地区
     */
    private String[] area;

    /**
     * 开始时间
     */
    private String start;

    /**
     * 结束时间
     */
    private String end;

    public String[] getArea() {
        return area;
    }

    public void setArea(String[] area) {
        this.area = area;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
