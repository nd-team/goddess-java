package com.bjike.goddess.taskallotment.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 完成状态
 *
 * @Author: [chenjunhao]
 * @Date: [2017-09-18 09:52]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum FinishStatus {
    /**
     * 已完成
     */
    @ExcelValue(name = "已完成")
    FINISH(0),
    /**
     * 未完成
     */
    @ExcelValue(name = "未完成")
    UNFINISHED(1);

    private int code;

    FinishStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    @Override
    public String toString() {
        String s = "";
        switch (code) {
            case 0:
                s = "已完成";
                break;
            case 1:
                s = "未完成";
                break;
        }
        return s;
    }
}
