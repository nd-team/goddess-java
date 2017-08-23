package com.bjike.goddess.marketdevelopment.to;

import java.io.Serializable;

/**
 * @Author: [dengjunren]
 * @Date: [2017-06-02 09:51]
 * @Description: [ 导出查询条件传输对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CollectTO implements Serializable {

    /**
     * 业务类型
     */
    private String type;

    /**
     * 地区
     */
    private String area;

    /**
     * 开始时间
     */
    private String start;

    /**
     * 结束时间
     */
    private String end;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
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
