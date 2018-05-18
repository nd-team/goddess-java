package com.bjike.goddess.fixedassets.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 固定资产类型
 * @Author: [lijuntao]
 * @Date: [2017-08-09 16:51]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum FixedAssetType {
    /**
     * 房屋、建筑物
     */
    @ExcelValue(name = "房屋、建筑物")
    HOUSESBUILDING(0),
    /**
     * 一般办公设备
     */
    @ExcelValue(name = "一般办公设备")
    GENERALEQUIPMENT(1),
    /**
     * 专用设备
     */
    @ExcelValue(name = "专用设备")
    SPECIALEQUIPMENT(2),
    /**
     * 机器机械生产设备
     */
    @ExcelValue(name = "机器机械生产设备")
    MACHINERYEQUIPMENT(3),
    /**
     * 器具、工具、家具
     */
    @ExcelValue(name = "器具、工具、家具")
    APPLIANCESTOOLSFURNITURE(4),
    /**
     * 运输工具
     */
    @ExcelValue(name = "运输工具")
    MEANSTRANSPORT(5),
    /**
     * 电子设备
     */
    @ExcelValue(name = "电子设备")
    ELECTRONICEQUIPMENT(6),
    /**
     * 其他固定资产
     */
    @ExcelValue(name = "其他固定资产")
    OTHERFIXEDASSETS(7)
    ;

    private int value;

    public int getValue() {
        return value;
    }

    FixedAssetType(int value) {
        this.value = value;
    }
}
