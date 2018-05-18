package com.bjike.goddess.workhandover.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * Created by chenyang on 17-11-14.
 */
public enum WorkHandoverReason {

    /**
     * 入职
     */
    @ExcelValue ( name = "入职")
    INDUCTION ( 0 ),
    /**
     * 岗位轮换
     */
    @ExcelValue ( name = "岗位轮换")
    JOBROTATION ( 1 ),
    /**
     * 请假
     */
    @ExcelValue ( name = "请假")
    ASKFORLEAVE ( 2 ),
    /**
     * 人员调动
     */
    @ExcelValue ( name = "人员调动")
    MOBILIZE ( 3 ),
    /**
     * 离职
     */
    @ExcelValue ( name = "离职")
    LEAVE ( 4 );
    private int code;

    WorkHandoverReason(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static WorkHandoverReason getEnumConvert(int code) {
        WorkHandoverReason workHandoverReason = WorkHandoverReason.INDUCTION;
        if (code == WorkHandoverReason.INDUCTION.getCode ()) {
            workHandoverReason = WorkHandoverReason.INDUCTION;
        }
        if (code == WorkHandoverReason.JOBROTATION.getCode ()) {
            workHandoverReason = WorkHandoverReason.JOBROTATION;
        }
        if (code == WorkHandoverReason.ASKFORLEAVE.getCode ()) {
            workHandoverReason = WorkHandoverReason.ASKFORLEAVE;
        }
        if (code == WorkHandoverReason.MOBILIZE.getCode ()) {
            workHandoverReason = WorkHandoverReason.MOBILIZE;
        }
        if (code == WorkHandoverReason.LEAVE.getCode ()) {
            workHandoverReason = WorkHandoverReason.LEAVE;
        }
        return workHandoverReason;
    }

    public static String getStrConvert(int code) {
        String name = "";
        if (code == WorkHandoverReason.INDUCTION.getCode ()) {
            name = "入职";
        }
        if (code == WorkHandoverReason.JOBROTATION.getCode ()) {
            name = "岗位轮换";
        }
        if (code == WorkHandoverReason.ASKFORLEAVE.getCode ()) {
            name = "请假";
        }
        if (code == WorkHandoverReason.MOBILIZE.getCode ()) {
            name = "人员调动";
        }
        if (code == WorkHandoverReason.LEAVE.getCode ()) {
            name = "离职";
        }
        return name;
    }

    public static String exportStrConvert(WorkHandoverReason workHandoverReason) {
        String name = "";
        if (workHandoverReason.equals(WorkHandoverReason.INDUCTION)) {
            name = "入职";
        }
        if (workHandoverReason.equals(WorkHandoverReason.JOBROTATION)) {
            name = "岗位轮换";
        }
        if (workHandoverReason.equals(WorkHandoverReason.ASKFORLEAVE)) {
            name = "请假";
        }
        if (workHandoverReason.equals(WorkHandoverReason.MOBILIZE)) {
            name = "人员调动";
        }
        if (workHandoverReason.equals(WorkHandoverReason.LEAVE)) {
            name = "离职";
        }
        return name;
    }
}
