package com.bjike.goddess.projectissuehandle.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 问题类型
 *
 * @Author: [lijuntao]
 * @Date: [17-3-27]
 * @Description: [问题类型]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess]
 */
public enum TypeProblem {
    /**
     * 派工类
     */
    @ExcelValue(name = "派工类")
    DISPATCHINGCLASS(0),
    /**
     * 人员类
     */
    @ExcelValue(name = "人员类")
    PERSONNELCLASS(1),
    /**
     * 进度类
     */
    @ExcelValue(name = "进度类")
    PROGRESSCLASS(2),
    /**
     * 交付类
     */
    @ExcelValue(name = "交付类")
    DELIVERCLASS(3),
    /**
     * 设备类
     */
    @ExcelValue(name = "设备类")
    DEVICECLASS(4),
    /**
     * 培训类
     */
    @ExcelValue(name = "培训类")
    TRAININGCLASS(5),
   ;

    private int code;

    TypeProblem(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
