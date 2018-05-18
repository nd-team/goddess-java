package com.bjike.goddess.businessprojectmanage.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * @Author: [caiwenxian]
 * @Date: [2017-12-09 15:28]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

public enum ProgressStatus {

    TODO(1, "未总结"),

    DOING(2, "总结中"),

    DONE(3, "总结完成");

    private int code;

    private String remark;

    ProgressStatus(int code, String remark) {
        this.code = code;
        this.remark = remark;
    }

    public int getCode() {
        return this.code;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public static String getStrConvert(int code) {
        String name = "";
        if (code == ProgressStatus.TODO.getCode()) {
            name = "未总结";
        }
        if (code == ProgressStatus.DOING.getCode()) {
            name = "总结中";
        }
        if (code == ProgressStatus.DONE.getCode()) {
            name = "总结完成";
        }
        return name;
    }

    /**
     * 已立项
     *//*
    @ExcelValue(name = "未总结")
    TODO(1),
    *//**
     * 未立项
     *//*
    @ExcelValue(name = "总结中")
    DOING(2),

    @ExcelValue(name = "总结完成")
    DONE(3);

    private int code;

    ProgressStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }*/

}
