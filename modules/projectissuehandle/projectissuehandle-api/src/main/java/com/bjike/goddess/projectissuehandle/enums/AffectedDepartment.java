package com.bjike.goddess.projectissuehandle.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 受影响部门枚举
 *
 * @Author: [xiazhili]
 * @Date: [17-4-25]
 * @Description: [受影响部门枚举]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess]
 */
public enum AffectedDepartment {

    /**
     * 影响1个部门
     */
    @ExcelValue(name = "影响1个部门")
    ONEDEPARTMENT(0),
    /**
     * 影响2-3个部门
     */
    @ExcelValue(name = "影响2-3个部门")
    TWOTOTHREEDEPARTMENT(1),
    /**
     * 影响3个部门以上
     */
    @ExcelValue(name = "影响3个部门以上")
    THREEDEPARTMENT(2),
    ;

    private int code;

    AffectedDepartment(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
    public static String exportStrConvert(AffectedDepartment affectedDepartment) {
        String name = "";
        if (affectedDepartment.equals(AffectedDepartment.ONEDEPARTMENT)) {
            name = "影响1个部门";
        }
        if (affectedDepartment.equals(AffectedDepartment.TWOTOTHREEDEPARTMENT)) {
            name = "影响2-3个部门";
        }
        if (affectedDepartment.equals(AffectedDepartment.THREEDEPARTMENT)) {
            name = "影响3个部门以上";
        }
        return name;
    }
}
