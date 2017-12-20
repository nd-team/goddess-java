package com.bjike.goddess.businessprojectmanage.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 是否有合同立项
 *
 * @Author: [xiazhili]
 * @Date: [2017-10-21 19:56]
 * @Description: [是否有合同立项]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum MakeContract {

    /**
     * 预立项
     */
    @ExcelValue(name = "预立项")
    NOMAKE(0),
    /**
     * 立项
     */
    @ExcelValue(name = "立项")
    HADMAKE(1),
    /**
     * 不立项
     */
    @ExcelValue(name = "不立项")
    NOTMAKE(2),;


    private int code;

    MakeContract(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static MakeContract getEnumConvert(int code) {
        MakeContract businessType = MakeContract.NOMAKE;
        if (code == MakeContract.NOMAKE.getCode()) {
            businessType = MakeContract.NOMAKE;
        }
        if (code == MakeContract.HADMAKE.getCode()) {
            businessType = MakeContract.HADMAKE;
        }
        if (code == MakeContract.NOTMAKE.getCode()) {
            businessType = MakeContract.NOTMAKE;
        }
        return businessType;
    }

    public static String getStrConvert(int code) {
        String name = "";
        if (code == MakeContract.NOMAKE.getCode()) {
            name = "预立项";
        }
        if (code == MakeContract.HADMAKE.getCode()) {
            name = "立项";
        }
        if (code == MakeContract.NOTMAKE.getCode()) {
            name = "不立项";
        }
        return name;
    }


    public static String exportStrConvert(MakeContract businessType) {
        String name = "";
        if (businessType.equals(MakeContract.NOMAKE)) {
            name = "预立项";
        }
        if (businessType.equals(MakeContract.HADMAKE)) {
            name = "立项";
        }
        if (businessType.equals(MakeContract.NOTMAKE)) {
            name = "不立项";
        }
        return name;
    }
}
