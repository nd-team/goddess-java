package com.bjike.goddess.taskallotment.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 任务类型
 *
 * @Author: [chenyang]
 * @Date: [2018-01-05 14:25]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum TimesType {
    /**
     * 行政时长
     */
    @ExcelValue(name = "行政时长")
    ADMININSTRATIONS(0),
    /**
     * 工程时长
     */
    @ExcelValue(name = "工程时长")
    ENGINEERINGS(1),
    /**
     * 培训时长
     */
    @ExcelValue(name = "培训时长")
    TRAININGS(2);

    private int code;

    TimesType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    @Override
    public String toString() {
        String s="";
        switch (code){
            case 0:
                s="行政时长";
                break;
            case 1:
                s="工程时长";
                break;
            case 2:
                s="培训时长";
                break;
        }
        return s;
    }
}
