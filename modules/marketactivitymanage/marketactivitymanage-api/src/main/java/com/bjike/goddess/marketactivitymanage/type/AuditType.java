package com.bjike.goddess.marketactivitymanage.type;

import com.bjike.goddess.common.utils.excel.ExcelValue;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;

/**
 * 审核枚举类型
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-20 09:09]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum AuditType {
    /**
     * 未通过
     */
    @ExcelValue(name = "未通过")
    NONE(0),
    /**
     * 通过
     */
    @ExcelValue(name = "通过")
    ALLOWED(1),
    /**
     * 拒绝
     */
    @ExcelValue(name = "拒绝")
    DENIED(2);

    private int value;

    AuditType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static AuditType getEnumConvert(int code) {
        AuditType auditType = AuditType.NONE;
        if (code == AuditType.NONE.getValue()) {
            auditType = AuditType.NONE;
        }
        if (code == AuditType.ALLOWED.getValue()) {
            auditType = AuditType.ALLOWED;
        }
        if (code == AuditType.DENIED.getValue()) {
            auditType = AuditType.DENIED;
        }

        return auditType;
    }

    public static String getStrConvert(int code) {
        String name = "";
        if (code == AuditType.NONE.getValue()) {
            name = "未通过";
        }
        if (code == AuditType.ALLOWED.getValue()) {
            name = "通过";
        }
        if (code == AuditType.DENIED.getValue()) {
            name = "拒绝";
        }
        return name;
    }

    public static String getFirstLetter(AuditType auditType) {
        String name = "";
        if (AuditType.NONE.equals(auditType)) {
            name = "WT";
        }
        if (AuditType.ALLOWED.equals(auditType)) {
            name = "TG";
        }
        if (AuditType.DENIED.equals(auditType)) {
            name = "JJ";
        }
        return name;
    }

    public static String exportStrConvert(AuditType auditType) {
        String name = "";
        if (auditType.equals(AuditType.NONE)) {
            name = "未通过";
        }
        if (auditType.equals(AuditType.ALLOWED)) {
            name = "通过";
        }
        if (auditType.equals(AuditType.DENIED)) {
            name = "拒绝";
        }
        return name;
    }
}
