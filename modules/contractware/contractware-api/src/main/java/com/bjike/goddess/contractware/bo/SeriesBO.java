package com.bjike.goddess.contractware.bo;

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

    private String radius;

    private String[] center;

    /**
     * 堆叠
     */
    private String stack;
    /**
     * 类型
     */
    private String type;

    /**
     * 标签
     */
    private LabelBO label;
    /**
     * 数据
     */
    private Double[] data;

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

    public Double[] getData() {
        return data;
    }

    public void setData(Double[] data) {
        this.data = data;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public LabelBO getLabel() {
        return label;
    }

    public void setLabel(LabelBO label) {
        this.label = label;
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }

    public String[] getCenter() {
        return center;
    }

    public void setCenter(String[] center) {
        this.center = center;
    }
}
