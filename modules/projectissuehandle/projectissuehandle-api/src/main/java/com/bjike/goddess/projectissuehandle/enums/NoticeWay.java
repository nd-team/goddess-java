package com.bjike.goddess.projectissuehandle.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 通知方式枚举
 *
 * @Author: [xiazhili]
 * @Date: [17-4-25]
 * @Description: [通知方式枚举]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess]
 */
public enum NoticeWay {
    /**
     * 系统
     */
    @ExcelValue(name = "系统")
    SYSTEM(0),
    /**
     * 书面
     */
    @ExcelValue(name = "书面")
    WRITTEN(1),
    /**
     * 邮件
     */
    @ExcelValue(name = "邮件")
    MAIL(2),
    /**
     * 口头
     */
    @ExcelValue(name = "口头")
    ORAL(2),
    ;

    private int code;

    NoticeWay(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static String exportStrConvert(NoticeWay noticeWay) {
        String name = "";
        if (noticeWay.equals(NoticeWay.SYSTEM)) {
            name = "系统";
        }
        if (noticeWay.equals(NoticeWay.WRITTEN)) {
            name = "书面";
        }
        if (noticeWay.equals(NoticeWay.MAIL)) {
            name = "邮件";
        }
        if (noticeWay.equals(NoticeWay.ORAL)) {
            name = "口头";
        }
        return name;
    }
}
