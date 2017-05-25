package com.bjike.goddess.regionalprogresscollect.to;

import java.io.Serializable;

/**
 * @Author: [dengjunren]
 * @Date: [2017-05-17 17:16]
 * @Description: [ 查询数据传输对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FindTO implements Serializable {

    /**
     * 地区
     */
    private String area;

    /**
     * 类别
     */
    private String type;

    /**
     * 节点
     */
    private String node;

    /**
     * 年份
     */
    private Integer year;

    /**
     * 月份
     */
    private Integer month;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
}
