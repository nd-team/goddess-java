package com.bjike.goddess.projectissuehandle.enums;

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
    SYSTEM(0),
    /**
     * 书面
     */
    WRITTEN(1),
    /**
     * 邮件
     */
    MAIL(2),
    /**
     * 口头
     */
    ORAL(2),
    ;

    private int code;

    NoticeWay(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
