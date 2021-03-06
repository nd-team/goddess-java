package com.bjike.goddess.reportmanagement.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.reportmanagement.enums.AssetType;
import com.bjike.goddess.reportmanagement.enums.Type;

/**
 * 资产表业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-19 11:19 ]
 * @Description: [ 资产表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AssetBO extends BaseBO {

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
     * 资产类型
     */
    private AssetType assetType;

    /**
     * 运算类型
     */
    private Type type;
    /**
     * 资产行次
     */
    private Integer assetNum;

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

    public AssetBO() {
    }

    public AssetBO(String id, String startTime, String endTime, String asset, AssetType assetType, Type type, Integer assetNum, Double beginAsset, Double current, Double endAsset) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.asset = asset;
        this.assetType = assetType;
        this.type = type;
        this.assetNum = assetNum;
        this.beginAsset = beginAsset;
        this.current = current;
        this.endAsset = endAsset;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public AssetType getAssetType() {
        return assetType;
    }

    public void setAssetType(AssetType assetType) {
        this.assetType = assetType;
    }

    public Integer getAssetNum() {
        return assetNum;
    }

    public void setAssetNum(Integer assetNum) {
        this.assetNum = assetNum;
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

    @Override
    public String toString() {
        return "AssetBO{" +
                "startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", asset='" + asset + '\'' +
                ", assetType=" + assetType +
                ", type=" + type +
                ", assetNum=" + assetNum +
                ", beginAsset=" + beginAsset +
                ", current=" + current +
                ", endAsset=" + endAsset +
                '}';
    }
}