package com.bjike.goddess.taskallotment.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 任务状态
 *
 * @Author: [chenjunhao]
 * @Date: [2017-09-16 11:43]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum TaskStatus {
    /**
     * 已完成
     */
    @ExcelValue(name = "已完成")
    FINISH ( 0 ),
    /**
     * 正在执行
     */
    @ExcelValue(name = "正在执行")
    DOING ( 1 ),
    /**
     * 未完成
     */
    @ExcelValue(name = "未完成")
    UNFINISHED(2),

    /**
     * 待接收
     */
    @ExcelValue ( name = "待接收")
    RECEIVE(3),
    /**
     * 不接收
     */
    @ExcelValue(name = "不接收")
    NOTRECEIVE(4),

    /**
     * 上报待审核
     */
    @ExcelValue(name = "上报待审核")
    TOBEAUDITED(5);

    private int code;

    TaskStatus(int code) {
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
                s = "正在执行";
                break;
            case 2:
                s = "未完成";
                break;
            case 3:
                s = "待接收";
                break;
            case 4:
                s = "不接收";
                break;
            case 5:
                s = "上报待审核";
                break;
        }
        return s;
    }
}
