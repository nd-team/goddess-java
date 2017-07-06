package com.bjike.goddess.reportmanagement.vo;

/**
 * 资产表表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-19 11:19 ]
 * @Description: [ 资产表表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AssetVO {

    /**
     * id
     */
    private String id;
    /**
     * 起始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 资产
     */
    private String asset;

    /**
     * 资产年初数
     */
    private Double beginAsset;

    /**
     * 当前月发生额
     */
    private Double current;

    /**
     * 资产期末数
     */
    private Double endAsset;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getCurrent() {
        return current;
    }

    public void setCurrent(Double current) {
        this.current = current;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public Double getBeginAsset() {
        return beginAsset;
    }

    public void setBeginAsset(Double beginAsset) {
        this.beginAsset = beginAsset;
    }

    public Double getEndAsset() {
        return endAsset;
    }

    public void setEndAsset(Double endAsset) {
        this.endAsset = endAsset;
    }
}