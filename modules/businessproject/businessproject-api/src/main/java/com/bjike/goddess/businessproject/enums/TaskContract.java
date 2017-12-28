package com.bjike.goddess.businessproject.enums;

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
public enum TaskContract {

    /**
     * 预立项
     */
    @ExcelValue(name = "未派工")
    NOTASK(0),
    /**
     * 立项
     */
    @ExcelValue(name = "已派工")
    HADTASK(1),
    /**
     * 不立项
     */
    @ExcelValue(name = "不派工")
    NOTTASK(2),;


    private int code;

    TaskContract(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static TaskContract getEnumConvert(int code) {
        TaskContract businessType = TaskContract.NOTASK;
        if (code == TaskContract.NOTASK.getCode()) {
            businessType = TaskContract.NOTASK;
        }
        if (code == TaskContract.HADTASK.getCode()) {
            businessType = TaskContract.HADTASK;
        }
        if (code == TaskContract.NOTTASK.getCode()) {
            businessType = TaskContract.NOTTASK;
        }
        return businessType;
    }

    public static String getStrConvert(int code) {
        String name = "";
        if (code == TaskContract.NOTASK.getCode()) {
            name = "未派工";
        }
        if (code == TaskContract.HADTASK.getCode()) {
            name = "已派工";
        }
        if (code == TaskContract.NOTTASK.getCode()) {
            name = "不派工";
        }
        return name;
    }


    public static String exportStrConvert(TaskContract businessType) {
        String name = "";
        if (TaskContract.NOTASK.equals(businessType)) {
            name = "未派工";
        }
        if (TaskContract.HADTASK.equals(businessType)) {
            name = "已派工";
        }
        if (TaskContract.NOTTASK.equals(businessType)) {
            name = "不派工";
        }
        return name;
    }
}
