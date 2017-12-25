package com.bjike.goddess.materialinstock.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 物资入库属性类
 *
 * @Author: [sunfengtao]
 * @Date: [2017-05-11 11:06]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class AttributeBO extends BaseBO {

    /**
     * 存储地区
     */
    private String storageArea;

    /**
     * 项目组/部门
     */
    private String projectGroup;

    /**
     * 物品类型
     */
    private String materialType;

    /**
     * 物资名称
     */
    private String materialName;

    /**
     * 单位
     */
    private String unit;

    public String getStorageArea() {
        return storageArea;
    }

    public void setStorageArea(String storageArea) {
        this.storageArea = storageArea;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
