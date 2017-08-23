package com.bjike.goddess.reportmanagement.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.reportmanagement.enums.AssetType;
import com.bjike.goddess.reportmanagement.enums.Type;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 资产表
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-19 11:19 ]
 * @Description: [ 资产表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AssetTO extends BaseTO {

//    /**
//     * 起始时间
//     */
//    private String startTime;
//
//    /**
//     * 结束时间
//     */
//    private String endTime;

    /**
     * 资产
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "资产不能为空")
    private String asset;

//    /**
//     * 资产年初数
//     */
//    private Double beginAsset;
//
//    /**
//     * 资产期末数
//     */
//    private Double endAsset;

    /**
     * 资产类型
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "资产类型不能为空")
    private AssetType assetType;

    /**
     * 运算类型
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "运算类型不能为空")
    private Type type;

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

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }


}