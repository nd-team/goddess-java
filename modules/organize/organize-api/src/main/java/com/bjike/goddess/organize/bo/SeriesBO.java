package com.bjike.goddess.organize.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 转正图形展示数据传输对象
 * @Author: [lijuntao]
 * @Date: [2017-09-09 15:32]
 * @Description: [转正图形展示数据传输对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SeriesBO extends BaseBO{
    /**
     * 名称
     */
    private String name;
    /**
     * 类型
     */
    private String type;
    /**
     * 数据
     */
    private Integer[] data;

    private String[] radius;

    private LabelBO label;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer[] getData() {
        return data;
    }

    public void setData(Integer[] data) {
        this.data = data;
    }

    public String[] getRadius() {
        return radius;
    }

    public void setRadius(String[] radius) {
        this.radius = radius;
    }

    public LabelBO getLabel() {
        return label;
    }

    public void setLabel(LabelBO label) {
        this.label = label;
    }
}
