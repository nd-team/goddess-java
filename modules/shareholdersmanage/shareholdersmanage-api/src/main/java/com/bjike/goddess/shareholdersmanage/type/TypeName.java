package com.bjike.goddess.shareholdersmanage.type;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 类型名称
 *
 * @Author: [lijuntao]
 * @Date: [2017-03-23 11:35]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum TypeName {

    /**
     * 法人
     */
    @ExcelValue(name = "法人")
    LEGALPERSON(0),
    /**
     * 自然人
     */
    @ExcelValue(name = "自然人")
    NATURALPERSON(1);

    private int code;

    TypeName(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static String exportStrConvert(TypeName typeName) {
        String name = "";
        if (typeName.equals(TypeName.LEGALPERSON)) {
            name = "法人";
        }
        if (typeName.equals(TypeName.NATURALPERSON)) {
            name = "自然人";
        }
        return name;
    }
}
