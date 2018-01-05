package com.bjike.goddess.shareholdersmanage.type;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 股东状态
 *
 * @Author: [lijuntao]
 * @Date: [2017-03-23 11:35]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum ShareholderStatus {

    /**
     * 正常
     */
    @ExcelValue(name = "正常")
    NORMAL(0),
    /**
     * 已注销
     */
    @ExcelValue(name = "已注销")
    HASCANCELLED(1);

    private int code;

    ShareholderStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static String exportStrConvert(ShareholderStatus shareholderStatus) {
        String name = "";
        if (shareholderStatus.equals(ShareholderStatus.NORMAL)) {
            name = "正常";
        }
        if (shareholderStatus.equals(ShareholderStatus.HASCANCELLED)) {
            name = "已注销";
        }
        return name;
    }
}
