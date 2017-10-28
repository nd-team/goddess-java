package com.bjike.goddess.lendreimbursement.vo.lendreimshape;


import java.io.Serializable;
import java.util.List;

/**
 * 日/周/月每个人的报销的情况
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-06 10:01 ]
 * @Description: [ 日/周/月每个人的报销的情况 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ReimShapeMixSeriesVO implements Serializable {

    /**
     * name
     */
    private String name;

    /**
     * type
     */
    private String type;

    /**
     * radius
     */
    private String barWidth;

    /**
     * seriesData
     */
    private List<Double> seriesDataVOList;

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

    public String getBarWidth() {
        return barWidth;
    }

    public void setBarWidth(String barWidth) {
        this.barWidth = barWidth;
    }

    public List<Double> getSeriesDataVOList() {
        return seriesDataVOList;
    }

    public void setSeriesDataVOList(List<Double> seriesDataVOList) {
        this.seriesDataVOList = seriesDataVOList;
    }
}