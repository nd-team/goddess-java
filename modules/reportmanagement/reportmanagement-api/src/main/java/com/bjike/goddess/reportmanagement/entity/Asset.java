package com.bjike.goddess.reportmanagement.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.reportmanagement.enums.AssetType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 资产表
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-19 11:19 ]
 * @Description: [ 资产表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "reportmanagement_asset")
public class Asset extends BaseEntity {

//    /**
//     * 起始时间
//     */
//    @Column(name = "startTime", nullable = false, columnDefinition = "DATE   COMMENT '起始时间'")
//    private LocalDate startTime;
//
//    /**
//     * 结束时间
//     */
//    @Column(name = "endTime", nullable = false, columnDefinition = "DATE   COMMENT '结束时间'")
//    private LocalDate endTime;

    /**
     * 资产
     */
    @Column(name = "asset", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '资产'")
    private String asset;

//    /**
//     * 资产年初数
//     */
//    @Column(name = "beginAsset", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '资产年初数'")
//    private Double beginAsset;
//
//    /**
//     * 资产期末数
//     */
//    @Column(name = "endAsset", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '资产期末数'")
//    private Double endAsset;

    /**
     * 资产类型
     */
    @Column(name = "assetType", columnDefinition = "TINYINT(2)   COMMENT '资产类型'")
    private AssetType assetType;

    public AssetType getAssetType() {
        return assetType;
    }

    public void setAssetType(AssetType assetType) {
        this.assetType = assetType;
    }

//    public LocalDate getStartTime() {
//        return startTime;
//    }
//
//    public void setStartTime(LocalDate startTime) {
//        this.startTime = startTime;
//    }
//
//    public LocalDate getEndTime() {
//        return endTime;
//    }
//
//    public void setEndTime(LocalDate endTime) {
//        this.endTime = endTime;
//    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

//    public Double getBeginAsset() {
//        return beginAsset;
//    }
//
//    public void setBeginAsset(Double beginAsset) {
//        this.beginAsset = beginAsset;
//    }
//
//    public Double getEndAsset() {
//        return endAsset;
//    }
//
//    public void setEndAsset(Double endAsset) {
//        this.endAsset = endAsset;
//    }
}