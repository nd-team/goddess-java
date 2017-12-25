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
public class ReimShapeSeriesVO implements Serializable{

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
    private String radius;

    /**
     * seriesData
     */
    private List<ReimShapeSeriesDataVO> seriesDataVOList;

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

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }

    public List<ReimShapeSeriesDataVO> getSeriesDataVOList() {
        return seriesDataVOList;
    }

    public void setSeriesDataVOList(List<ReimShapeSeriesDataVO> seriesDataVOList) {
        this.seriesDataVOList = seriesDataVOList;
    }

    public ReimShapeSeriesVO() {
    }

    public ReimShapeSeriesVO(String name, String type, String radius, List<ReimShapeSeriesDataVO> seriesDataVOList) {
        this.name = name;
        this.type = type;
        this.radius = radius;
        this.seriesDataVOList = seriesDataVOList;
    }
}